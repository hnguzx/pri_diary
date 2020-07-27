package per.guzx.pri_diary.enumeration;

public enum ErrorEnum {
    ACTIVATION(100001, "用户不存在"),
    CANCELLATION(100002, "000002错误"),
    INACTIVATION(100003, "000003错误");

    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static StateEnum getStateEnumById(String code) {
        for (StateEnum stateEnum : StateEnum.values()) {
            if (code.equals(stateEnum.getId())) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
