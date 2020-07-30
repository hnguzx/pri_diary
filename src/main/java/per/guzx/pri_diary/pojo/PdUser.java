package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;
import per.guzx.pri_diary.enumeration.UserStateEnum;

import javax.validation.constraints.*;
import java.io.Serializable;

@Alias("user")
public class PdUser implements Serializable, Cloneable {
    /**
     * 用户唯一性标识
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    @NotNull(message = "用户名不能为空！")
    @Size(min = 1,message = "用户名不能为空！")
    private String userName;
    /**
     * 登录密码
     */
    @NotNull(message = "密码不能为空！")
    @Size(min = 6,max = 50,message = "密码长度应该在6-50位之间！")
    private String userPassword;
    /**
     * 用户状态
     */
    private UserStateEnum userState;

    /**
     * 用户手机号码
     */
    private String userPhone;

    /**
     * 用户邮箱地址
     */
    @Email(message = "邮箱格式错误！")
    private String userEmail;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserStateEnum getUserState() {
        return userState;
    }

    public void setUserState(UserStateEnum userState) {
        this.userState = userState;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == null) {
            return true;
        }
        if (obj instanceof PdUser) {
            PdUser user = (PdUser) obj;
            if (user.getUserName().equals(this.getUserName()) &&
                    user.getUserPassword().equals(this.getUserPassword()) &&
                    user.getUserState().equals(this.getUserState())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}