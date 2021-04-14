package per.guzx.priDiary.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import per.guzx.priDiary.utils.Groups;
import per.guzx.priDiary.valid.PhoneNumber;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;


/**
 * @author Administrator
 */
@Table(name = "pd_blog")
@Alias("friend")
@ApiModel(description = "好友详细信息")
public class PdFriend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 好友ID
     */
    @Null(message = "新增时不需要指定id", groups = Groups.Add.class)
    @NotNull(message = "更新时必须指定id", groups = Groups.Update.class)
    @Id
    @Column(name = "friend_id")
    @ApiModelProperty(value = "好友ID")
    private Integer friendId;

    /**
     * 我的用户ID
     */
    @NotNull(message = "本人id不能为空",groups = {Groups.Add.class,Groups.Update.class})
    @Column(name = "my_user_id")
    @ApiModelProperty(value = "我的用户ID")
    private Integer myUserId;

    /**
     * 我的邮箱
     */
    @Email(message = "邮箱格式不正确",groups = {Groups.Add.class,Groups.Update.class})
    @Column(name = "my_email")
    @ApiModelProperty(value = "我的邮箱")
    private String myEmail;

    /**
     * 我的手机号码
     */
    @PhoneNumber(message = "手机号码格式不正确",groups = {Groups.Add.class})
    @Column(name = "my_phone")
    @ApiModelProperty(value = "我的手机号码")
    private String myPhone;

    /**
     * 好友的用户ID
     */
    @NotNull(message = "好友id不能为空",groups = {Groups.Add.class,Groups.Update.class})
    @Column(name = "friend_user_id")
    @ApiModelProperty(value = "好友的用户ID")
    private Integer friendUserId;

    /**
     * 好友的邮箱
     */
    @Email(message = "邮箱格式不正确",groups = {Groups.Add.class,Groups.Update.class})
    @Column(name = "friend_email")
    @ApiModelProperty(value = "好友的邮箱")
    private String friendEmail;

    /**
     * 好友的手机号码
     */
    @PhoneNumber(message = "手机号码格式不正确",groups = {Groups.Add.class})
    @Column(name = "friend_phone")
    @ApiModelProperty(value = "好友的手机号码")
    private String friendPhone;

    /**
     * 好友的备注
     */
    @Column(name = "friend_remark")
    @ApiModelProperty(value = "好友的备注")
    private String friendRemark;


    private Integer friendApplyResult;

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
     *
     * @return
     */
    public Integer getFriendApplyResult() {
        return friendApplyResult;
    }

    /**
     * 设置好友申请结果
     *
     * @param friendApplyResult
     */
    public void setFriendApplyResult(Integer friendApplyResult) {
        this.friendApplyResult = friendApplyResult;
    }
}