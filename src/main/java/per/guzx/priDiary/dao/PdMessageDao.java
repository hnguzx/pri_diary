package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.priDiary.pojo.PdMessage;

@Repository
public interface PdMessageDao {

    int save(PdMessage pdMessage);
}