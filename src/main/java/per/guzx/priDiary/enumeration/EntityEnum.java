package per.guzx.priDiary.enumeration;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/4 16:40
 * @describe 项目中所有的实体枚举
 */
public enum EntityEnum {
    // 用户
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

    public String getEntityDesc() {
        return entityDesc;
    }
}
