package per.guzx.pri_diary.enumeration;

public enum MoodEnum {


    SUNNY(1, "晴"),
    CLOUDY(2, "阴"),
    RAIN(3, "雨"),
    SNOW(4, "雪"),
    WIND(5, "风"),
    DESTROY(0, "地球毁灭");

    private int code;
    private String name;

    MoodEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static WeathEnum getStateEnumById(int code) {
        for (WeathEnum weathEnum : WeathEnum.values()) {
            if (weathEnum.getCode() == code) {
                return weathEnum;
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
