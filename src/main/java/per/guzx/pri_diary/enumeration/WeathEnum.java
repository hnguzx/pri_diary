package per.guzx.pri_diary.enumeration;

public enum WeathEnum {

    SUNNY(1, "晴"),
    CLOUDY(2, "阴"),
    RAIN(3, "雨"),
    SNOW(4, "雪"),
    WIND(5, "风"),
    DESTROY(0, "地球毁灭");

    private int id;
    private String name;

    WeathEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static WeathEnum getStateEnumById(int id) {
        for (WeathEnum weathEnum : WeathEnum.values()) {
            if (weathEnum.getId() == id) {
                return weathEnum;
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
