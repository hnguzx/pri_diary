package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/4 15:53
 */
@Repository
public interface PubSequenceDao {
    /**
     * 通过交易类型获取指定序列号
     * @param businessType
     * @return
     */
    int getSequence(String businessType);
}
