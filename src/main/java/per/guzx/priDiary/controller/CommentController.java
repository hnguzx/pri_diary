package per.guzx.priDiary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdComment;
import per.guzx.priDiary.service.PdCommentService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author Guzx
 * @date 2020/09/07
 * 评论接口
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论")
public class CommentController {

    @Resource
    private PdCommentService pdCommentService;

    /**
     * 新增评论
     * @param pdComment 新增的评论
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation("新增评论")
    public ApiResp<Object> add(@Valid @RequestBody PdComment pdComment) {
        pdCommentService.save(pdComment);
        return ApiResp.retOk();
    }

    /**
     * 删除评论
     * @param id 被删除的评论的唯一id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除评论")
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
    @ApiOperation("更新评论")
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
    @ApiOperation("查看评论详情")
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
    @ApiOperation("获取评论列表")
    public ApiResp<PageInfo<List<PdComment>>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        PageInfo<List<PdComment>> pageInfo = pdCommentService.findAll();
        return ApiResp.retOk(pageInfo);
    }
}
