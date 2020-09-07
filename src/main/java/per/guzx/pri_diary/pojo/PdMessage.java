package per.guzx.pri_diary.pojo;

import org.apache.ibatis.type.Alias;


@Alias("message")
public class PdMessage {
    /**
     * 发送用户
     */
    private Integer msgSendUser;

    /**
     * 接收用户
     */
    private Integer msgReceive;

    /**
     * 发送时间
     */
    private String msgCreateTime;

    /**
     * 接收者是否查看
     */
    private String msgIsReaded;

    /**
     * 消息内容（图片，音频，文件2.0处理）
     */
    private String msgContent;

    /**
     * 获取发送用户
     *
     * @return msg_send_user - 发送用户
     */
    public Integer getMsgSendUser() {
        return msgSendUser;
    }

    /**
     * 设置发送用户
     *
     * @param msgSendUser 发送用户
     */
    public void setMsgSendUser(Integer msgSendUser) {
        this.msgSendUser = msgSendUser;
    }

    /**
     * 获取接收用户
     *
     * @return msg_receive - 接收用户
     */
    public Integer getMsgReceive() {
        return msgReceive;
    }

    /**
     * 设置接收用户
     *
     * @param msgReceive 接收用户
     */
    public void setMsgReceive(Integer msgReceive) {
        this.msgReceive = msgReceive;
    }

    /**
     * 获取发送时间
     *
     * @return msg_create_time - 发送时间
     */
    public String getMsgCreateTime() {
        return msgCreateTime;
    }

    /**
     * 设置发送时间
     *
     * @param msgCreateTime 发送时间
     */
    public void setMsgCreateTime(String msgCreateTime) {
        this.msgCreateTime = msgCreateTime;
    }

    /**
     * 获取接收者是否查看
     *
     * @return msg_is_readed - 接收者是否查看
     */
    public String getMsgIsReaded() {
        return msgIsReaded;
    }

    /**
     * 设置接收者是否查看
     *
     * @param msgIsReaded 接收者是否查看
     */
    public void setMsgIsReaded(String msgIsReaded) {
        this.msgIsReaded = msgIsReaded;
    }

    /**
     * 获取消息内容（图片，音频，文件2.0处理）
     *
     * @return msg_content - 消息内容（图片，音频，文件2.0处理）
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * 设置消息内容（图片，音频，文件2.0处理）
     *
     * @param msgContent 消息内容（图片，音频，文件2.0处理）
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}