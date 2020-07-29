package per.guzx.pri_diary.exception;

import per.guzx.pri_diary.enumeration.ErrorEnum;

public class CommonException extends RuntimeException {

    private ErrorEnum errorEnum;

    public CommonException(ErrorEnum errorEnum) {
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
}
