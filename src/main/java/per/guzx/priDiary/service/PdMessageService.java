package per.guzx.priDiary.service;

import com.github.pagehelper.PageInfo;
import per.guzx.priDiary.pojo.PdMessage;

import java.util.List;


/**
 *
 * @author Guzx
 * @date 2020/09/07
 */
public interface PdMessageService {

    boolean sendMsg(PdMessage pdMessage);

    PageInfo<List<PdMessage>> findAll();

    PdMessage findById(Integer id);
}
