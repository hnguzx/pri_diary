package per.guzx.priDiary.service;

public interface ActiveMQService {

    /**
     * 发送消息
     * @param message
     */
    void sendMsg(String message);

    /**
     * 接收消息
     * @param message
     */
    void receiveMsg(String message);
}
