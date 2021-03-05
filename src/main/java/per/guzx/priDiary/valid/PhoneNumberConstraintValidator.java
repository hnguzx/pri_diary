package per.guzx.priDiary.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/5 10:28
 * @describe
 */
public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumber, String> {

    private String phoneNumber;

    private static final String PHONE_NUMBER = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$";
    private static final Pattern pattern = Pattern.compile(PHONE_NUMBER);

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        this.phoneNumber = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value) || value.trim().length()==0){
            return true;
        }
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
