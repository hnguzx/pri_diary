package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdMessageDao;
import per.guzx.pri_diary.pojo.PdMessage;
import per.guzx.pri_diary.service.PdMessageService;
import per.guzx.pri_diary.tool.DateUtil;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Service
@Transactional
public class PdMessageServiceImpl implements PdMessageService {

    @Autowired
    private PdMessageDao messageDao;

    @Autowired
    private DateUtil dateUtil;

    @Override
    public boolean sendMsg(PdMessage pdMessage) {
        pdMessage.setMsgCreateTime(dateUtil.getTimeStamp());
        pdMessage.setMsgIsReade(false);
        int result = messageDao.save(pdMessage);
        return result > 0;
    }

    @Override
    public List<PdMessage> findAll() {
        return null;
    }

    @Override
    public PdMessage findById(Integer id) {
        return null;
    }
}
