package per.guzx.priDiary.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdFriend;
import per.guzx.priDiary.service.PdFriendService;
import per.guzx.priDiary.service.PdMessageService;
import per.guzx.priDiary.tool.Groups;
import per.guzx.priDiary.tool.NoticeUtil;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author Guzx
 * @date 2020/09/07
 * 好友接口
 */
@RestController
@RequestMapping("/friend")
@Api(tags = "好友")
public class FriendController {
    @Resource
    private PdFriendService pdFriendService;

    @Resource
    private PdMessageService pdMessageService;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private NoticeUtil noticeUtil;

    /**
     * 新增好友
     *
     * @param pdFriend
     * @return
     */
    @PostMapping("/add")
//    @MessageMapping("/addFriend")
//    @SubscribeMapping("/addFriend")
    @ApiOperation("新增好友")
    public ApiResp<PdFriend> add(@Validated(Groups.Add.class) @RequestBody PdFriend pdFriend) {
        boolean added = pdFriendService.save(pdFriend);
        if(added){
            return ApiResp.retOk(pdFriend);
        }
//        noticeUtil.sendTxtToUser(pdFriend.getFriendUserId(),"/client_user/add_friend","用户"+ JSON.toJSONString(pdFriend)+"请求添加您为好友！");
        return ApiResp.retOk();
    }

    /**
     * 删除好友
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除好友")
    public ApiResp delete(@PathVariable Integer id) {
        pdFriendService.deleteById(id);
        return ApiResp.retOk();
    }

    /**
     * 更新好友相关信息
     *
     * @param pdFriend
     * @return
     */
    @PatchMapping("/update")
    @ApiOperation("更新好友相关信息")
    public ApiResp<PdFriend> update(@Validated(Groups.Update.class) @RequestBody PdFriend pdFriend) {
        pdFriendService.update(pdFriend);
        return ApiResp.retOk(pdFriend);
    }

    /**
     * 查看好友详情信息
     *
     * @param id
     * @return
     */
    /*@GetMapping("/{id}")
    public ApiResp<PdFriend> detail(@PathVariable Integer id) {
        PdFriend pdFriend = pdFriendService.findById(id);
        return ApiResp.retOk(pdFriend);
    }*/

    /**
     * 获取好友列表
     * @param myUserId
     * @param start
     * @param size
     * @param global
     * @return
     */
    @GetMapping(value = {"/{myUserId}/{start}/{size}/{global}", "/{myUserId}/{start}/{size}"})
    @ApiOperation("获取好友列表")
    public ApiResp<PageInfo> list(@PathVariable(value = "myUserId",required = true) int myUserId, @PathVariable("start") int start, @PathVariable("size") int size, @PathVariable(value = "global", required = false) String global) {
        PageInfo pageInfo = pdFriendService.findFriendByInfo(myUserId, start, size, global);
        return ApiResp.retOk(pageInfo);
    }

    /**
     * 给好友发送消息
     *
     * @param principal
     * @param body
     */
    /*@MessageMapping("/sendUser")
    public void sendToUser(Principal principal, String body) {
        String[] args = body.split(",");
        String desUser = args[0];
        String msg = args[1];
        //  发送到用户和监听地址
        simpMessagingTemplate.convertAndSendToUser(desUser, "/client_chat/friend", msg);
    }*/
}
