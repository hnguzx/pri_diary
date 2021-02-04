package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdPraiseDao;
import per.guzx.pri_diary.pojo.PdPraise;
import per.guzx.pri_diary.service.PdPraiseService;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Transactional
@Service
public class PdPraiseServiceImpl implements PdPraiseService {

    @Override
    public void save(PdPraise pdPraise) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(PdPraise pdPraise) {

    }

    @Override
    public PdPraise findById(Integer id) {
        return null;
    }

    @Override
    public List<PdPraise> findAll() {
        return null;
    }
}
