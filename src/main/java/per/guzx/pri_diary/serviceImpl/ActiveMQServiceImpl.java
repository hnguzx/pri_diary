package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import per.guzx.pri_diary.service.ActiveMQService;

//@Service
public class ActiveMQServiceImpl implements ActiveMQService {

    @Autowired
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
