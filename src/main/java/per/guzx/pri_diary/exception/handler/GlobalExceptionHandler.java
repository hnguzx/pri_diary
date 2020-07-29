package per.guzx.pri_diary.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.exception.CommonException;
import per.guzx.pri_diary.pojo.ApiResp;

import javax.annotation.Resource;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @Resource
    private ObjectMapper jsonMapper;

    /**
     * 捕获自定义异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public ApiResp baseServiceException(CommonException exception) {
        log.warn(exception.getMessage());
        return ApiResp.retFail(exception.getErrorEnum());
    }

    @ExceptionHandler(Exception.class)
    public ApiResp OtherException(Exception exception) {
        log.warn(exception.getMessage());
        return ApiResp.retFail(ErrorEnum.SYS_ERROR);
    }

}
