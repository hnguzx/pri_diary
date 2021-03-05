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

    Integer save(PdBlog pdBlog);

    Integer deleteById(Integer id);

    Integer update(PdBlog pdBlog);

    PdBlog findById(Integer id);

    PageInfo<List<PdBlog>> findAll();
}
