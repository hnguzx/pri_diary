package per.guzx.priDiary.tool;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class PasswordEncodeUtil {

    public String passwordEncode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
