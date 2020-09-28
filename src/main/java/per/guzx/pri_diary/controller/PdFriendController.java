package per.guzx.pri_diary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdFriend;
import per.guzx.pri_diary.service.PdFriendService;

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
    public ApiResp<PdFriend> add(@RequestBody PdFriend pdFriend) {
        pdFriendService.save(pdFriend);
        return ApiResp.retOk(pdFriend);
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
    @PatchMapping("/update")
    public ApiResp<PdFriend> update(@RequestBody PdFriend pdFriend) {
        pdFriendService.update(pdFriend);
        return ApiResp.retOk(pdFriend);
    }

    /**
     * 查看好友详情信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResp<PdFriend> detail(@PathVariable Integer id) {
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
    public ApiResp<PageInfo> list(@RequestBody PageInfo pageInfo) {
        PageInfo friends = pdFriendService.findAll(pageInfo.getPageNum(),pageInfo.getPageSize());
        return ApiResp.retOk(friends);
    }
}
