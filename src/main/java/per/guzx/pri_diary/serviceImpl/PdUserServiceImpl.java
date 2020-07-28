package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdUserDao;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.enumeration.StateEnum;
import per.guzx.pri_diary.exception.CommonException;
import per.guzx.pri_diary.pojo.PdUser;
import per.guzx.pri_diary.service.PdUserService;

import java.util.List;

@Service
@Transactional
public class PdUserServiceImpl implements PdUserService {

    @Autowired
    private PdUserDao userDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public PdUser insertUser(PdUser user) throws CommonException {
        if (user.getUserId() == null) {
            user.setUserState(StateEnum.getStateEnumById(3));
        }
        int result = userDao.insertUser(user);
        if (result > 0) {
            return user;
        }else {
            throw new CommonException("新增用户失败",ErrorEnum.DATA_EXCEPTION);
        }

    }

    @Override
    public PdUser updateUser(PdUser user) {
        int result = userDao.updateUser(user);
        if (result > 0) {
            return user;
        }
        return null;
    }

    @Override
    public PdUser findUserById(int id) {
        PdUser user = userDao.findUserById(id);
        if (user != null) {
            return user;
        }
        return null;
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
        return null;
    }

    @Override
    public PdUser cancleUser(int id) {
        PdUser user = findUserById(id);
        if (user != null) {
            user.setUserState(StateEnum.getStateEnumById(2));
            updateUser(user);
            return user;
        }
        return null;
    }

    @Override
    public List<PdUser> findUsers(PdUser user, int start, int limit) {
        if (user != null) {
            List<PdUser> results = userDao.findUsers(user, start, limit);
            if (results.size() > 0) {
                return results;
            }
        }
        return null;
    }
}
