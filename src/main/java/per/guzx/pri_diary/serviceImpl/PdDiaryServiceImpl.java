package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdDiaryDao;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.exception.CommonException;
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
@Transactional
public class PdDiaryServiceImpl implements PdDiaryService {

    @Autowired
    private PdDiaryDao diaryDao;

    @Autowired
    private FileUtil fileUtil;

    @Override
    public int insertDiary(HttpServletRequest request, PdDiary diary, Part detailPhoto) {
        diary.setDiaryCreateTime(DateUtil.getTimeStamp());
        diary.setDiaryUpdateTime(DateUtil.getTimeStamp());
        String filePath = fileUtil.uploadFile(request, detailPhoto, diary);
        if (filePath == null) {
            throw new CommonException(ErrorEnum.FILE_UPLOAD);
        }
        diary.setDetailPhoto(filePath);
        int diaryResult = diaryDao.insertDiary(diary);
        return diaryResult;
    }

    @Override
    public int updateDiary(HttpServletRequest request, PdDiary diary, Part detailPhoto) {
        PdDiary remoteDiary = findDiaryOtherById(diary.getUserId(), diary.getDiaryId());
        if (!remoteDiary.equals(diary)) {
            String filePath = fileUtil.uploadFile(request, detailPhoto, diary);
            if (filePath == null) {
                throw new CommonException(ErrorEnum.FILE_UPLOAD);
            }
            diary.setDetailPhoto(filePath);
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
    public Map<String, Object> findDiaryById(HttpServletResponse response, int userId, int diaryId) {
        Map<String, Object> result = new HashMap<>();
        PdDiary diary = diaryDao.findDiaryById(userId, diaryId);
        result.put("diary", diary);
        if (diary != null) {
            File file = fileUtil.downloadFile(diary.getDetailPhoto());
            result.put("file", file);
            return result;
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
