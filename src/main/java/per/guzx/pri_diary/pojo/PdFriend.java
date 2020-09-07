package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;

import javax.persistence.*;

@Alias("friend")
public class PdFriend {
    /**
     * 我的用户ID
     */
    private Integer myUserId;

    /**
     * 我的邮箱
     */
    private String myEmail;

    /**
     * 我的手机号码
     */
    private String myPhone;

    /**
     * 好友的用户ID
     */
    private Integer friendUserId;

    /**
     * 好友的邮箱
     */
    private String friendEmail;

    /**
     * 好友的手机号码
     */
    private String friendPhone;

    /**
     * 好友的备注
     */
    private String friendRemark;

    /**
     * 获取我的用户ID
     *
     * @return my_user_id - 我的用户ID
     */
    public Integer getMyUserId() {
        return myUserId;
    }

    /**
     * 设置我的用户ID
     *
     * @param myUserId 我的用户ID
     */
    public void setMyUserId(Integer myUserId) {
        this.myUserId = myUserId;
    }

    /**
     * 获取我的邮箱
     *
     * @return my_email - 我的邮箱
     */
    public String getMyEmail() {
        return myEmail;
    }

    /**
     * 设置我的邮箱
     *
     * @param myEmail 我的邮箱
     */
    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    /**
     * 获取我的手机号码
     *
     * @return my_phone - 我的手机号码
     */
    public String getMyPhone() {
        return myPhone;
    }

    /**
     * 设置我的手机号码
     *
     * @param myPhone 我的手机号码
     */
    public void setMyPhone(String myPhone) {
        this.myPhone = myPhone;
    }

    /**
     * 获取好友的用户ID
     *
     * @return friend_user_id - 好友的用户ID
     */
    public Integer getFriendUserId() {
        return friendUserId;
    }

    /**
     * 设置好友的用户ID
     *
     * @param friendUserId 好友的用户ID
     */
    public void setFriendUserId(Integer friendUserId) {
        this.friendUserId = friendUserId;
    }

    /**
     * 获取好友的邮箱
     *
     * @return friend_email - 好友的邮箱
     */
    public String getFriendEmail() {
        return friendEmail;
    }

    /**
     * 设置好友的邮箱
     *
     * @param friendEmail 好友的邮箱
     */
    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }

    /**
     * 获取好友的手机号码
     *
     * @return friend_phone - 好友的手机号码
     */
    public String getFriendPhone() {
        return friendPhone;
    }

    /**
     * 设置好友的手机号码
     *
     * @param friendPhone 好友的手机号码
     */
    public void setFriendPhone(String friendPhone) {
        this.friendPhone = friendPhone;
    }

    /**
     * 获取好友的备注
     *
     * @return friend_remark - 好友的备注
     */
    public String getFriendRemark() {
        return friendRemark;
    }

    /**
     * 设置好友的备注
     *
     * @param friendRemark 好友的备注
     */
    public void setFriendRemark(String friendRemark) {
        this.friendRemark = friendRemark;
    }
}