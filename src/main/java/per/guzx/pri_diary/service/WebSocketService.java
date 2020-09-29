package per.guzx.pri_diary.service;

import per.guzx.pri_diary.pojo.PdMessage;

import javax.websocket.*;
import java.io.IOException;

public interface WebSocketService {

    /**
     * 连接建立成功
     * @param session
     */
    public void onOpen(Session session,String receiver);

    /**
     * 连接关闭
     */
    public void OnClose();

    /**
     * 接收消息
     * @param message
     * @param session
     */
    public void onMessage(String message, Session session) throws IOException;

    /**
     * 调用错误
     * @param session
     * @param error
     */
    public void onError(Session session,Throwable error);

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(String message) throws IOException;
}
