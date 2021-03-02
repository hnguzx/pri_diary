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
    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int insertUser(PdUser user);

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    PdUser findUserByUserName(String userName);

    /**
     * 根据用户查找权限
     * @param userId
     * @return
     */
    List<PdRole> findUserAuthorities(long userId);

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
    PdUser findUserById(Integer id);

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
     * @param size
     * @return
     */
    List<PdUser> findUsers(PdUser user, int start, int size);

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
