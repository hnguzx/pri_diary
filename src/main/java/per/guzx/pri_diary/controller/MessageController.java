package per.guzx.pri_diary.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdMessage;
import per.guzx.pri_diary.pojo.PdUser;
import per.guzx.pri_diary.service.PdMessageService;
import per.guzx.pri_diary.service.PdUserService;
import per.guzx.pri_diary.tool.NoticeUtil;

import java.security.Principal;
import java.util.List;

/**
 * Created by Guzx on 2020/09/07.
 * 消息接口
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {
    @Autowired
    private PdMessageService pdMessageService;

    @Autowired
    private NoticeUtil noticeUtil;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private PdUserService userService;

    @SubscribeMapping("/connect")
    public ApiResp connect(Principal principal) {
        log.trace("用户" + principal.getName() + "已连接聊天服务");
        // 连接成功后应该将用户信息保存
        PdUser user = userService.findByName(principal.getName());
        return ApiResp.retOk(ErrorEnum.USER_CONNECT_SUCCESS);
    }

    @SubscribeMapping({"/disConnect"})
    public ApiResp closeConnect(Principal principal) {
        log.trace("用户" + principal.getName() + "已断开聊天服务");
        // 断开连接后应该将用户信息清除
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
        PdMessage message = JSONObject.parseObject(body,PdMessage.class);

        //  发送到用户和监听地址
        noticeUtil.sendTxtToUser(message.getMsgReceiver(), "/queue/customer", message.getMsgContent());
    }

    /**
     * 新增消息
     *
     * @param pdMessage
     * @return
     */
    @PostMapping("/sendMsg")
    public ApiResp sendMsg(@RequestBody PdMessage pdMessage) {
        pdMessageService.sendMsg(pdMessage);
        noticeUtil.sendTxtToUser(pdMessage.getMsgReceiver(), "/client_chat/receive_msg", pdMessage.getMsgContent());
        return ApiResp.retOk();
    }

    /**
     * 消息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResp detail(@PathVariable Integer id) {
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
    public ApiResp list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PdMessage> list = pdMessageService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResp.retOk(pageInfo);
    }
}
