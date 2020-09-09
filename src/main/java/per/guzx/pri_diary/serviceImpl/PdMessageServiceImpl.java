package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdMessageDao;
import per.guzx.pri_diary.pojo.PdMessage;
import per.guzx.pri_diary.service.PdMessageService;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Service
@Transactional
public class PdMessageServiceImpl implements PdMessageService {

    @Override
    public void save(PdMessage pdMessage) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(PdMessage pdMessage) {

    }

    @Override
    public List<PdMessage> findAll() {
        return null;
    }

    @Override
    public PdMessage findById(Integer id) {
        return null;
    }
}
