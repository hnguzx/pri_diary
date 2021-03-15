package per.guzx.priDiary.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdDiary;
import per.guzx.priDiary.service.PdBlogService;
import per.guzx.priDiary.service.PdDiaryService;
import per.guzx.priDiary.serviceImpl.PdBlogServiceImpl;
import per.guzx.priDiary.tool.FileUtil;
import per.guzx.priDiary.tool.Groups;

import javax.annotation.Resource;
import javax.servlet.http.Part;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 日记接口
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/diary")
@Slf4j
@Api(tags = "日记")
public class DiaryController {
    @Resource
    private PdDiaryService pdDiaryService;

    @Resource
    private FileUtil fileUtil;

    /**
     * 上传图片
     *
     * @param diaryPhoto
     * @return
     */
    @PostMapping("/uploadImg")
    @ApiOperation("上传图片")
    public ApiResp uploadImg(@RequestParam(value = "diaryPhoto", required = false) MultipartFile diaryPhoto) {
        String filePath = fileUtil.uploadFile(diaryPhoto);
        return ApiResp.retOk();
    }

    /**
     * 新增日记
     *
     * @param diaryPhoto
     * @param diary
     * @return
     */
    @PostMapping("/insertDiary")
    @ApiOperation("新增日记")
    public ApiResp<PdDiary> insertDiary(@RequestPart(name = "diaryPhoto", required = false) MultipartFile diaryPhoto, @Validated(Groups.Add.class) @RequestPart(name = "diary", required = true) PdDiary diary) {
        pdDiaryService.insertDiary(diary, diaryPhoto);
        return ApiResp.retOk(diary);
    }

    /**
     * 更新日记
     *
     * @param detailPhoto
     * @param diary
     * @return
     */
    @PostMapping("/updateDiary")
    @ApiOperation("更新日记")
    public ApiResp<PdDiary> updateDiary(@RequestPart(name = "detailPhoto", required = false) MultipartFile detailPhoto, @Validated(Groups.Update.class) @RequestPart(name = "diary", required = false) PdDiary diary) {
        pdDiaryService.updateDiary(diary, detailPhoto);
        PdDiary updateAfterDiary = pdDiaryService.findDiaryById(diary.getUserId(), diary.getDiaryId());
        return ApiResp.retOk(updateAfterDiary);
    }

    /**
     * 删除日记
     *
     * @param diaryId
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}/{diaryId}")
    @ApiOperation("删除日记")
    public ApiResp deleteDiary(@NotNull @PathVariable("diaryId") int diaryId,@NotNull @PathVariable("userId") int userId) {
        pdDiaryService.deleteDiary(diaryId, userId);
        return ApiResp.retOk();
    }

    /**
     * 查找日记
     *
     * @param diaryId
     * @param userId
     * @return
     */
    @GetMapping("/detail/{userId}/{diaryId}")
    @ApiOperation("查询评论")
    public ApiResp<PdDiary> findDiaryById(@PathVariable("diaryId") int diaryId, @PathVariable("userId") int userId) {
        PdDiary diary = pdDiaryService.findDiaryById(userId, diaryId);
        return ApiResp.retOk(diary);
    }

    /**
     * 查找指定用户的所有日记
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    @ApiOperation("查询指定用户的日记列表")
    public ApiResp<List<PdDiary>> findDiaryAll(@PathVariable("userId") int userId) {
        List<PdDiary> diaries = pdDiaryService.findDiaryAll(userId);
        return ApiResp.retOk(diaries);
    }

    /**
     * 根据条件模糊查找日记
     *
     * @param userId
     * @param global
     * @param start
     * @param size
     * @return
     */
    @GetMapping(value = {"/{userId}/{start}/{size}/{global}", "/{userId}/{start}/{size}"})
    @ApiOperation("查询指定用户的日记列表（模糊查询）")
    public ApiResp<PageInfo> findDiaryByGlobal(@PathVariable("userId") int userId, @PathVariable(value = "global", required = false) String global, @PathVariable("start") int start, @PathVariable("size") int size) {
        PageInfo pageInfo = pdDiaryService.findDiaryByGlobal(userId, global, start, size);
        return ApiResp.retOk(pageInfo);
    }

    /**
     * 获取日记相关的数量
     *
     * @param userId
     * @return
     */
    @GetMapping("/getDiaryNumber/{userId}")
    @ApiOperation("获取日记相关的数量")
    public ApiResp<Map<String, Object>> getDiaryNumber(@PathVariable("userId") int userId) {
        Map<String, Object> result = pdDiaryService.getDiaryNumber(userId);
        return ApiResp.retOk(result);
    }

    /**
     * 获取日记的图片信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/getImgInfo/{userId}")
    @ApiOperation("获取日记的图片信息")
    public ApiResp<Map<String, Object>> getImgInfo(@PathVariable("userId") int userId) {
        Map<String, Object> result = pdDiaryService.getImageInfo(userId);
        return ApiResp.retOk(result);
    }

    /**
     * 获取日记的地址信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/getAddressInfo/{userId}")
    @ApiOperation("获取日记的地址信息")
    public ApiResp<Map<String, Object>> getAddressInfo(@PathVariable("userId") int userId) {
        Map<String, Object> result = pdDiaryService.getAddressInfo(userId);
        return ApiResp.retOk(result);
    }

    /**
     * 获取日记的标签信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/getDiaryLabelInfo/{userId}")
    @ApiOperation("获取日记的标签信息")
    public ApiResp<Map<String, Object>> getDiaryLabelInfo(@PathVariable("userId") int userId) {
        Map<String, Object> result = pdDiaryService.getDiaryLabelInfo(userId);
        return ApiResp.retOk(result);
    }

}
