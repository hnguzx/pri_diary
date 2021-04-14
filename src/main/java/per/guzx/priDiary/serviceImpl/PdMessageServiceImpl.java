package per.guzx.priDiary.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.priDiary.dao.PdMessageDao;
import per.guzx.priDiary.pojo.PdMessage;
import per.guzx.priDiary.pojo.PdUser;
import per.guzx.priDiary.service.AsyncService;
import per.guzx.priDiary.service.PdMessageService;
import per.guzx.priDiary.service.PdUserService;
import per.guzx.priDiary.utils.DateUtil;
import per.guzx.priDiary.utils.NoticeUtil;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * @author Guzx
 * @date 2020/09/07
 */
@Transactional
@Service
public class PdMessageServiceImpl implements PdMessageService {

    @Resource
    private PdMessageDao messageDao;

    @Resource
    private DateUtil dateUtil;

    @Resource
    private PdUserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AsyncService asyncService;

    @Resource
    private NoticeUtil noticeUtil;

    @Override
    public boolean sendMsg(PdMessage message) {
        message.setMsgCreateTime(dateUtil.getTimeStamp());
        message.setMsgIsReaded(false);

        PdUser user = userService.findUserById(message.getMsgReceiver());
        // 判断接收用户是否在线，不在线的话先缓存，然后入库
        boolean isOnLine = redisTemplate.opsForHash().get("loggedUser", user.getUsername()) != null;
        if (isOnLine) {
            message.setMsgIsReaded(true);
            //  发送到用户和监听地址
            noticeUtil.sendTxtToUser(message.getMsgReceiver(), "/queue/customer", message);
        } else {
            // 保存在redis中
            redisTemplate.opsForList().rightPush(user.getUsername(), JSONArray.toJSON(message).toString());
            // 定时同步到数据库中，将redis中的清除
            asyncService.syncChatHistory();
        }
        int result = messageDao.save(message);
        return result > 0;
    }

    @Override
    public PageInfo<List<PdMessage>> findAll() {
        return null;
    }

    @Override
    public PdMessage findById(Integer id) {
        return null;
    }
}
