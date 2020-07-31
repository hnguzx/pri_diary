package per.guzx.pri_diary.enumeration;

/**
 * 可以都保存到redis中
 */
public enum ErrorEnum {

    REQUEST_FAIL(400, "请求失败"),
    NOT_FOUND(404, "接口不存在"),
    SYS_ERROR(500, "系统错误"),
    UPDATE_INFO_FAIL(100000, "数据更新失败！"),
    INFO_IS_LATEST(100001, "数据已是最新！"),
    DATA_EXCEPTION(100002, "数据异常！"),
    FILE_UPLOAD(100003, "文件上传失败！"),
    FILE_NOT_FOUND(100004, "文件不存在！"),
    DATA_VALIDATE(100005, "数据验证未通过！"),
    USER_NOTFOUND(200000, "用户信息不存在！"),
    USER_INSERT_FAIL(200001, "新增用户失败！"),
    USER_INFO_EXC(200002, "用户信息异常！"),
    DIARY_NOTFOUND(300000, "日记信息不存在！");

    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static UserStateEnum getStateEnumById(String code) {
        for (UserStateEnum userStateEnum : UserStateEnum.values()) {
            if (code.equals(userStateEnum.getCode())) {
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
