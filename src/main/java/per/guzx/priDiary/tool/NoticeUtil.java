package per.guzx.priDiary.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import per.guzx.priDiary.pojo.PdMessage;
import per.guzx.priDiary.pojo.PdUser;
import per.guzx.priDiary.service.PdUserService;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@Component
public class NoticeUtil {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private PdUserService userService;

    /**
     * 发送文字消息给指定用户的指定地址
     * @param targetUserId
     * @param destinationUrl
     * @param msg
     */
    public void sendTxtToUser(int targetUserId, String destinationUrl, PdMessage msg){
        simpMessagingTemplate.convertAndSendToUser(getTargetUser(targetUserId).getUsername(), destinationUrl, msg);
    }

    /**
     * 发送图片消息给指定用户的指定地址
     * @param targetUserId
     * @param destinationUrl
     * @param msg
     */
    public void sendImgToUser(int targetUserId,String destinationUrl,String msg){}

    /**
     * 获取目标用户信息
     * @param userId
     * @return
     */
    private PdUser getTargetUser(int userId){
        PdUser user = userService.findUserById(userId);
        return user;
    }
}
