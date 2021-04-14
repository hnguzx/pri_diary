package per.guzx.priDiary.serviceImpl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import per.guzx.priDiary.dao.PdDiaryDao;
import per.guzx.priDiary.dao.PdUserDao;
import per.guzx.priDiary.service.AsyncService;
import per.guzx.priDiary.utils.DateUtil;
import per.guzx.priDiary.utils.FileUtil;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Resource
    private FileUtil fileUtil;

    @Resource
    private PdUserDao userDao;


    @Resource
    private PdDiaryDao diaryDao;

    @Resource
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
