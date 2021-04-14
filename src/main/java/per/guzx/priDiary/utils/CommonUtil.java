package per.guzx.priDiary.utils;

import org.springframework.stereotype.Component;
import per.guzx.priDiary.dao.PubSequenceDao;
import per.guzx.priDiary.enumeration.BusinessTypeEnum;
import per.guzx.priDiary.enumeration.EntityEnum;

import javax.annotation.Resource;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/3 10:23
 */
@Component
public class CommonUtil {

    @Resource
    private DateUtil dateUtil;

    @Resource
    private PubSequenceDao sequenceDao;

    /**
     * 根据实体类型生成主键
     * @param entityEnum
     * @return
     */
    public String generatePrimaryKey(EntityEnum entityEnum) {
        return entityEnum.getEntityName() + dateUtil.getTimeStamp();
    }

    /**
     * 根据交易类型生成流水号
     * @param businessTypeEnum
     * @return
     */
    public String generateFlowNo(BusinessTypeEnum businessTypeEnum) {
        return businessTypeEnum.getBusinessCode() + dateUtil.getDateStamp() + businessTypeEnum.getBusinessType() + sequenceDao.getSequence(businessTypeEnum.getBusinessType());
    }

}
