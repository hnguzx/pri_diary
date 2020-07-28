package per.guzx.pri_diary.exception;

import per.guzx.pri_diary.enumeration.ErrorEnum;

public class CommonException extends Exception {

    private int code;


    public CommonException(String message, ErrorEnum errorEnum) {
        super(message);
        this.code = errorEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
