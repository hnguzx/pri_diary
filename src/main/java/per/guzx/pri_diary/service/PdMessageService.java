package per.guzx.pri_diary.service;

import per.guzx.pri_diary.pojo.PdMessage;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
public interface PdMessageService {

    void save(PdMessage pdMessage);

    void deleteById(Integer id);

    void update(PdMessage pdMessage);

    List<PdMessage> findAll();

    PdMessage findById(Integer id);
}
