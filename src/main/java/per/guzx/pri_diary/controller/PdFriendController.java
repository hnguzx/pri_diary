package per.guzx.pri_diary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.core.ApiResp;
import per.guzx.pri_diary.pojo.PdFriend;
import per.guzx.pri_diary.service.PdFriendService;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by Guzx on 2020/09/07.
 * 好友接口
*/
@RestController
@RequestMapping("/friend")
public class PdFriendController {
    @Autowired
    private PdFriendService pdFriendService;

    /**
     * 新增好友
     * @param pdFriend
     * @return
     */
    @PostMapping("/add")
    public ApiResp add(@RequestBody PdFriend pdFriend) {
        pdFriendService.save(pdFriend);
        return ApiResp.retOk();
    }

    /**
     * 删除好友
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
     * @param pdFriend
     * @return
     */
    @PutMapping("/update")
    public ApiResp update(@RequestBody PdFriend pdFriend) {
        pdFriendService.update(pdFriend);
        return ApiResp.retOk();
    }

    /**
     * 查看好友详情信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResp detail(@PathVariable Integer id) {
        PdFriend pdFriend = pdFriendService.findById(id);
        return ApiResp.retOk(pdFriend);
    }

    /**
     * 获取好友列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ApiResp list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PdFriend> list = pdFriendService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResp.retOk(pageInfo);
    }
}
