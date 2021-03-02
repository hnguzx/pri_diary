package per.guzx.priDiary.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.priDiary.dao.PdDiaryDao;
import per.guzx.priDiary.enumeration.ErrorEnum;
import per.guzx.priDiary.exception.ServiceException;
import per.guzx.priDiary.pojo.PdDiary;
import per.guzx.priDiary.service.PdDiaryService;
import per.guzx.priDiary.tool.DateUtil;
import per.guzx.priDiary.tool.FileUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.Part;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class PdDiaryServiceImpl implements PdDiaryService {

    @Resource
    private PdDiaryDao diaryDao;

    @Resource
    private FileUtil fileUtil;

    @Resource
    private DateUtil dateUtil;

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

        PageHelper.startPage(start,size);
        Example example = new Example(PdDiary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("diaryTitle",global);
        criteria.andLike("diaryWeather",global);
        criteria.andLike("diaryMood",global);
        criteria.andLike("diaryEvent",global);
        criteria.andLike("diaryLocation",global);
        criteria.andLike("diary_content",global);
        List<PdDiary> diaries = diaryDao.selectByExample(example);
        PageInfo pageInfo = new PageInfo(diaries);
        return pageInfo;
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
