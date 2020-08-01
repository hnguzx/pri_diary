package per.guzx.pri_diary.enumeration;

public enum WeathEnum {

    SUNNY(1, "晴朗"),
    CLOUDY(2, "阴沉"),
    RAINY(3, "小雨"),
    SNOWY(4, "下雪"),
    WINDY(5, "刮风"),
    FOGGY (6, "雾/霾"),
    STORMY(7, "闪电"),
    DESTROY(0, "地球毁灭");

    private int code;
    private String name;

    WeathEnum(int code, String name) {
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
