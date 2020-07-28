package per.guzx.pri_diary.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.guzx.pri_diary.exception.CommonException;

import javax.annotation.Resource;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Resource
    private ObjectMapper jsonMapper;

    @ExceptionHandler(CommonException.class)
    public ObjectNode baseServiceException(CommonException e) {
        int code = e.getCode();
        String msg = e.getMessage();
        return jsonMapper.createObjectNode().put("code", code).put("msg", msg);
    }
}
