package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;
import per.guzx.pri_diary.enumeration.StateEnum;

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
    private String userName;
    /**
     * 登录密码
     */
    private String userPassword;
    /**
     * 用户状态
     */
    private StateEnum userState;

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

    public StateEnum getUserState() {
        return userState;
    }

    public void setUserState(StateEnum userState) {
        this.userState = userState;
    }
}