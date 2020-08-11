package per.guzx.pri_diary.service;

import per.guzx.pri_diary.pojo.PdUser;

import java.util.List;

public interface PdUserService {
    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    public PdUser insertUser(PdUser user);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    public PdUser login(PdUser user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    public int updateUser(PdUser user);

    /**
     * 根据id查询单个用户
     *
     * @param id
     * @return
     */
    public PdUser findUserById(int id);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public PdUser deleteUser(int id);

    /**
     * 用户信息注销
     *
     * @param id
     * @return
     */
    public PdUser cancelUser(int id);

    /**
     * 分页查询用户
     *
     * @param user
     * @param start
     * @param limit
     * @return
     */
    public List<PdUser> findUsers(PdUser user, int start, int limit);

    /**
     * 获取符合条件的用户总数
     * @param user
     * @return
     */
    public int findUserCount(PdUser user);

    /**
     * 通过邮箱地址或手机号码重置密码
     * @param user
     * @return
     */
    public int updateUserByEmailOrPhone(PdUser user);
}
