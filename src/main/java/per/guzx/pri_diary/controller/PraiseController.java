package per.guzx.pri_diary.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdPraise;
import per.guzx.pri_diary.service.PdPraiseService;

import java.util.List;

/**
 * Created by Guzx on 2020/09/07.
 * 点赞接口
 */
@RestController
@RequestMapping("/praise")
public class PraiseController {
    @Autowired
    private PdPraiseService pdPraiseService;

    /**
     * 新增点赞
     * @param pdPraise
     * @return
     */
    @PostMapping("/add")
    public ApiResp add(@RequestBody PdPraise pdPraise) {
        pdPraiseService.save(pdPraise);
        return ApiResp.retOk();
    }

    /**
     * 删除点赞
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
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
    public ApiResp list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PdPraise> list = pdPraiseService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResp.retOk(pageInfo);
    }
}
