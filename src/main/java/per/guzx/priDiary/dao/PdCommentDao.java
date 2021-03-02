package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.priDiary.pojo.PdComment;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Administrator
 */
@Repository
public interface PdCommentDao extends Mapper<PdComment> {
}