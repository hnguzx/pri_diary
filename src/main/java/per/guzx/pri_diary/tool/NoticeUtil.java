package per.guzx.pri_diary.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NoticeUtil {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 发送文字消息给指定用户的指定地址
     * @param targetUser
     * @param destinationUrl
     * @param msg
     */
    public void sendTxtToUser(String targetUser,String destinationUrl,String msg){
        simpMessagingTemplate.convertAndSendToUser(targetUser, destinationUrl, msg);
    }

    /**
     * 发送图片消息给指定用户的指定地址
     * @param targetUser
     * @param destinationUrl
     * @param msg
     */
    public void sendImgToUser(String targetUser,String destinationUrl,String msg){}
}
