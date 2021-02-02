package per.guzx.pri_diary.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdDiaryDao;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.exception.ServiceException;
import per.guzx.pri_diary.pojo.PageInfo;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.service.PdDiaryService;
import per.guzx.pri_diary.tool.DateUtil;
import per.guzx.pri_diary.tool.FileUtil;

import javax.servlet.http.Part;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Transactional
public class PdDiaryServiceImpl implements PdDiaryService {

    @Autowired
    private PdDiaryDao diaryDao;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private PageInfo pageInfo;

    @Override
    public int insertDiary(PdDiary diary, Part detailPhoto) {
        diary.setDiaryCreateTime(dateUtil.getTimeStamp());
        diary.setDiaryUpdateTime(dateUtil.getTimeStamp());
        diary.setDiaryCreateDay(dateUtil.getDateStamp());
        if (detailPhoto != null && detailPhoto.getSubmittedFileName() != null) {
            String filePath = fileUtil.uploadFile(detailPhoto, diary);
            diary.setDiaryPhoto(filePath);
        }
        int diaryResult = diaryDao.insertDiary(diary);
        if (diaryResult > 0) {
            return diaryResult;
        }
        throw new ServiceException(ErrorEnum.DATA_EXCEPTION);
    }

    @Override
    public int updateDiary(PdDiary diary, Part detailPhoto) {
        PdDiary remoteDiary = findDiaryOtherById(diary.getUserId(), diary.getDiaryId());
        if (!remoteDiary.equals(diary)) {
            diary.setDiaryUpdateTime(dateUtil.getTimeStamp());
            if (detailPhoto.getSubmittedFileName() != null) {
                String filePath = fileUtil.uploadFile(detailPhoto, diary);
                diary.setDiaryPhoto(filePath);
            }
            int result = diaryDao.updateDiary(diary);
            return result;
        }
        throw new ServiceException(ErrorEnum.UPDATE_INFO_FAIL);
    }

    @Override
    public int deleteDiary(int diaryId, int userId) {
        int diaryResult = diaryDao.deleteDiary(userId, diaryId);
        if (diaryResult > 0) {
            return diaryResult;
        }
        throw new ServiceException(ErrorEnum.DIARY_NOTFOUND);

    }

    @Override
    public PdDiary findDiaryById(int userId, int diaryId) {
        PdDiary result = diaryDao.findDiaryById(userId, diaryId);
        if (result != null) {
            return result;
        }
        throw new ServiceException(ErrorEnum.DIARY_NOTFOUND);
    }

    @Override
    public PdDiary findDiaryOtherById(int userId, int diaryId) {
        PdDiary diary = diaryDao.findDiaryOtherById(userId, diaryId);
        if (diary != null) {
            return diary;
        }
        throw new ServiceException(ErrorEnum.DIARY_NOTFOUND);
    }

    @Override
    public List<PdDiary> findDiaryAll(int userId) {
        List<PdDiary> diaries = diaryDao.findDiaryAll(userId);
        if (diaries.size() > 0) {
            return diaries;
        }
        throw new ServiceException(ErrorEnum.DIARY_NOTFOUND);
    }

    @Override
    public PageInfo findDiaryByGlobal(int userId, String global, int start, int size) {
        List<PdDiary> diaries = diaryDao.findDiaryByGlobal(userId, global, start, size);
        if (diaries.size() > 0) {
            pageInfo.setCurrentPage(start);
            pageInfo.setPageSize(size);
            pageInfo.setTotal(diaries.size());
            pageInfo.setResult(diaries);
            return pageInfo;
        }
        throw new ServiceException(ErrorEnum.DIARY_NOTFOUND);
    }

    @Override
    public Map<String, Object> getDiaryNumber(int userId) {
        Map<String, Object> diaryNumber = diaryDao.getDiaryCountAndDiaryDay(userId);
        int DiaryAndAddressTotal = diaryDao.getDiaryAndAddressCount(userId);

        Map dateMap = dateUtil.getWeekDate();
        int weekRecordTotal = diaryDao.getWeekDiaryCount(userId, (String) dateMap.get("mondayDate"), (String) dateMap.get("sundayDate"));

        diaryNumber.put("weekRecordTotal", weekRecordTotal);
        diaryNumber.put("DiaryAndAddressTotal", DiaryAndAddressTotal);

        return diaryNumber;
    }

    @Override
    public Map<String, Object> getImageInfo(int userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, String>> imgInfo = diaryDao.getDiaryImgInfo(userId);
        result.put("imgInfo", imgInfo);
        return result;
    }

    @Override
    public Map<String, Object> getAddressInfo(int userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, String>> addressInfo = diaryDao.getDiaryAddressInfo(userId);
        result.put("addressInfo", addressInfo);
        return result;
    }

    @Override
    public Map<String, Object> getDiaryLabelInfo(int userId) {
        Map<String, Object> result = new HashMap<String, Object>();

        List<Map<String, String>> weatherList = diaryDao.getWeatherTimes(userId);
        if (weatherList.size() > 6) {
            weatherList = weatherList.subList(0, 6);
        }
        List<Map<String, String>> moodList = diaryDao.getMoodTimes(userId);
        if (moodList.size() > 6) {
            moodList = moodList.subList(0, 6);
        }
        List<Map<String, String>> eventList = diaryDao.getEventTimes(userId);
        if (eventList.size() > 6) {
            eventList = eventList.subList(0, 6);
        }
        result.put("weatherList", weatherList);
        result.put("moodList", moodList);
        result.put("eventList", eventList);
        return result;
    }

}
