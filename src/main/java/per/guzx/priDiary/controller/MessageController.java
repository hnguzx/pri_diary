package per.guzx.priDiary.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.*;
import per.guzx.priDiary.enumeration.ErrorEnum;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdMessage;
import per.guzx.priDiary.pojo.PdUser;
import per.guzx.priDiary.service.PdMessageService;
import per.guzx.priDiary.service.PdUserService;
import per.guzx.priDiary.tool.NoticeUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author Guzx
 * @date 2020/09/07
 * 消息接口
 */
@RestController
@RequestMapping("/message")
@Slf4j
@Api(tags = "消息")
public class MessageController {
    @Resource
    private PdMessageService pdMessageService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private PdUserService userService;

    @Resource
    private NoticeUtil noticeUtil;

    @SubscribeMapping("/connect")
    public ApiResp connect(Principal principal) {
        log.trace("用户" + principal.getName() + "已连接聊天服务");
        // 连接成功后应该将用户信息保存
        PdUser user = userService.findByName(principal.getName());
        redisTemplate.opsForHash().put("loggedUser", user.getUsername(), user.getUserId().toString());
        // 判断是否有其的未读消息，先去redis中查，再去数据库中查
        long unReadAmount = redisTemplate.opsForList().size(user.getUsername());
        for (int i = 0; i < unReadAmount; i++) {
            PdMessage message = JSONObject.parseObject((String) redisTemplate.opsForList().leftPop(user.getUsername()), PdMessage.class);
            pdMessageService.sendMsg(message);
        }
        return ApiResp.retOk(ErrorEnum.USER_CONNECT_SUCCESS);
    }

    @SubscribeMapping({"/disConnect"})
    public ApiResp closeConnect(Principal principal) {
        log.trace("用户" + principal.getName() + "已断开聊天服务");
        // 断开连接后应该将用户信息清除
        redisTemplate.opsForHash().delete("loggedUser", principal.getName());
        return ApiResp.retOk(ErrorEnum.USER_DISCONNECT_SUCCESS);
    }

    // 定义消息请求路径
    @MessageMapping("/notice")
    // 定义结果发送到特定路径
    @SendTo("/sub/chat")
    public String sendMessage(String value) {
        return value;
    }

    /**
     * 发送消息给特定用户
     *
     * @param body
     */
    @MessageMapping("/sendUser")
    public void sendToUser(String body) {
        // 需要添加所需参数的构造器
        PdMessage message = JSONObject.parseObject(body, PdMessage.class);
        pdMessageService.sendMsg(message);
    }

    /**
     * 新增消息
     *
     * @param pdMessage
     * @return
     */
    @PostMapping("/sendMsg")
    public ApiResp sendMsg(@Valid @RequestBody PdMessage pdMessage) {
        pdMessageService.sendMsg(pdMessage);
        noticeUtil.sendTxtToUser(pdMessage.getMsgReceiver(), "/client_chat/receive_msg", pdMessage);
        return ApiResp.retOk();
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("消息详情")
    public ApiResp<PdMessage> detail(@PathVariable("id") Integer id) {
        PdMessage pdMessage = pdMessageService.findById(id);
        return ApiResp.retOk(pdMessage);
    }

    /**
     * 消息列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("消息列表")
    public ApiResp<PageInfo<List<PdMessage>>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        PageInfo<List<PdMessage>> pageInfo = pdMessageService.findAll();
        return ApiResp.retOk(pageInfo);
    }
}
