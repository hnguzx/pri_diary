package per.guzx.pri_diary.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import per.guzx.pri_diary.enumeration.ErrorEnum;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class EmailOrMsg {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    public String sender;

    /**
     * 判断用户是否是使用邮箱注册
     *
     * @param emailOrPhone
     * @return
     */
    public boolean isEmail(String emailOrPhone) {
        String emailRegex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(emailOrPhone);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

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
            messageHelper.setText("<p><h3>验证码:" + verifyCode + "</h3>您正在注册成为<b>私人日记</b>用户，" +
                    "请在1分钟内完成注册。若非本人操作，请忽略。</p>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error(ErrorEnum.EMAIL_SEND_ERROR.getMsg());
        }
        javaMailSender.send(messageHelper.getMimeMessage());
    }

    /**
     * 短信验证码
     * verifyCodeMsg
     */
    @Async
    public void sendVerifyCodeMsg(String receiver, String verifyCode) {
        try {

        } catch (Exception e) {
            log.error(ErrorEnum.MSG_SEND_ERROR.getMsg());
        }
    }
}
