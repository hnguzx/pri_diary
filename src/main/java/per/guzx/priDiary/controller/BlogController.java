package per.guzx.priDiary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdBlog;
import per.guzx.priDiary.service.PdBlogService;
import per.guzx.priDiary.tool.Groups;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author Guzx
 * @date 2020/09/07
 * 博客接口
 */
@RestController
@RequestMapping("/blog")
@Api(tags = "博客")
public class BlogController {
    @Resource
    private PdBlogService pdBlogService;


    /**
     * 新增博客
     * @param pdBlog
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增博客")
    public ApiResp<PdBlog> add(@Validated(Groups.Add.class) @RequestBody PdBlog pdBlog) {
        pdBlogService.save(pdBlog);
        return ApiResp.retOk();
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除博客")
    public ApiResp delete(@PathVariable Integer id) {
        pdBlogService.deleteById(id);
        return ApiResp.retOk();
    }

    /**
     * 更新博客
     * @param pdBlog
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("更新博客")
    public ApiResp<PdBlog> update(@Validated(Groups.Update.class) @RequestBody PdBlog pdBlog) {
        pdBlogService.update(pdBlog);
        return ApiResp.retOk();
    }

    /**
     * 获取博客详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("获取博客详情")
    public ApiResp<PdBlog> detail(@PathVariable Integer id) {
        PdBlog pdBlog = pdBlogService.findById(id);
        return ApiResp.retOk(pdBlog);
    }

    /**
     * 查询博客列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询博客列表")
    public ApiResp<PageInfo<List<PdBlog>>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
        PageInfo<List<PdBlog>> pageInfo = pdBlogService.findAll(page,size);
        return ApiResp.retOk(pageInfo);
    }
}
