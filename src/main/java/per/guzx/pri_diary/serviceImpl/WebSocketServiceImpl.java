package per.guzx.pri_diary.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Service
@ServerEndpoint("/ws")
public class WebSocketServiceImpl {
    private static int onlineCount = 0;
    // 存放每个客户端对应的webSocketImpl对象
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;
    private String receiver;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.receiver = userId;
        webSocketSet.add(this);
        addOnlineCount();
        log.trace("用户：" + userId + "上线，当前在线人数为：" + getOnlineCount());
    }

    @OnClose
    public void OnClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        log.trace("有一个用户下线，当前用户数为：" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        if (message.indexOf('|') == -1) {
            for (WebSocketServiceImpl item : webSocketSet) {
                item.sendMessage(message);
            }
        } else {
            String sendUser = message.split("|")[0];
            String receiver = message.split("|")[1];
            String messageContent = message.split("|")[2];
            log.trace("用户：" + sendUser + "发送消息给用户：" + receiver + "。消息内容为：" + messageContent);
            for (WebSocketServiceImpl item : webSocketSet) {
                item.sendMessage(messageContent);
            }
        }

    }


    @OnError
    public void onError(Session session, Throwable error) {
        log.trace("消息发送出错！");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        // 暂时只支持发送文字
        this.session.getBasicRemote().sendText(message);
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServiceImpl.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServiceImpl.onlineCount--;
    }
}
