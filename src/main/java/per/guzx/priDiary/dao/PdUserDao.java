package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.priDiary.pojo.PdRole;
import per.guzx.priDiary.pojo.PdUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface PdUserDao extends Mapper<PdUser> {
    int insertUser(PdUser user);

    PdUser findUserByUserName(String userName);

    /**
     * 根据用户查找权限
     * @param userId
     * @return
     */
    List<PdRole> findUserAuthorities(long userId);

    PdUser findUserByPhoneOrEmail(PdUser user);

    int updateUser(PdUser user);

    PdUser findUserById(Integer id);

    int deleteUser(int id);

    int findUserCount(PdUser user);

    List<PdUser> findUsers(PdUser user, int start, int size);

    List<PdUser> getUserId();

    int updateUserByEmailOrPhone(PdUser user);

    int findEmailOrPhone(PdUser user);
}
