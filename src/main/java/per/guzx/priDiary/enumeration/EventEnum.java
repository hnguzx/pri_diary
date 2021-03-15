package per.guzx.priDiary.enumeration;

/**
 * @author Administrator
 */

public enum EventEnum {

    // 电影
    MOVIE(1, "电影"),
    GAME(2, "游戏"),
    FAMILY(3, "家庭"),
    TOURISM(4, "旅游"),
    RELATIONSHIP(5, "关系"),
    FOOD(6, "食物"),
    MUSIC(7, "音乐"),
    WORK(8, "工作"),
    REST(9, "休息"),
    LOVE(10, "爱情"),
    SHOPPING(11, "逛街"),
    ADVENTURE(12, "冒险"),
    IMAGINATION(13, "脑洞"),
    LEARNING(14, "学习"),
    READING(15, "阅读"),
    MOVEMENT(16, "运动"),
    ADJUST(17, "调整"),
    FITNESS(18, "健身"),
    SICK(19, "生病"),
    STAR(20, "爱豆"),
    PET(21, "宠物"),
    AFTER_PLAY(22, "追剧"),
    CARTOON(23, "动漫"),
    INTERNET(24, "上网"),
    ONLINE_SHOPPING(25, "网购"),
    EAT_THE_MELON(26, "吃瓜"),
    STAY_UP_LATE(27, "熬夜"),
    DRAWING(28, "画画"),
    COOKING(29, "烹饪"),
    PLANT(30, "植物"),
    BALANCE(31, "余额"),
    BECOME_BEAUTIFUL(32, "变美"),
    MILK_TEA(33, "奶茶"),
    KEEPING_IN_GOOD_HEALTH(34, "养生"),
    CLOTHES(35, "衣服"),
    OTHER(36, "其它");

    private int code;
    private String name;

    EventEnum(int code, String name) {
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
