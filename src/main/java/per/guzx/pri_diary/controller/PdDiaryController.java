package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.service.PdDiaryService;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/diary")
@Slf4j
public class PdDiaryController {
    @Autowired
    private PdDiaryService diaryService;

    @PostMapping("/uploadImg")
    public ApiResp uploadImg(@RequestParam("detailPhoto") Part detailPhoto) {
        return ApiResp.retOk();
    }

    @PostMapping("/insertDiary")
    public ApiResp insertDiary(@RequestPart(name = "detailPhoto", required = false) Part detailPhoto, @RequestPart(name = "diary", required = false) PdDiary diary) {
        diaryService.insertDiary(diary, detailPhoto);
        return ApiResp.retOk(diary);
    }

    @PatchMapping("/updateDiary")
    public ApiResp updateDiary(@RequestPart(name = "detailPhoto", required = false) Part detailPhoto, @RequestPart(name = "diary", required = false) PdDiary diary) {
        diaryService.updateDiary(diary, detailPhoto);
        PdDiary updateAfterDiary = diaryService.findDiaryById(diary.getUserId(), diary.getDiaryId());
        return ApiResp.retOk(updateAfterDiary);
    }

    @DeleteMapping("/{userId}/{diaryId}")
    public ApiResp deleteDiary(@PathVariable("diaryId") int diaryId, @PathVariable("userId") int userId) {
        diaryService.deleteDiary(diaryId, userId);
        return ApiResp.retOk();
    }

    @GetMapping("/detail/{userId}/{diaryId}")
    public ApiResp findDiaryById(@PathVariable("diaryId") int diaryId, @PathVariable("userId") int userId) {
        PdDiary diary = diaryService.findDiaryById(userId, diaryId);
        return ApiResp.retOk(diary);
    }

    @GetMapping("/{userId}")
    public ApiResp findDiaryAll(@PathVariable("userId") int userId) {
        List<PdDiary> diaries = diaryService.findDiaryAll(userId);
        return ApiResp.retOk(diaries);
    }

    @GetMapping(value = {"/{userId}/{start}/{size}/{global}", "/{userId}/{start}/{size}"})
    public ApiResp findDiaryByGlobal(@PathVariable("userId") int userId, @PathVariable(value = "global", required = false) String global, @PathVariable("start") int start, @PathVariable("size") int size) {
        List<PdDiary> diaries = diaryService.findDiaryByGlobal(userId, global, start, size);
        return ApiResp.retOk(diaries);
    }

    @GetMapping("/getDiaryNumber/{userId}")
    public ApiResp getDiaryNumber(@PathVariable("userId") int userId) {
        Map<String, Object> result = diaryService.getDiaryNumber(userId);
        return ApiResp.retOk(result);
    }

    @GetMapping("/getImgInfo/{userId}")
    public ApiResp getImgInfo(@PathVariable("userId") int userId) {
        Map<String, Object> result = diaryService.getImageInfo(userId);
        return ApiResp.retOk(result);
    }

    @GetMapping("/getAddressInfo/{userId}")
    public ApiResp getAddressInfo(@PathVariable("userId") int userId) {
        Map<String, Object> result = diaryService.getAddressInfo(userId);
        return ApiResp.retOk(result);
    }

    @GetMapping("/getDiaryLabelInfo/{userId}")
    public ApiResp getDiaryLabelInfo(@PathVariable("userId") int userId){
        Map<String, Object> result = diaryService.getDiaryLabelInfo(userId);
        return ApiResp.retOk(result);
    }

}
