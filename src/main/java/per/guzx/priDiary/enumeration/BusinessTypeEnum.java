package per.guzx.priDiary.enumeration;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/3 10:27
 *
 *
 */
public enum BusinessTypeEnum {
    // 用户信息
    USER(10001,"UR"),
    // 博客
    BLOG(10002,"BG"),
    // 评论
    COMMENT(10003,"CM"),
    // 日记
    DIARY(10004,"DY"),
    // 消息
    MESSAGE(10005,"MS"),
    // 点赞
    PRAISE(10006,"PR");

    BusinessTypeEnum(Integer businessCode, String businessType){
        this.businessType = businessType;
        this.businessCode = businessCode;
    }

    private Integer businessCode;
    private String businessType;

    public Integer getBusinessCode() {
        return businessCode;
    }

    public String getBusinessType() {
        return businessType;
    }
}
