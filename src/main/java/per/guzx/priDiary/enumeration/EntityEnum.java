package per.guzx.priDiary.enumeration;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/4 16:40
 * @describe 项目中所有的实体枚举
 */
public enum EntityEnum {
    /**
     * 用户实体
     */
    USER("PDUSER","用户");

    EntityEnum(String entityName, String entityDesc){
        this.entityName = entityName;
        this.entityDesc = entityDesc;
    }

    private String entityName;
    private String entityDesc;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityDesc() {
        return entityDesc;
    }

    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc;
    }
}
