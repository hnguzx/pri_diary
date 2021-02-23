package per.guzx.priDiary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.priDiary.dao.PdFriendDao;
import per.guzx.priDiary.enumeration.ErrorEnum;
import per.guzx.priDiary.exception.ServiceException;
import per.guzx.priDiary.pojo.PageInfo;
import per.guzx.priDiary.pojo.PdFriend;
import per.guzx.priDiary.service.PdFriendService;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Transactional
@Service
public class PdFriendServiceImpl implements PdFriendService {

    @Autowired
    private PdFriendDao friendDao;

    @Autowired
    private PageInfo pageInfo;

    @Override
    public boolean save(PdFriend pdFriend) {
        // 查询是否已经添加过好友了
        PdFriend isAdd = friendDao.findById(pdFriend.getMyUserId(), pdFriend.getFriendUserId());
        if(isAdd!=null){
            throw new ServiceException(ErrorEnum.FRIEND_IS_ADDED);
        }
        pdFriend.setFriendApplyResult(1);
        int result1 = friendDao.save(pdFriend);
        // 查询对方是否已经同意添加好友
        PdFriend friend = friendDao.findById(pdFriend.getFriendUserId(), pdFriend.getMyUserId());
        if (friend != null && 1 == friend.getFriendApplyResult()) {
            return true;
        } else if(result1 > 0){
            return false;
        }else {
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
    public PdFriend findById(int myUserId, int friendUserId) {
        PdFriend friend = friendDao.findById(myUserId, friendUserId);
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
