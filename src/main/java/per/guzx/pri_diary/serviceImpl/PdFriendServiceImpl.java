package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdFriendDao;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.exception.ServiceException;
import per.guzx.pri_diary.pojo.PageInfo;
import per.guzx.pri_diary.pojo.PdFriend;
import per.guzx.pri_diary.service.PdFriendService;
import per.guzx.pri_diary.service.PdMessageService;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Service
@Transactional
public class PdFriendServiceImpl implements PdFriendService {

    @Autowired
    private PdFriendDao friendDao;

    @Autowired
    private PageInfo pageInfo;

    @Override
    public PdFriend save(PdFriend pdFriend) {
        pdFriend.setFriendApplyResult(1);
        int result1 = friendDao.save(pdFriend);
//        int result2 = 0;
//        if (result1 > 0) {
//            PdFriend friend2 = new PdFriend();
//            friend2.setMyUserId(pdFriend.getFriendUserId());
//            friend2.setMyEmail(pdFriend.getFriendEmail());
//            friend2.setMyPhone(pdFriend.getFriendPhone());
//            friend2.setFriendUserId(pdFriend.getMyUserId());
//            friend2.setFriendEmail(pdFriend.getMyEmail());
//            friend2.setFriendPhone(pdFriend.getMyPhone());
//            result2 = friendDao.save(friend2);
//        }
        if (result1 > 0) {
            return pdFriend;
        } else {
            throw new ServiceException(ErrorEnum.USER_INFO_EXC);
        }
    }

    @Override
    public int deleteById(Integer id) {
        int result = friendDao.deleteById(id);
        return result;
    }

    @Override
    public PdFriend update(PdFriend pdFriend) {
        int result = friendDao.update(pdFriend);
        if (result > 0) {
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
    public PageInfo findFriendByInfo(int myUserId, int start, int size, String global) {
        List<PdFriend> friends = friendDao.findFriendByInfo(myUserId, start, size, global);
        pageInfo.setCurrentPage(start);
        pageInfo.setPageSize(size);
        pageInfo.setTotal(friends.size());
        pageInfo.setResult(friends);
        return pageInfo;
    }
}
