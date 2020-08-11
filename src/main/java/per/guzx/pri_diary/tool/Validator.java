package per.guzx.pri_diary.tool;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class Validator {

    public static Map<String, Object> validator(Errors errors) {
        List<ObjectError> objectErrors = errors.getAllErrors();
        Map<String, Object> errorMap = new HashMap<>();
        for (ObjectError error : objectErrors) {
            String key = null;
            String value = null;
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                key = fieldError.getField();
            } else {
                key = error.getObjectName();
            }
            value = error.getDefaultMessage();
            errorMap.put(key, value);
        }
        return errorMap;
    }

    public boolean isNull(String str) {
        if (str.length() == 0 || Objects.isNull(str)) {
            return true;
        }
        return false;
    }
}
