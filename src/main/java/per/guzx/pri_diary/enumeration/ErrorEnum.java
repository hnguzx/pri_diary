package per.guzx.pri_diary.enumeration;

/**
 * 可以都保存到redis中
 */
public enum ErrorEnum {

    REQUEST_FAIL(400, "请求失败"),
    NOT_FOUND(404, "接口不存在"),
    SYS_ERROR(500, "系统错误"),
    COMMON_FAIL(999,"其它错误"),
    UPDATE_INFO_FAIL(100000, "数据更新失败！"),
    INFO_IS_LATEST(100001, "数据已是最新！"),
    DATA_EXCEPTION(100002, "数据异常！"),
    FILE_UPLOAD(100003, "文件上传失败！"),
    FILE_NOT_FOUND(100004, "文件不存在！"),
    DATA_VALIDATE(100005, "数据验证未通过！"),
    VERIFY_ERROR(100006, "验证码不匹配！"),
    USER_NOTFOUND(200000, "用户信息不存在！"),
    USER_INSERT_FAIL(200001, "新增用户失败！"),
    USER_INFO_EXC(200002, "用户信息异常！"),
    USER_INFO_EXIST(200003, "该邮箱或手机号已注册！"),
    EMAIL_SEND_ERROR(200004, "邮件发送错误！"),
    MSG_SEND_ERROR(200005, "邮件发送错误！"),
    USER_NOT_LOGIN(200006,"用户未登陆"),
    USER_ACCOUNT_EXPIRED(200007,"账号已过期"),
    USER_CREDENTIALS_ERROR(200008,"密码错误"),
    USER_CREDENTIALS_EXPIRED(200009,"密码已过期"),
    USER_ACCOUNT_DISABLE(200010,"账号不可用"),
    USER_ACCOUNT_LOCKED(200011,"账号已锁定"),
    USER_ACCOUNT_NOT_EXIST(200012,"用户不存在"),
    USER_ACCOUNT_USE_BY_OTHERS(200013,"用户账户已在别处登录"),
    USER_CONNECT_SUCCESS(200007,"连接成功"),
    USER_DISCONNECT_SUCCESS(200008,"断开连接成功"),
//    3000-3999 通知相关错误
    DIARY_NOTFOUND(300000, "日记信息不存在！"),
//    4000-4999 好友相关错误
    FRIEND_IS_ADDED(400000, "好友已添加，请不要重复添加！");

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
