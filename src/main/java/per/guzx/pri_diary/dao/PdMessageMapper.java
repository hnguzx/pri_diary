package per.guzx.pri_diary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.pri_diary.core.MyBatisMapper;
import per.guzx.pri_diary.pojo.PdMessage;

@Repository
public interface PdMessageMapper extends MyBatisMapper<PdMessage> {
}