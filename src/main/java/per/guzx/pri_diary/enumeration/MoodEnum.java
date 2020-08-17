package per.guzx.pri_diary.enumeration;

public enum MoodEnum {


    HAPPY(1, "开心"),
    ENRICH(2, "充实"),
    SURPRISED(3, "惊喜"),
    PROUD(4, "得意"),
    WARM(5, "温暖"),
    TOUCH(6, "平静"),
    SORRY(7, "难过"),
    AGITATED(8, "烦躁"),
    LOST(9, "迷惘"),
    LONELY(10, "孤独"),
    ANGRY(11, "生气"),
    EMBARRASSED(12, "尴尬"),
    INJUSTICE(13, "委屈"),
    SWEET(14, "甜蜜"),
    DREAM(15, "梦境"),
    TIRED(16, "疲惫"),
    ESCAPE(17, "逃避"),
    IT_IS_A_LONG_STORY(18, "一言难尽");

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
