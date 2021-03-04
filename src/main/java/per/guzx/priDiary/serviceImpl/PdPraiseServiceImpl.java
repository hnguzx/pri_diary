package per.guzx.priDiary.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.priDiary.dao.PdPraiseDao;
import per.guzx.priDiary.pojo.PdPraise;
import per.guzx.priDiary.service.PdPraiseService;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * @author Guzx
 * @date 2020/09/07
 */
@Transactional
@Service
public class PdPraiseServiceImpl implements PdPraiseService {

    @Resource
    private PdPraiseDao pdPraiseDao;

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
    public PageInfo<List<PdPraise>> findAll() {
        return null;
    }
}
