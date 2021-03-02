package per.guzx.priDiary.service;

import com.github.pagehelper.PageInfo;
import per.guzx.priDiary.pojo.PdUser;

import java.util.List;

/**
 * @author Administrator
 */
public interface PdUserService {
    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    PdUser insertUser(PdUser user);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    PdUser login(PdUser user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    int updateUser(PdUser user);

    /**
     * 根据id查询单个用户
     *
     * @param id
     * @return
     */
    PdUser findUserById(int id);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    PdUser deleteUser(int id);

    /**
     * 用户信息注销
     *
     * @param id
     * @return
     */
    PdUser cancelUser(int id);

    /**
     * 分页查询用户
     *
     * @param user
     * @param start
     * @param size
     * @return
     */
    PageInfo<List<PdUser>> findUsers(PdUser user, int start, int size);

    /**
     * 获取符合条件的用户总数
     * @param user
     * @return
     */
    int findUserCount(PdUser user);

    /**
     * 通过邮箱地址或手机号码重置密码
     * @param user
     * @return
     */
    int updateUserByEmailOrPhone(PdUser user);

    PdUser findByName(String username);
}
