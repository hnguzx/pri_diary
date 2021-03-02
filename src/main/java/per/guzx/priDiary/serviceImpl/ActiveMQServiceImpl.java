package per.guzx.priDiary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import per.guzx.priDiary.service.ActiveMQService;

import javax.annotation.Resource;

public class ActiveMQServiceImpl implements ActiveMQService {

//    @Resource
//    private JmsTemplate jmsTemplate = null;

    @Override
    public void sendMsg(String message) {
        System.out.println("要发送的消息是：" + message);
//        jmsTemplate.convertAndSend(message);
    }

    @Override
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void receiveMsg(String message) {
        System.out.println("接收到消息：" + message);
    }
}
