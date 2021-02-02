package per.guzx.pri_diary.service;

import org.springframework.stereotype.Service;
import per.guzx.pri_diary.pojo.PdMessage;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Service
public interface PdMessageService {

    boolean sendMsg(PdMessage pdMessage);

    List<PdMessage> findAll();

    PdMessage findById(Integer id);
}
