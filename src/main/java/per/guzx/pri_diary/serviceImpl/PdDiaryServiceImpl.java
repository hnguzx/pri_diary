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
    public PdDiary updateDiary(PdDiary diary) {
        return null;
    }

    @Override
    public int deleteDiary(int diaryId) {
        return 0;
    }

    @Override
    public List<PdDiary> findDiaryAll(int userId) {
        return null;
    }

    @Override
    public List<PdDiary> findDiaryByGlobal(int userId, String global) {
        return null;
    }
}
