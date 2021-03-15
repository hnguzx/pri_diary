package per.guzx.priDiary.service;

/**
 * @author Administrator
 */
public interface ActiveMQService {

    void sendMsg(String message);

    void receiveMsg(String message);
}
