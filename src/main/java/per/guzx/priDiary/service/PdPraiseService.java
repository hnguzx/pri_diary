package per.guzx.priDiary.service;

import com.github.pagehelper.PageInfo;
import per.guzx.priDiary.pojo.PdPraise;

import java.util.List;


/**
 *
 * @author Guzx
 * @date 2020/09/07
 */
public interface PdPraiseService {

    void save(PdPraise pdPraise);

    void deleteById(Integer id);

    void update(PdPraise pdPraise);

    PdPraise findById(Integer id);

    PageInfo<List<PdPraise>> findAll();
}
