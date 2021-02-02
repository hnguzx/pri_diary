package per.guzx.pri_diary.service;


import org.springframework.stereotype.Service;
import per.guzx.pri_diary.pojo.PdBlog;

import java.util.List;

/**
 * Created by Guzx on 2020/09/07.
 */
@Service
public interface PdBlogService {

    void save(PdBlog pdBlog);

    void deleteById(Integer id);

    void update(PdBlog pdBlog);

    PdBlog findById(Integer id);

    List<PdBlog> findAll();
}
