package per.guzx.priDiary.service;


import com.github.pagehelper.PageInfo;
import per.guzx.priDiary.pojo.PdBlog;

import java.util.List;

/**
 *
 * @author Guzx
 * @date 2020/09/07
 */
public interface PdBlogService {

    void save(PdBlog pdBlog);

    void deleteById(Integer id);

    void update(PdBlog pdBlog);

    PdBlog findById(Integer id);

    PageInfo<List<PdBlog>> findAll();
}
