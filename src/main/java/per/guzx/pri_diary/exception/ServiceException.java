package per.guzx.pri_diary.exception;

import per.guzx.pri_diary.enumeration.ErrorEnum;

/**
 * 服务错误
 */
public class ServiceException extends RuntimeException {

    private ErrorEnum errorEnum;

    public ServiceException() {
    }

    public ServiceException(ErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
