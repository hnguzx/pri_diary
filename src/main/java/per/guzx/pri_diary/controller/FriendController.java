package per.guzx.pri_diary.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PageInfo;
import per.guzx.pri_diary.pojo.PdFriend;
import per.guzx.pri_diary.pojo.PdMessage;
import per.guzx.pri_diary.service.PdFriendService;
import per.guzx.pri_diary.service.PdMessageService;
import per.guzx.pri_diary.tool.NoticeUtil;

import java.security.Principal;

/**
 * Created by Guzx on 2020/09/07.
 * 好友接口
 */
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private PdFriendService pdFriendService;

    @Autowired
    private PdMessageService pdMessageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
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
    public ApiResp<PdFriend> add(@RequestBody PdFriend pdFriend) {
        boolean added = pdFriendService.save(pdFriend);
        if(added){
            return ApiResp.retOk(pdFriend);
        }
        noticeUtil.sendTxtToUser(pdFriend.getFriendUserId(),"/client_user/add_friend","用户"+ JSON.toJSONString(pdFriend)+"请求添加您为好友！");
        return ApiResp.retOk();
    }

    /**
     * 删除好友
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
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
    public ApiResp<PdFriend> update(@RequestBody PdFriend pdFriend) {
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
