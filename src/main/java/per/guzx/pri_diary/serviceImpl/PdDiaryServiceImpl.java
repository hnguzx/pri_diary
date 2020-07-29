package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdDiaryDao;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.pojo.PdDiaryDetail;
import per.guzx.pri_diary.service.PdDiaryService;
import per.guzx.pri_diary.tool.DateUtil;

import java.util.List;

@Service
@Transactional
public class PdDiaryServiceImpl implements PdDiaryService {

    @Autowired
    private PdDiaryDao diaryDao;

    @Override
    public int insertDiary(PdDiary diary) {
        diary.setDiaryCreateTime(DateUtil.getTimeStamp());
        diary.setDiaryUpdateTime(DateUtil.getTimeStamp());
        int diaryResult = diaryDao.insertDiary(diary);
        return diaryResult;
    }

    @Override
    public int updateDiary(PdDiary diary) {
        PdDiary remoteDiary = findDiaryOtherById(diary.getUserId(), diary.getDiaryId());
        if (!remoteDiary.equals(diary)) {
            diary.setDiaryUpdateTime(DateUtil.getTimeStamp());
            int result = diaryDao.updateDiary(diary);
            return result;
        }
        return 0;
    }

    @Override
    public int deleteDiary(int diaryId, int userId) {
        int diaryResult = diaryDao.deleteDiary(userId, diaryId);
        return diaryResult;
    }

    @Override
    public PdDiary findDiaryById(int userId, int diaryId) {
        PdDiary diary = diaryDao.findDiaryById(userId, diaryId);
        if (diary != null) {
            return diary;
        }
        return null;
    }

    @Override
    public PdDiary findDiaryOtherById(int userId, int diaryId) {
        PdDiary diary = diaryDao.findDiaryOtherById(userId, diaryId);
        if (diary != null) {
            return diary;
        }
        return null;
    }

    @Override
    public List<PdDiary> findDiaryAll(int userId) {
        List<PdDiary> diaries = diaryDao.findDiaryAll(userId);
        if (diaries.size() > 0) {
            return diaries;
        }
        return null;
    }

    @Override
    public List<PdDiary> findDiaryByGlobal(int userId, String global) {
        List<PdDiary> diaries = diaryDao.findDiaryByGlobal(userId, global);
        if (diaries.size() > 0) {
            return diaries;
        }
        return null;
    }
}
