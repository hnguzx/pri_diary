package per.guzx.pri_diary.enumeration;

public enum EventEnum {


    MOVIE(1, "电影"),
    GAME(2, "游戏"),
    FAMILY(3, "家庭"),
    TOURISM(4, "旅游"),
    RELATIONSHIP(5, "关系"),
    FOOD(5, "食物"),
    MUSIC(5, "音乐"),
    WORK(5, "工作"),
    REST(5, "休息"),
    LOVE(5, "爱情"),
    SHOPPING(5, "逛街"),
    ADVENTURE(5, "冒险"),
    IMAGINATION(5, "脑洞"),
    LEARNING(5, "学习"),
    READING(5, "阅读"),
    MOVEMENT(5, "运动"),
    ADJUST(5, "调整"),
    FITNESS(5, "健身"),
    SICK(5, "生病"),
    STAR(5, "爱豆"),
    PET(5, "宠物"),
    AFTER_PLAY(5, "追剧"),
    ANIME(5, "动漫"),
    INTERNET(5, "上网"),
    ONLINE_SHOPPING(5, "网购"),
    EAT_THE_MELON(5, "吃瓜"),
    STAY_UP_LATE(5, "熬夜"),
    DRAWING(5, "画画"),
    COOKING(5, "烹饪"),
    PLANT(5, "植物"),
    BALANCE(5, "余额"),
    BECOME_BEAUTIFUL(5, "变美"),
    MILK_TEA(5, "奶茶"),
    KEEPING_IN_GOOD_HEALTH(5, "养生"),
    CLOTHES(5, "衣服"),
    OTHER(0, "其它");

    private int code;
    private String name;

    EventEnum(int code, String name) {
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
