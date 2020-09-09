package per.guzx.pri_diary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.pri_diary.pojo.PdBlog;

@Repository
public interface PdBlogDao {
    int insertBlog(PdBlog pdBlog);
}