package per.guzx.pri_diary.service;


import per.guzx.pri_diary.pojo.PdComment;

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
