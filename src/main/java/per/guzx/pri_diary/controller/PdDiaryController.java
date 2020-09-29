package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PageInfo;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.service.PdDiaryService;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

/**
 * 日记接口
 */
@RestController
@RequestMapping("/diary")
@Slf4j
public class PdDiaryController {
    @Autowired
    private PdDiaryService diaryService;

    /**
     * 上传图片
     * @param detailPhoto
     * @return
     */
    @PostMapping("/uploadImg")
    public ApiResp uploadImg(@RequestParam("detailPhoto") Part detailPhoto) {
        return ApiResp.retOk();
    }

    /**
     * 新增日记
     * @param detailPhoto
     * @param diary
     * @return
     */
    @PostMapping("/insertDiary")
    public ApiResp<PdDiary> insertDiary(@RequestPart(name = "detailPhoto", required = false) Part detailPhoto, @RequestPart(name = "diary", required = false) PdDiary diary) {
        diaryService.insertDiary(diary, detailPhoto);
        return ApiResp.retOk(diary);
    }

    /**
     * 更新日记
     * @param detailPhoto
     * @param diary
     * @return
     */
    @PatchMapping("/updateDiary")
    public ApiResp<PdDiary> updateDiary(@RequestPart(name = "detailPhoto", required = false) Part detailPhoto, @RequestPart(name = "diary", required = false) PdDiary diary) {
        diaryService.updateDiary(diary, detailPhoto);
        PdDiary updateAfterDiary = diaryService.findDiaryById(diary.getUserId(), diary.getDiaryId());
        return ApiResp.retOk(updateAfterDiary);
    }

    /**
     * 更新日记
     * @param diaryId
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}/{diaryId}")
    public ApiResp deleteDiary(@PathVariable("diaryId") int diaryId, @PathVariable("userId") int userId) {
        diaryService.deleteDiary(diaryId, userId);
        return ApiResp.retOk();
    }

    /**
     * 查找日记
     * @param diaryId
     * @param userId
     * @return
     */
    @GetMapping("/detail/{userId}/{diaryId}")
    public ApiResp<PdDiary> findDiaryById(@PathVariable("diaryId") int diaryId, @PathVariable("userId") int userId) {
        PdDiary diary = diaryService.findDiaryById(userId, diaryId);
        return ApiResp.retOk(diary);
    }

    /**
     * 查找指定用户的所有日记
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ApiResp<List<PdDiary>> findDiaryAll(@PathVariable("userId") int userId) {
        List<PdDiary> diaries = diaryService.findDiaryAll(userId);
        return ApiResp.retOk(diaries);
    }

    /**
     * 根据条件模糊查找日记
     * @param userId
     * @param global
     * @param start
     * @param size
     * @return
     */
    @GetMapping(value = {"/{userId}/{start}/{size}/{global}", "/{userId}/{start}/{size}"})
    public ApiResp<PageInfo> findDiaryByGlobal(@PathVariable("userId") int userId, @PathVariable(value = "global", required = false) String global, @PathVariable("start") int start, @PathVariable("size") int size) {
        PageInfo pageInfo = diaryService.findDiaryByGlobal(userId, global, start, size);
        return ApiResp.retOk(pageInfo);
    }

    /**
     * 获取日记相关的数量
     * @param userId
     * @return
     */
    @GetMapping("/getDiaryNumber/{userId}")
    public ApiResp<Map<String,Object>> getDiaryNumber(@PathVariable("userId") int userId) {
        Map<String, Object> result = diaryService.getDiaryNumber(userId);
        return ApiResp.retOk(result);
    }

    /**
     * 获取日记的图片信息
     * @param userId
     * @return
     */
    @GetMapping("/getImgInfo/{userId}")
    public ApiResp<Map<String,Object>> getImgInfo(@PathVariable("userId") int userId) {
        Map<String, Object> result = diaryService.getImageInfo(userId);
        return ApiResp.retOk(result);
    }

    /**
     * 获取日记的地址信息
     * @param userId
     * @return
     */
    @GetMapping("/getAddressInfo/{userId}")
    public ApiResp<Map<String,Object>> getAddressInfo(@PathVariable("userId") int userId) {
        Map<String, Object> result = diaryService.getAddressInfo(userId);
        return ApiResp.retOk(result);
    }

    /**
     * 获取日记的标签信息
     * @param userId
     * @return
     */
    @GetMapping("/getDiaryLabelInfo/{userId}")
    public ApiResp<Map<String,Object>> getDiaryLabelInfo(@PathVariable("userId") int userId){
        Map<String, Object> result = diaryService.getDiaryLabelInfo(userId);
        return ApiResp.retOk(result);
    }

}
