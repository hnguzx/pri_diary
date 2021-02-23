package per.guzx.priDiary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdBlog;
import per.guzx.priDiary.service.PdBlogService;

import java.util.List;

/**
 * Created by Guzx on 2020/09/07.
 * 博客接口
 */
@RestController
@RequestMapping("/blog")
@Api(tags = "博客管理相关接口")
public class BlogController {
    @Autowired
    private PdBlogService pdBlogService;


    /**
     * 新增博客
     * @param pdBlog
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加博客")
    public ApiResp<PdBlog> add(@RequestBody PdBlog pdBlog) {
        pdBlogService.save(pdBlog);
        return ApiResp.retOk();
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
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
    public ApiResp<PdBlog> update(@RequestBody PdBlog pdBlog) {
        pdBlogService.update(pdBlog);
        return ApiResp.retOk();
    }

    /**
     * 获取博客详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResp<PdBlog> detail(@PathVariable Integer id) {
        PdBlog pdBlog = pdBlogService.findById(id);
        return ApiResp.retOk(pdBlog);
    }

    /**
     * 分页查询博客
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ApiResp list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PdBlog> list = pdBlogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResp.retOk(pageInfo);
    }
}
