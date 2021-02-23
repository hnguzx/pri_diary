package per.guzx.priDiary.service;

import per.guzx.priDiary.pojo.PdMessage;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
public interface PdMessageService {

    boolean sendMsg(PdMessage pdMessage);

    List<PdMessage> findAll();

    PdMessage findById(Integer id);
}
