package per.guzx.priDiary.service;


import com.github.pagehelper.PageInfo;
import per.guzx.priDiary.pojo.PdComment;

import java.util.List;

/**
 *
 * @author Guzx
 * @date 2020/09/07
 */
public interface PdCommentService {

    void save(PdComment pdComment);

    void deleteById(Integer id);

    void update(PdComment pdComment);

    PdComment findById(Integer id);

    PageInfo<List<PdComment>> findAll();
}
