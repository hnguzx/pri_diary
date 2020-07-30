package per.guzx.pri_diary.enumeration;

public enum UserStateEnum {

    ACTIVATION(1, "已激活"),
    CANCELLATION(2, "已注销"),
    INACTIVATION(3, "未激活");

    private int code;
    private String name;

    UserStateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserStateEnum getStateEnumById(int code) {
        for (UserStateEnum userStateEnum : UserStateEnum.values()) {
            if (userStateEnum.getCode() == code) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
