package per.guzx.pri_diary.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import per.guzx.pri_diary.pojo.PdMessage;
import per.guzx.pri_diary.service.WebSocketService;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@ServerEndpoint("/ws/{receiver}")
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {

    private static int onlineCount = 0;
    // 存放每个客户端对应的webSocketImpl对象
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;
    private String receiver;

    @Override
    @OnOpen
    public void onOpen(Session session, @PathParam("receiver") String receiver) {
        this.session = session;
        this.receiver = receiver;
        webSocketSet.add(this);
        addOnlineCount();
    }

    @Override
    @OnClose
    public void OnClose() {
        webSocketSet.remove(this);
        subOnlineCount();
    }

    @Override
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String sendUser = message.split("|")[0];
        String receiver = message.split("|")[1];
        String messageContent = message.split("|")[2];
        log.trace("用户：" + sendUser + "发送消息给用户：" + receiver + "。消息内容为：" + messageContent);
        for (WebSocketServiceImpl item : webSocketSet) {
            if ()
            item.sendMessage(messageContent);
        }
    }


    @Override
    @OnError
    public void onError(Session session, Throwable error) {
        log.trace("消息发送出错！");
        error.printStackTrace();
    }

    @Override
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
