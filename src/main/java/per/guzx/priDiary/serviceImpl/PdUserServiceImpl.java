package per.guzx.priDiary.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.priDiary.dao.PdUserDao;
import per.guzx.priDiary.enumeration.ErrorEnum;
import per.guzx.priDiary.enumeration.SexEnum;
import per.guzx.priDiary.enumeration.UserStateEnum;
import per.guzx.priDiary.exception.ServiceException;
import per.guzx.priDiary.pojo.PdRole;
import per.guzx.priDiary.pojo.PdUser;
import per.guzx.priDiary.service.PdUserService;
import per.guzx.priDiary.tool.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@Transactional
@Service
public class PdUserServiceImpl implements PdUserService, UserDetailsService {

    @Resource
    private PdUserDao userDao;

    @Resource
    private DateUtil dateUtil;

    @Resource
    private MathUtil mathUtil;

    @Resource
    private AddressUtil addressUtil;

    @Resource
    private PasswordEncodeUtil encodeUtil;

    @Override
    public PdUser insertUser(PdUser user) throws ServiceException {
        user.setUserState(UserStateEnum.getStateEnumById(3));
        user.setUserCreateTime(dateUtil.getTimeStamp());
        user.setUserUpdateTime(dateUtil.getTimeStamp());
        user.setUserPassword(encodeUtil.passwordEncode(user.getPassword()));
        int count = userDao.findEmailOrPhone(user);
        if (count > 0) {
            throw new ServiceException(ErrorEnum.USER_INFO_EXIST);
        }
        user.setUserHead(generateHeadImage(user.getUserSex()));
        int result = userDao.insertUser(user);
        if (result > 0) {
            return user;
        }
        throw new ServiceException(ErrorEnum.USER_INSERT_FAIL);
    }

    /**
     * 生成随机初始头像
     *
     * @param sexEnum
     * @return
     */
    public String generateHeadImage(SexEnum sexEnum) {
        String headImg = "";
        String address = addressUtil.getV4IP();
        if (SexEnum.MALE.equals(sexEnum)) {
            headImg = address + "/File/head/boy/boy_" + mathUtil.getRangeInteger(1, 7) + ".svg";
        } else {
            headImg = address + "/File/head/girl/girl_" + mathUtil.getRangeInteger(1, 14) + ".svg";
        }
        return headImg;
    }

    @Override
    public PdUser login(PdUser user) {
        PdUser isUser = userDao.findUserByPhoneOrEmail(user);
        if (isUser != null) {
            return isUser;
        }
        throw new ServiceException(ErrorEnum.USER_INFO_EXC);
    }

    @Override
    public int updateUser(PdUser user) {
//        PdUser remoteUser = userDao.findUserByPhoneOrEmail(user);
        if (Objects.isNull(user)) {
            throw new ServiceException(ErrorEnum.USER_INFO_EXC);
        }
//        if (!user.equals(remoteUser)) {
        user.setUserLastLoginTime(dateUtil.getTimeStamp());
        int result = userDao.updateUser(user);
        if (result > 0) {
            return result;
        }
        throw new ServiceException(ErrorEnum.USER_INFO_EXC);
//        } else {
//            throw new ServiceException(ErrorEnum.INFO_IS_LATEST);
//        }

    }

    @Override
    public PdUser findUserById(int id) {
        PdUser user = userDao.findUserById(id);
        if (!Objects.isNull(user)) {
            return user;
        }
        throw new ServiceException(ErrorEnum.USER_NOTFOUND);
    }

    @Override
    public PdUser deleteUser(int id) {
        PdUser user = findUserById(id);
        if (!Objects.isNull(user)) {
            int result = userDao.deleteUser(id);
            if (result > 0) {
                return user;
            }
            throw new ServiceException(ErrorEnum.USER_INFO_EXC);
        }
        throw new ServiceException(ErrorEnum.USER_NOTFOUND);
    }

    @Override
    public PdUser cancelUser(int id) {
        PdUser user = findUserById(id);
        if (!Objects.isNull(user)) {
            user.setUserState(UserStateEnum.getStateEnumById(2));
            userDao.updateUser(user);
            return user;
        }
        throw new ServiceException(ErrorEnum.USER_NOTFOUND);
    }

    @Override
    public PageInfo<List<PdUser>> findUsers(PdUser user, int start, int size) {
        PageHelper.startPage(start, size);
        List<PdUser> users = userDao.select(user);
        PageInfo<List<PdUser>> pageInfo = new PageInfo(users);
        return pageInfo;
    }

    @Override
    public int findUserCount(PdUser user) {
        return userDao.findUserCount(user);
    }

    @Override
    public int updateUserByEmailOrPhone(PdUser user) {
        return userDao.updateUserByEmailOrPhone(user);
    }

    @Override
    public PdUser findByName(String username) {
        return userDao.findUserByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        PdUser user = new PdUser();
//        if (emailOrMsg.isEmail(userName)){
//            user.setUserEmail(userName);
//        }else {
//            user.setUserPhone(userName);
//        }
        PdUser user = userDao.findUserByUserName(userName);
//        user = userDao.findUserByPhoneOrEmail(user);
        if (user != null) {
            List<PdRole> authorities = userDao.findUserAuthorities(user.getUserId());
            user.setAuthorities(authorities);
        }
        /*UserDetails userDetails = User.withUserDetails(user).
                passwordEncoder(PasswordEncoderFactories.
                        createDelegatingPasswordEncoder()::encode).
                build();*/
        return user;
    }
}
