package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import per.guzx.pri_diary.enumeration.SexEnum;
import per.guzx.pri_diary.enumeration.UserStateEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;

@Alias("user")
@Table(name = "pd_blog")
public class PdUser implements Serializable, Cloneable, UserDetails {
    /**
     * 用户唯一标识
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户昵称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户出生日期
     */
    @Column(name = "user_birthday")
    private String userBirthday;

    /**
     * 用户性别
     */
    @Column(name = "user_sex")
    private SexEnum userSex;

    /**
     * 用户登录密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 用户状态
     */
    @Column(name = "user_state")
    private UserStateEnum userState;

    /**
     * 手机号码
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 邮箱地址
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 用户头像图片存储地址
     */
    @Column(name = "user_head")
    private String userHead;

    /**
     * 用户创建时间
     */
    @Column(name = "user_create_time")
    private String userCreateTime;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 获取用户唯一标识
     *
     * @return user_id - 用户唯一标识
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户唯一标识
     *
     * @param userId 用户唯一标识
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户昵称
     *
     * @return user_name - 用户昵称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户昵称
     *
     * @param userName 用户昵称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户出生日期
     *
     * @return user_birthday - 用户出生日期
     */
    public String getUserBirthday() {
        return userBirthday;
    }

    /**
     * 设置用户出生日期
     *
     * @param userBirthday 用户出生日期
     */
    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    /**
     * 获取用户性别
     *
     * @return user_sex - 用户性别
     */
    public SexEnum getUserSex() {
        return userSex;
    }
    /**
     * 设置用户性别
     *
     * @param userSex 用户性别
     */
    public void setUserSex(SexEnum userSex) {
        this.userSex = userSex;
    }

    /**
     * 获取用户登录密码
     *
     * @return user_password - 用户登录密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置用户登录密码
     *
     * @param userPassword 用户登录密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取用户状态
     *
     * @return user_state - 用户状态
     */
    public void setUserState(UserStateEnum userState) {
        this.userState = userState;
    }

    /**
     * 设置用户状态
     * @return
     */
    public UserStateEnum getUserState() {
        return userState;
    }

    /**
     * 获取手机号码
     *
     * @return user_phone - 手机号码
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置手机号码
     *
     * @param userPhone 手机号码
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 获取邮箱地址
     *
     * @return user_email - 邮箱地址
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置邮箱地址
     *
     * @param userEmail 邮箱地址
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取用户头像图片存储地址
     *
     * @return user_head - 用户头像图片存储地址
     */
    public String getUserHead() {
        return userHead;
    }

    /**
     * 设置用户头像图片存储地址
     *
     * @param userHead 用户头像图片存储地址
     */
    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    /**
     * 获取用户创建时间
     *
     * @return user_create_time - 用户创建时间
     */
    public String getUserCreateTime() {
        return userCreateTime;
    }

    /**
     * 设置用户创建时间
     *
     * @param userCreateTime 用户创建时间
     */
    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == null) {
            return true;
        }
        // 需要在判断是否为空！
        if (obj instanceof PdUser) {
            PdUser user = (PdUser) obj;
            if (user.getUserName().equals(this.getUserName()) &&
                    user.getUserPassword().equals(this.getUserPassword()) &&
                    user.getUserBirthday().equals(this.getUserBirthday()) &&
                    user.getUserSex().equals(this.getUserSex()) &&
                    user.getUserPhone().equals(this.getUserPhone()) &&
                    user.getUserEmail().equals(this.getUserEmail()) &&
                    user.getUserHead().equals(this.getUserHead()) &&
                    user.getUserState().equals(this.getUserState())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 返回账号的权限合集
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 账户是否失效，返回false账户失效，不可用
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否被锁，返回false，账号被锁，不可用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账号认证是否过期，返回false，账号过期，不可用
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否可用，返回false，不可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}