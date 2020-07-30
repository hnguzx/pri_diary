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
import java.util.Objects;

@Service
@Slf4j
@Transactional
public class PdUserServiceImpl implements PdUserService {

    @Autowired
    private PdUserDao userDao;

    @Override
    public PdUser insertUser(PdUser user) throws CommonException {
        if (user.getUserId() == null) {
            user.setUserState(UserStateEnum.getStateEnumById(3));
        }
        int result = userDao.insertUser(user);
        if (result > 0) {
            return user;
        }
        throw new CommonException(ErrorEnum.USER_INSERT_FAIL);
    }

    @Override
    public PdUser login(PdUser user) {
        PdUser isUser = userDao.findUserByPhoneOrEmail(user);
        if (isUser != null) {
            return isUser;
        }
        throw new CommonException(ErrorEnum.USER_INFO_EXC);
    }

    @Override
    public int updateUser(PdUser user) {
        PdUser remoteUser = findUserById(user.getUserId());
        if (Objects.isNull(user)) {
            throw new CommonException(ErrorEnum.USER_INFO_EXC);
        }
        if (!user.equals(remoteUser)) {
            int result = userDao.updateUser(user);
            if (result > 0) {
                return result;
            }
            throw new CommonException(ErrorEnum.USER_INFO_EXC);
        } else {
            throw new CommonException(ErrorEnum.INFO_IS_LATEST);
        }

    }

    @Override
    public PdUser findUserById(int id) {
        PdUser user = userDao.findUserById(id);
        if (!Objects.isNull(user)) {
            return user;
        }
        throw new CommonException(ErrorEnum.USER_NOTFOUND);
    }

    @Override
    public PdUser deleteUser(int id) {
        PdUser user = findUserById(id);
        if (!Objects.isNull(user)) {
            int result = userDao.deleteUser(id);
            if (result > 0) {
                return user;
            }
            throw new CommonException(ErrorEnum.USER_INFO_EXC);
        }
        throw new CommonException(ErrorEnum.USER_NOTFOUND);
    }

    @Override
    public PdUser cancelUser(int id) {
        PdUser user = findUserById(id);
        if (!Objects.isNull(user)) {
            user.setUserState(UserStateEnum.getStateEnumById(2));
            updateUser(user);
            return user;
        }
        throw new CommonException(ErrorEnum.USER_NOTFOUND);
    }

    @Override
    public List<PdUser> findUsers(PdUser user, int start, int limit) {
        if (!Objects.isNull(user)) {
            List<PdUser> results = userDao.findUsers(user, start, limit);
            if (results.size() > 0) {
                return results;
            }
        }
        throw new CommonException(ErrorEnum.USER_NOTFOUND);
    }
}
