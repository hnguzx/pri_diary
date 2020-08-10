package per.guzx.pri_diary.tool;

import org.springframework.stereotype.Component;

@Component
public class VerifyCodeFactory {

    public String getCode() {
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += (int) (Math.random() * 10);
        }
        return code;
    }
}
