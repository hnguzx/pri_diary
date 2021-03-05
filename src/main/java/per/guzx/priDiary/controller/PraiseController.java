package per.guzx.priDiary.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdPraise;
import per.guzx.priDiary.service.PdPraiseService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author Guzx
 * @date 2020/09/07
 * 点赞接口
 */
@RestController
@RequestMapping("/praise")
@Api(tags = "点赞")
public class PraiseController {
    @Resource
    private PdPraiseService pdPraiseService;

    /**
     * 新增点赞
     * @param pdPraise
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增点赞")
    public ApiResp add(@Valid @RequestBody PdPraise pdPraise) {
        pdPraiseService.save(pdPraise);
        return ApiResp.retOk();
    }

    /**
     * 删除点赞
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除点赞")
    public ApiResp delete(@PathVariable Integer id) {
        pdPraiseService.deleteById(id);
        return ApiResp.retOk();
    }

    /**
     * 更新点赞
     * @param pdPraise
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("更新点赞")
    public ApiResp update(@RequestBody PdPraise pdPraise) {
        pdPraiseService.update(pdPraise);
        return ApiResp.retOk();
    }

    /**
     * 点赞详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("获取点赞详情")
    public ApiResp detail(@PathVariable Integer id) {
        PdPraise pdPraise = pdPraiseService.findById(id);
        return ApiResp.retOk(pdPraise);
    }

    /**
     * 点赞列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询点赞列表")
    public ApiResp<PageInfo> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo pageInfo = pdPraiseService.findAll();
        return ApiResp.retOk(pageInfo);
    }
}
