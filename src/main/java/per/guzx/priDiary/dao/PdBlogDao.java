package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.priDiary.pojo.PdBlog;

@Repository
public interface PdBlogDao {
    int insertBlog(PdBlog pdBlog);
}