package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.service.PdDiaryService;
import per.guzx.pri_diary.tool.DateUtil;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/diary")
@Slf4j
public class PdDiaryController {
    @Autowired
    private PdDiaryService diaryService;

    @PostMapping("/insertDiary")
    public ApiResp insertDiary(@RequestBody PdDiary diary) {
        diary.setDiaryCreateTime(DateUtil.getTimeStamp());
        diary.setDiaryUpdateTime(DateUtil.getTimeStamp());
        PdDiary newDiary = diaryService.insertDiary(diary);
        if (newDiary != null) {
            return ApiResp.retOk(newDiary);
        }
        return ApiResp.retFail(ErrorEnum.DATA_EXCEPTION);
    }

    @PatchMapping("/updateDiary")
    public ApiResp updateDiary(@RequestBody PdDiary diary) {
        int result = diaryService.updateDiary(diary);
        if (result > 0) {
            PdDiary updateDiaryAfter = diaryService.findDiaryById(diary.getUserId(), diary.getDiaryId());
            return ApiResp.retOk(updateDiaryAfter);
        }
        return ApiResp.retFail(ErrorEnum.UPDATE_INFO_FAIL);
    }

    @DeleteMapping("/{userId}/{diaryId}")
    public ApiResp deleteDiary(@PathVariable("diaryId") int diaryId, @PathVariable("userId") int userId) {
        int result = diaryService.deleteDiary(diaryId, userId);
        if (result > 0) {
            return ApiResp.retOk();
        }
        return ApiResp.retFail(ErrorEnum.DIARY_NOTFOUND);
    }

    @GetMapping("/{userId}")
    public ApiResp findDiaryAll(@PathVariable("userId") int userId) {
        List<PdDiary> diaries = diaryService.findDiaryAll(userId);
        if (diaries.size() > 0) {
            return ApiResp.retOk(diaries);
        }
        return ApiResp.retFail(ErrorEnum.DIARY_NOTFOUND);
    }

    @GetMapping("/{userId}/{global}")
    public ApiResp findDiaryByGlobal(@PathVariable("userId") int userId, @PathVariable("global") String global) {
        List<PdDiary> diaries = diaryService.findDiaryByGlobal(userId, global);
        if (diaries.size() > 0) {
            return ApiResp.retOk(diaries);
        }
        return ApiResp.retFail(ErrorEnum.DIARY_NOTFOUND);
    }

}
