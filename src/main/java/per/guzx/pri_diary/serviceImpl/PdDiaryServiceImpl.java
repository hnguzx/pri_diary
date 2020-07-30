package per.guzx.pri_diary.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdDiaryDao;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.exception.CommonException;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.pojo.PdDiaryDetail;
import per.guzx.pri_diary.service.PdDiaryService;
import per.guzx.pri_diary.tool.DateUtil;
import per.guzx.pri_diary.tool.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class PdDiaryServiceImpl implements PdDiaryService {

    @Autowired
    private PdDiaryDao diaryDao;

    @Autowired
    private FileUtil fileUtil;

    @Override
    public int insertDiary(PdDiary diary, Part detailPhoto) {
        diary.setDiaryCreateTime(DateUtil.getTimeStamp());
        diary.setDiaryUpdateTime(DateUtil.getTimeStamp());
        if (detailPhoto.getSubmittedFileName() != null) {
            String filePath = fileUtil.uploadFile(detailPhoto, diary);
            diary.setDetailPhoto(filePath);
        }
        int diaryResult = diaryDao.insertDiary(diary);
        if (diaryResult > 0) {
            return diaryResult;
        }
        throw new CommonException(ErrorEnum.DATA_EXCEPTION);
    }

    @Override
    public int updateDiary(PdDiary diary, Part detailPhoto) {
        PdDiary remoteDiary = findDiaryOtherById(diary.getUserId(), diary.getDiaryId());
        if (!remoteDiary.equals(diary)) {
            if (detailPhoto.getSubmittedFileName() != null) {
                String filePath = fileUtil.uploadFile(detailPhoto, diary);
                diary.setDetailPhoto(filePath);
            }
            diary.setDiaryUpdateTime(DateUtil.getTimeStamp());
            int result = diaryDao.updateDiary(diary);
            return result;
        }
        throw new CommonException(ErrorEnum.UPDATE_INFO_FAIL);
    }

    @Override
    public int deleteDiary(int diaryId, int userId) {
        int diaryResult = diaryDao.deleteDiary(userId, diaryId);
        if (diaryResult > 0) {
            return diaryResult;
        }
        throw new CommonException(ErrorEnum.DIARY_NOTFOUND);

    }

    @Override
    public PdDiary findDiaryById(int userId, int diaryId) {
        PdDiary result = diaryDao.findDiaryById(userId, diaryId);
        if (result != null) {
            return result;
        }
        throw new CommonException(ErrorEnum.DIARY_NOTFOUND);
    }

    @Override
    public PdDiary findDiaryOtherById(int userId, int diaryId) {
        PdDiary diary = diaryDao.findDiaryOtherById(userId, diaryId);
        if (diary != null) {
            return diary;
        }
        throw new CommonException(ErrorEnum.DIARY_NOTFOUND);
    }

    @Override
    public List<PdDiary> findDiaryAll(int userId) {
        List<PdDiary> diaries = diaryDao.findDiaryAll(userId);
        if (diaries.size() > 0) {
            return diaries;
        }
        throw new CommonException(ErrorEnum.DIARY_NOTFOUND);
    }

    @Override
    public List<PdDiary> findDiaryByGlobal(int userId, String global) {
        List<PdDiary> diaries = diaryDao.findDiaryByGlobal(userId, global);
        if (diaries.size() > 0) {
            return diaries;
        }
        throw new CommonException(ErrorEnum.DIARY_NOTFOUND);
    }
}
