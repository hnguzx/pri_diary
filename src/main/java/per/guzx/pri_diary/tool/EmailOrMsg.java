package per.guzx.pri_diary.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmailOrMsg {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    public String sender;

    /**
     * 邮件验证码
     * verifyCodeEmail
     */
    @Async
    public void sendVerifyCodeEmail(String receiver, String verifyCode) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setSubject("私人日记");
            messageHelper.setFrom(sender);
            messageHelper.setTo(receiver);
            messageHelper.setText("<p>" + verifyCode + "</p>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(messageHelper.getMimeMessage());
    }

    /**
     * 短信验证码
     * verifyCodeMsg
     */
    @Async
    public void sendVerifyCodeMsg(String receiver, String verifyCode) {
    }
}
