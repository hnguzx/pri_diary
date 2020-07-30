package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.exception.CommonException;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.pojo.PdDiaryDetail;
import per.guzx.pri_diary.service.PdDiaryService;
import per.guzx.pri_diary.tool.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/diary")
@Slf4j
public class PdDiaryController {
    @Autowired
    private PdDiaryService diaryService;

    @PostMapping("/insertDiary")
    public ApiResp insertDiary(@RequestPart(name = "detailPhoto", required = false) Part detailPhoto, @RequestPart(name = "diary", required = false) PdDiary diary, HttpServletRequest request) {
        int result = diaryService.insertDiary(request, diary, detailPhoto);
        if (result > 0) {
            return ApiResp.retOk();
        }
        throw new CommonException(ErrorEnum.DATA_EXCEPTION);
    }

    @PatchMapping("/updateDiary")
    public ApiResp updateDiary(@RequestPart(name = "detailPhoto", required = false) Part detailPhoto, @RequestPart(name = "diary", required = false) PdDiary diary, HttpServletResponse response, HttpServletRequest request) {
        int result = diaryService.updateDiary(request, diary, detailPhoto);
        if (result > 0) {
            Map<String, Object> diaryAll = diaryService.findDiaryById(response, diary.getUserId(), diary.getDiaryId());
            PdDiary updateDiaryAfter = (PdDiary) diaryAll.get("diary");
            return ApiResp.retOk(updateDiaryAfter);
        }
        return ApiResp.retFail(ErrorEnum.INFO_IS_LATEST);
    }

    @DeleteMapping("/{userId}/{diaryId}")
    public ApiResp deleteDiary(@PathVariable("diaryId") int diaryId, @PathVariable("userId") int userId) {
        int result = diaryService.deleteDiary(diaryId, userId);
        if (result > 0) {
            return ApiResp.retOk();
        }
        return ApiResp.retFail(ErrorEnum.DIARY_NOTFOUND);
    }

    @GetMapping("/detail/{userId}/{diaryId}")
    public ApiResp findDiaryById(@PathVariable("diaryId") int diaryId, @PathVariable("userId") int userId, HttpServletResponse response) {
        Map<String, Object> diaryAll = diaryService.findDiaryById(response, userId, diaryId);
        if (diaryAll != null) {
            return ApiResp.retOk(diaryAll);
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
