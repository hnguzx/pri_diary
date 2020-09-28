package per.guzx.pri_diary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdComment;
import per.guzx.pri_diary.service.PdCommentService;

import java.util.List;

/**
 * Created by Guzx on 2020/09/07.
 * 评论接口
 */
@RestController
@RequestMapping("/comment")
public class PdCommentController {
    @Autowired
    private PdCommentService pdCommentService;

    /**
     * 新增评论
     * @param pdComment
     * @return
     */
    @PostMapping("/add")
    public ApiResp add(@RequestBody PdComment pdComment) {
        pdCommentService.save(pdComment);
        return ApiResp.retOk();
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ApiResp delete(@PathVariable Integer id) {
        pdCommentService.deleteById(id);
        return ApiResp.retOk();
    }

    /**
     * 更新评论
     * @param pdComment
     * @return
     */
    @PutMapping("/update")
    public ApiResp update(@RequestBody PdComment pdComment) {
        pdCommentService.update(pdComment);
        return ApiResp.retOk();
    }

    /**
     * 查看评论详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResp detail(@PathVariable Integer id) {
        PdComment pdComment = pdCommentService.findById(id);
        return ApiResp.retOk(pdComment);
    }

    /**
     * 获取评论列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ApiResp list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PdComment> list = pdCommentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResp.retOk(pageInfo);
    }
}
