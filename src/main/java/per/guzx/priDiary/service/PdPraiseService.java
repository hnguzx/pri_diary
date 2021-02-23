package per.guzx.priDiary.service;

import per.guzx.priDiary.pojo.PdPraise;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
public interface PdPraiseService {

    void save(PdPraise pdPraise);

    void deleteById(Integer id);

    void update(PdPraise pdPraise);

    PdPraise findById(Integer id);

    List<PdPraise> findAll();
}
