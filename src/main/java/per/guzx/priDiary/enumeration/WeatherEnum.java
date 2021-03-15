package per.guzx.priDiary.enumeration;

public enum WeatherEnum {

    /**
     * 天气枚举
     */
    SUNNY_DAY(1, "晴-昼"),
    SUNNY_NIGHT(2, "晴-夜"),
    HAIL(3, "冰雹"),
    HEAVY_SNOW(4, "大雪"),
    HEAVY_RAIN(5, "大雨"),
    HEAVY_RAIN_DAY(6, "多云-昼"),
    HEAVY_RAIN_NIGHT(7, "多云-夜"),
    FLOATING_DUST(8, "浮尘"),
    THUNDER_AND_LIGHTNING(9, "雷电"),
    HAZE(10, "霾"),
    TYPHOON(11, "台风"),
    FOG(12, "雾"),
    LIGHT_SNOW(13, "小雪"),
    LIGHT_RAIN(14, "小雨"),
    CLOUDY_DAY(15, "阴天"),
    SNOW_SHOWER_DAY(16, "阵雪-昼"),
    SNOW_SHOWER_NIGHT(17, "阵雪-夜"),
    SHOWER_DAY(18, "阵雨-昼"),
    SHOWER_NIGHT(19, "阵雨-夜"),
    RAISE_SAND(20, "扬沙");

    private int code;
    private String name;

    WeatherEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static WeatherEnum getStateEnumById(int code) {
        for (WeatherEnum weatherEnum : WeatherEnum.values()) {
            if (weatherEnum.getCode() == code) {
                return weatherEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
