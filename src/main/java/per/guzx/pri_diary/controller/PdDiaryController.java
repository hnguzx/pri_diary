package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.service.PdDiaryService;
import per.guzx.pri_diary.tool.DateUtil;

import java.util.Date;

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
        return ApiResp.retFail(ErrorEnum.CANCELLATION);
    }
}
