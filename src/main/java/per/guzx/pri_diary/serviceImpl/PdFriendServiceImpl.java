package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdFriendDao;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.exception.ServiceException;
import per.guzx.pri_diary.pojo.PdFriend;
import per.guzx.pri_diary.service.PdFriendService;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Service
@Transactional
public class PdFriendServiceImpl implements PdFriendService {

    @Autowired
    private PdFriendDao friendDao;

    @Override
    public PdFriend save(PdFriend pdFriend) {
        int result = friendDao.save(pdFriend);
        if (result > 0) {
            return pdFriend;
        }
        throw new ServiceException(ErrorEnum.USER_INFO_EXC);
    }

    @Override
    public int deleteById(Integer id) {
        int result = friendDao.deleteById(id);
        return result;
    }

    @Override
    public PdFriend update(PdFriend pdFriend) {
        int result = friendDao.update(pdFriend);
        if (result>0){
            return pdFriend;
        }
        return null;
    }

    @Override
    public PdFriend findById(Integer id) {
        PdFriend friend = friendDao.findById(id);
        return friend;
    }

    @Override
    public PdFriend findByFriend(PdFriend friend) {
        return null;
    }

    @Override
    public List<PdFriend> findAll() {
        return null;
    }
}
