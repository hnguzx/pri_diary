package per.guzx.priDiary.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import per.guzx.priDiary.enumeration.SexEnum;
import per.guzx.priDiary.enumeration.UserStateEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Administrator
 */
@Table(name = "pd_user")
@Alias("user")
@ApiModel(description = "评论详细信息")
public class PdUser implements Serializable, UserDetails {
    /**
     * 用户唯一标识
     */
    @Id
    @Column(name = "user_id")
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空")
    @Length(max = 30,message = "用户昵称长度不能超过30个字符")
    @Column(name = "user_name")
    @ApiModelProperty(value = "用户昵称")
    private String userName;

    /**
     * 用户出生日期
     */
    @NotBlank(message = "用户出生日期不能为空")
    @Length(min = 10,max = 10,message = "日期格式不正确")
    @Column(name = "user_birthday")
    @ApiModelProperty(value = "用户出生日期")
    private String userBirthday;

    /**
     * 用户性别
     */
    @NotNull(message = "用户性别不能为空")
    @Column(name = "user_sex")
    @ApiModelProperty(value = "用户性别")
    private SexEnum userSex;

    /**
     * 用户登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码必须为8~16个字母和数字组合")
    @Column(name = "user_password")
    @ApiModelProperty(value = "日记所属用户id")
    private String userPassword;

    /**
     * 用户状态
     */
    @Column(name = "user_state")
    @ApiModelProperty(value = "用户状态")
    private UserStateEnum userState;

    /**
     * 手机号码
     */
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$", message = "手机号码格式不正确")
    @Column(name = "user_phone")
    @ApiModelProperty(value = "手机号码")
    private String userPhone;

    /**
     * 邮箱地址
     */
    @Email
    @Column(name = "user_email")
    @ApiModelProperty(value = "邮箱地址")
    private String userEmail;

    /**
     * 用户头像图片存储地址
     */
    @Column(name = "user_head")
    @ApiModelProperty(value = "用户头像图片存储地址")
    private String userHead;

    /**
     * 用户创建时间
     */
    @Column(name = "user_create_time")
    @ApiModelProperty(value = "用户创建时间")
    private String userCreateTime;

    /**
     * 用户最后登录时间
     */
    @Column(name = "user_last_login_time")
    @ApiModelProperty(value = "用户最后登录时间")
    private String userLastLoginTime;

    /**
     * 用户信息更新时间
     */
    @Column(name = "user_update_time")
    @ApiModelProperty(value = "用户信息更新时间")
    private String userUpdateTime;

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
     *
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
        return this.userCreateTime;
    }

    /**
     * 设置用户创建时间
     *
     * @param userCreateTime 用户创建时间
     */
    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    /**
     * 获取用户最后登录时间
     *
     * @return user_create_time - 用户创建时间
     */
    public String getUserLastLoginTime() {
        return this.userLastLoginTime;
    }

    /**
     * 设置用户最后登录时间
     *
     * @param userLastLoginTime 用户创建时间
     */
    public void setUserLastLoginTime(String userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
    }

    /**
     * 获取用户最后更新时间
     *
     * @return user_create_time - 用户创建时间
     */
    public String getUserUpdateTime() {
        return this.userUpdateTime;
    }

    /**
     * 设置用户最后更新时间
     *
     * @param userUpdateTime 用户创建时间
     */
    public void setUserUpdateTime(String userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    /*@Override
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
    }*/

    /**
     * 返回账号的权限合集
     *
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
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否被锁，返回false，账号被锁，不可用
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账号认证是否过期，返回false，账号过期，不可用
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否可用，返回false，不可用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
