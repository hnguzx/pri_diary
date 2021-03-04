package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.priDiary.pojo.PdBlog;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Administrator
 */
@Repository
public interface PdBlogDao extends Mapper<PdBlog> {
}