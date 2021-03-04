package per.guzx.priDiary.enumeration;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/3 10:27
 *
 *
 */
public enum BusinessTypeEnum {
    /**
     * 用户相关交易
     */
    USER(10001,"UR"),
    BLOG(10002,"BG"),
    COMMENT(10003,"CM"),
    DIARY(10004,"DY"),
    MESSAGE(10005,"MS"),
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

    public void setBusinessCode(Integer businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
