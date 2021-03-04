package per.guzx.priDiary.serviceImpl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.priDiary.pojo.PdComment;
import per.guzx.priDiary.service.PdCommentService;

import java.util.List;


/**
 *
 * @author Guzx
 * @date 2020/09/07
 */
@Transactional
@Service
public class PdCommentServiceImpl implements PdCommentService {

    @Override
    public void save(PdComment pdComment) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(PdComment pdComment) {

    }

    @Override
    public PdComment findById(Integer id) {
        return null;
    }

    @Override
    public PageInfo<List<PdComment>> findAll() {
        return null;
    }
}
