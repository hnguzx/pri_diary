package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdDiaryDao;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.service.PdDiaryService;

import java.util.List;

@Service
@Transactional
public class PdDiaryServiceImpl implements PdDiaryService {

    @Autowired
    private PdDiaryDao diaryDao;

    @Override
    public PdDiary insertDiary(PdDiary diary) {
        int result = diaryDao.insertDiary(diary);
        if (result > 0) {
            return diary;
        }
        return null;
    }

    @Override
    public int updateDiary(PdDiary diary) {
        int result = diaryDao.updateDiary(diary);
        return result;
    }

    @Override
    public int deleteDiary(int diaryId, int userId) {
        int result = diaryDao.deleteDiary(userId, diaryId);
        return result;
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
