package per.guzx.priDiary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdComment;
import per.guzx.priDiary.service.PdCommentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Guzx on 2020/09/07.
 * 评论接口
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private PdCommentService pdCommentService;

    /**
     * 新增评论
     * @param pdComment 新增的评论
     * @return 新增结果
     */
    @PostMapping("/add")
    public ApiResp<Object> add(@RequestBody PdComment pdComment) {
        pdCommentService.save(pdComment);
        return ApiResp.retOk();
    }

    /**
     * 删除评论
     * @param id 被删除的评论的唯一id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ApiResp<Object> delete(@PathVariable Integer id) {
        pdCommentService.deleteById(id);
        return ApiResp.retOk();
    }

    /**
     * 更新评论
     * @param pdComment 更新后的评论
     * @return 更新结果
     */
    @PutMapping("/update")
    public ApiResp<Object> update(@RequestBody PdComment pdComment) {
        pdCommentService.update(pdComment);
        return ApiResp.retOk();
    }

    /**
     * 查看评论详情
     * @param id 评论的唯一标示
     * @return 评论详情
     */
    @GetMapping("/{id}")
    public ApiResp<PdComment> detail(@PathVariable Integer id) {
        PdComment pdComment = pdCommentService.findById(id);
        return ApiResp.retOk(pdComment);
    }

    /**
     * 获取评论列表
     * @param page 当前页码
     * @param size 当前页的数据量
     * @return 评论列表
     */
    @GetMapping("/list")
    public ApiResp<PageInfo<PdComment>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PdComment> list = pdCommentService.findAll();
        PageInfo<PdComment> pageInfo = new PageInfo<>(list);
        return ApiResp.retOk(pageInfo);
    }
}
