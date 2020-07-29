package per.guzx.pri_diary.enumeration;

/**
 * 可以都保存到redis中
 */
public enum ErrorEnum {
    /**
     * 1：公共错误
     * 2：用户相关
     * 3：日记相关
     * 4：日记详情相关
     * 5：
     * 6：
     * 7：
     * 8：
     * 9：
     */

    UPDATE_INFO_FAIL(100000, "信息更新失败"),
    INFO_IS_LATEST(100001, "数据已经是最新的"),
    DATA_EXCEPTION(100002, "数据异常"),
    SYS_ERROR(100004, "系统错误"),
    USER_NOTFOUND(200000, "用户信息不存在"),
    DIARY_NOTFOUND(300000, "日记信息不存在");

    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static UserStateEnum getStateEnumById(String code) {
        for (UserStateEnum userStateEnum : UserStateEnum.values()) {
            if (code.equals(userStateEnum.getId())) {
                return userStateEnum;
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
