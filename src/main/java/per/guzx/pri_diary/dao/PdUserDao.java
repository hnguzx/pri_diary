package per.guzx.pri_diary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.pri_diary.pojo.PdUser;

import java.util.List;

@Repository
public interface PdUserDao {
    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int insertUser(PdUser user);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    PdUser findUserByPhoneOrEmail(PdUser user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    int updateUser(PdUser user);

    /**
     * 查找单个用户
     *
     * @param id
     * @return
     */
    PdUser findUserById(int id);

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    int deleteUser(int id);

    /**
     * 获取符合条件的用户的数量
     * @param user
     * @return
     */
    int findUserCount(PdUser user);

    /**
     * 分页查询
     *
     * @param user
     * @param start
     * @param limit
     * @return
     */
    List<PdUser> findUsers(PdUser user, int start, int limit);

    /**
     * 获取所有用户的基本信息
     * @return
     */
    List<PdUser> getUserId();

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUserByEmailOrPhone(PdUser user);

    /**
     * 获取指定邮箱或手机号的注册数量
     * @param user
     * @return
     */
    int findEmailOrPhone(PdUser user);
}
