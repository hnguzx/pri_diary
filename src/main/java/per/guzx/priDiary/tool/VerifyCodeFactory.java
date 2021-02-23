package per.guzx.priDiary.tool;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author guzx
 */
@Component
public class VerifyCodeFactory {

    static final int VERIFICATION_CODE_LENGTH = 6;

    public String getCode() {
        StringBuilder code = new StringBuilder();
        Random verificationCode = new Random();
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
            code.append((verificationCode.nextInt()));
        }
        return code.toString();
    }
}
