package per.guzx.pri_diary.enumeration;

public enum StateEnum {

    ACTIVATION(1, "已激活"),
    CANCELLATION(2, "已注销"),
    INACTIVATION(3, "未激活");

    private int id;
    private String name;

    StateEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StateEnum getStateEnumById(int id) {
        for (StateEnum stateEnum : StateEnum.values()) {
            if (stateEnum.getId() == id) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
