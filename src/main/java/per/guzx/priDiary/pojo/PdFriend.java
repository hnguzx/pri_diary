package per.guzx.priDiary.pojo;

import org.apache.ibatis.type.Alias;

import javax.persistence.*;

@Alias("friend")
@Table(name = "pd_blog")
public class PdFriend {
    /**
     * 好友ID
     */
    @Id
    @Column(name = "friend_id")
    private Integer friendId;

    /**
     * 我的用户ID
     */
    @Column(name = "my_user_id")
    private Integer myUserId;

    /**
     * 我的邮箱
     */
    @Column(name = "my_email")
    private String myEmail;

    /**
     * 我的手机号码
     */
    @Column(name = "my_phone")
    private String myPhone;

    /**
     * 好友的用户ID
     */
    @Column(name = "friend_user_id")
    private Integer friendUserId;

    /**
     * 好友的邮箱
     */
    @Column(name = "friend_email")
    private String friendEmail;

    /**
     * 好友的手机号码
     */
    @Column(name = "friend_phone")
    private String friendPhone;

    /**
     * 好友的备注
     */
    @Column(name = "friend_remark")
    private String friendRemark;

    private int friendApplyResult;

    /**
     * 获取好友ID
     *
     * @return friend_id - 好友ID
     */
    public Integer getFriendId() {
        return friendId;
    }

    /**
     * 设置好友ID
     *
     * @param friendId 好友ID
     */
    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

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

    /**
     * 获取好友申请结果
     * @return
     */
    public int getFriendApplyResult() {
        return friendApplyResult;
    }

    /**
     * 设置好友申请结果
     * @param friendApplyResult
     */
    public void setFriendApplyResult(int friendApplyResult) {
        this.friendApplyResult = friendApplyResult;
    }
}