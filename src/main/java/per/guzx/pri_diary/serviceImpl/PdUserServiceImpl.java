package per.guzx.pri_diary.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdUserDao;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.enumeration.UserStateEnum;
import per.guzx.pri_diary.exception.CommonException;
import per.guzx.pri_diary.pojo.PdUser;
import per.guzx.pri_diary.service.PdUserService;

import java.util.List;

@Service
@Slf4j
@Transactional
public class PdUserServiceImpl implements PdUserService {

    @Autowired
    private PdUserDao userDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public PdUser insertUser(PdUser user) throws CommonException {
        if (user.getUserId() == null) {
            user.setUserState(UserStateEnum.getStateEnumById(3));
        }
        int result = userDao.insertUser(user);
        if (result > 0) {
            return user;
        }
        throw new CommonException(ErrorEnum.DATA_EXCEPTION);


    }

    @Override
    public int updateUser(PdUser user) {
        PdUser remoteUser = findUserById(user.getUserId());
        if (!user.equals(remoteUser)) {
            int result = userDao.updateUser(user);
            return result;
        }
        return 0;
    }

    @Override
    public PdUser findUserById(int id) {
        PdUser user = userDao.findUserById(id);
        if (user != null) {
            return user;
        }
        throw new CommonException(ErrorEnum.USER_NOTFOUND);
    }

    @Override
    public PdUser deleteUser(int id) {
        PdUser user = findUserById(id);
        if (user != null) {
            int result = userDao.deleteUser(id);
            if (result > 0) {
                return user;
            }
        }
        throw new CommonException(ErrorEnum.USER_NOTFOUND);
    }

    @Override
    public PdUser cancelUser(int id) {
        PdUser user = findUserById(id);
        if (user != null) {
            user.setUserState(UserStateEnum.getStateEnumById(2));
            updateUser(user);
            return user;
        }
        throw new CommonException(ErrorEnum.USER_NOTFOUND);
    }

    @Override
    public List<PdUser> findUsers(PdUser user, int start, int limit) {
        if (user != null) {
            List<PdUser> results = userDao.findUsers(user, start, limit);
            if (results.size() > 0) {
                return results;
            }
        }
        throw new CommonException(ErrorEnum.USER_NOTFOUND);
    }
}
