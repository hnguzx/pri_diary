package per.guzx.priDiary.enumeration;

/**
 * @author Administrator
 */

public enum SexEnum {

    FEMALE(0, "女"),
    MALE(1, "男");


    private int code;
    private String name;

    SexEnum(int code, String name) {
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
