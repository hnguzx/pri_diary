package per.guzx.priDiary.service;


import per.guzx.priDiary.pojo.PdComment;

import java.util.List;

/**
 * Created by Guzx on 2020/09/07.
 */
public interface PdCommentService {

    void save(PdComment pdComment);

    void deleteById(Integer id);

    void update(PdComment pdComment);

    PdComment findById(Integer id);

    List<PdComment> findAll();
}
