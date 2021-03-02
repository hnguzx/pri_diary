package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.priDiary.pojo.PdMessage;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Administrator
 */
@Repository
public interface PdMessageDao extends Mapper<PdMessage> {

    /**
     * 保存消息
     * @param pdMessage
     * @return
     */
    int save(PdMessage pdMessage);
}