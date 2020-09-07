package per.guzx.pri_diary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.core.ApiResp;
import per.guzx.pri_diary.pojo.PdMessage;
import per.guzx.pri_diary.service.PdMessageService;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by Guzx on 2020/09/07.
 * 消息接口
*/
@RestController
@RequestMapping("/message")
public class PdMessageController {
    @Autowired
    private PdMessageService pdMessageService;

    /**
     * 新增消息
     * @param pdMessage
     * @return
     */
    @PostMapping("/add")
    public ApiResp add(@RequestBody PdMessage pdMessage) {
        pdMessageService.save(pdMessage);
        return ApiResp.retOk();
    }

    /**
     * 删除消息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ApiResp delete(@PathVariable Integer id) {
        pdMessageService.deleteById(id);
        return ApiResp.retOk();
    }

    /**
     * 更新消息
     * @param pdMessage
     * @return
     */
    @PutMapping("/update")
    public ApiResp update(@RequestBody PdMessage pdMessage) {
        pdMessageService.update(pdMessage);
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
