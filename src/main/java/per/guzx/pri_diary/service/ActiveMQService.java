package per.guzx.pri_diary.service;

import org.springframework.stereotype.Service;

public interface ActiveMQService {

    /**
     * 发送消息
     * @param message
     */
    public void sendMsg(String message);

    /**
     * 接收消息
     * @param message
     */
    public void receiveMsg(String message);
}
