package per.guzx.pri_diary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdMessage;
import per.guzx.pri_diary.service.PdMessageService;
import per.guzx.pri_diary.tool.NoticeUtil;

import java.util.List;

/**
* Created by Guzx on 2020/09/07.
 * 消息接口
*/
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private PdMessageService pdMessageService;

    @Autowired
    private NoticeUtil noticeUtil;

    /**
     * 新增消息
     * @param pdMessage
     * @return
     */
    @PostMapping("/sendMsg")
    public ApiResp sendMsg(@RequestBody PdMessage pdMessage) {
        pdMessageService.sendMsg(pdMessage);
        noticeUtil.sendTxtToUser(pdMessage.getMsgReceiver(),"/client_chat/receive_msg",pdMessage.getMsgContent());
        return ApiResp.retOk();
    }

    /**
     * 消息详情
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
