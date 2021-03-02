package per.guzx.priDiary.service;

import javax.websocket.*;
import java.io.IOException;

public interface WebSocketService {

    /**
     * 连接建立成功
     * @param session
     */
    void onOpen(Session session, String receiver);

    /**
     * 连接关闭
     */
    void OnClose();

    /**
     * 接收消息
     * @param message
     * @param session
     */
    void onMessage(String message, Session session) throws IOException;

    /**
     * 调用错误
     * @param session
     * @param error
     */
    void onError(Session session, Throwable error);

    /**
     * 发送消息
     * @param message
     * @throws IOException
     */
    void sendMessage(String message) throws IOException;
}
