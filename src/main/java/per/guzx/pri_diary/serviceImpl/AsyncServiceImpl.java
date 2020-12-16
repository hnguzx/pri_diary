package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import per.guzx.pri_diary.dao.PdDiaryDao;
import per.guzx.pri_diary.dao.PdUserDao;
import per.guzx.pri_diary.pojo.PdDiary;
import per.guzx.pri_diary.pojo.PdUser;
import per.guzx.pri_diary.service.AsyncService;
import per.guzx.pri_diary.service.PdDiaryService;
import per.guzx.pri_diary.service.PdUserService;
import per.guzx.pri_diary.tool.DateUtil;
import per.guzx.pri_diary.tool.FileUtil;

import java.util.List;

@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private PdUserDao userDao;


    @Autowired
    private PdDiaryDao diaryDao;

    @Autowired
    private DateUtil dateUtil;

    @Override
    @Async
    // 秒 分 时 天 月 星期 年（年可以不要）
    @Scheduled(cron = "0 0/1 * ? * *")
    public void deleteOverdueImg() {
        /*List<PdUser> users = userDao.getUserId();
        String photoWebPath = "";
        String photoRealPath = "";
        for (PdUser user : users) {
            List<PdDiary> diaries = diaryDao.findDiaryByUserId(user.getUserId());
            for (PdDiary diary : diaries) {
                photoWebPath = diary.getDetailPhoto();
                realDelete(photoWebPath);
            }
        }*/
    }

    @Override
    public void syncChatHistory() {

    }

    public void realDelete(String fileName){

    }
}
