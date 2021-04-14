package per.guzx.priDiary.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author guzx
 */
@Component
@Slf4j
public class VerifyCodeFactory {

    static final int VERIFICATION_CODE_LENGTH = 6;

    public String getCode() {
        StringBuilder code = new StringBuilder();
        Random verificationCode = new Random();
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
            code.append((verificationCode.nextInt(10)));
        }
        log.trace("生成验证码：" + code.toString());
        return code.toString();
    }
}
