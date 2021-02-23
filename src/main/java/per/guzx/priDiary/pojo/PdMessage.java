package per.guzx.priDiary.pojo;

import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


@Alias("message")
@Table(name = "pd_blog")
public class PdMessage {
    /**
     * 消息主键
     */
    @Id
    @Column(name = "msg_id")
    private Integer msgId;

    /**
     * 发送用户
     */
    @Column(name = "msg_sender")
    private Integer msgSender;

    /**
     * 接收用户
     */
    @Column(name = "msg_receiver")
    private Integer msgReceiver;

    /**
     * 发送时间
     */
    @Column(name = "msg_create_time")
    private String msgCreateTime;

    /**
     * 接收者是否查看
     */
    @Column(name = "msg_is_readed")
    private Boolean msgIsReaded;

    /**
     * 消息内容（图片，音频，文件2.0处理）
     */
    @Column(name = "msg_content")
    private String msgContent;

    /**
     * 获取消息主键
     *
     * @return msg_id - 消息主键
     */
    public Integer getMsgId() {
        return msgId;
    }

    /**
     * 设置消息主键
     *
     * @param msgId 消息主键
     */
    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取发送用户
     *
     * @return msg_sender - 发送用户
     */
    public Integer getMsgSender() {
        return msgSender;
    }

    /**
     * 设置发送用户
     *
     * @param msgSender 发送用户
     */
    public void setMsgSender(Integer msgSender) {
        this.msgSender = msgSender;
    }

    /**
     * 获取接收用户
     *
     * @return msg_receiver - 接收用户
     */
    public Integer getMsgReceiver() {
        return msgReceiver;
    }

    /**
     * 设置接收用户
     *
     * @param msgReceiver 接收用户
     */
    public void setMsgReceive(Integer msgReceiver) {
        this.msgReceiver = msgReceiver;
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
    public Boolean getMsgIsReaded() {
        return msgIsReaded;
    }

    /**
     * 设置接收者是否查看
     *
     * @param msgIsReaded 接收者是否查看
     */
    public void setMsgIsReade(boolean msgIsReaded) {
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

    public PdMessage(Integer msgId, Integer msgSender, Integer msgReceiver, String msgCreateTime, Boolean msgIsReaded, String msgContent) {
        this.msgId = msgId;
        this.msgSender = msgSender;
        this.msgReceiver = msgReceiver;
        this.msgCreateTime = msgCreateTime;
        this.msgIsReaded = msgIsReaded;
        this.msgContent = msgContent;
    }
}