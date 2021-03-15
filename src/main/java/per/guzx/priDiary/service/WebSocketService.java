package per.guzx.priDiary.service;

import javax.websocket.*;
import java.io.IOException;

/**
 * @author Administrator
 */
public interface WebSocketService {

    void onOpen(Session session, String receiver);

    void OnClose();

    void onMessage(String message, Session session) throws IOException;

    void onError(Session session, Throwable error);

    void sendMessage(String message) throws IOException;
}
