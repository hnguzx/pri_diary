package per.guzx.priDiary.serviceImpl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.priDiary.dao.PdBlogDao;
import per.guzx.priDiary.pojo.PdBlog;
import per.guzx.priDiary.service.PdBlogService;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * @author Guzx
 * @date 2020/09/07
 */
@Transactional
@Service
public class PdBlogServiceImpl implements PdBlogService {

    @Resource
    private PdBlogDao pdBlogDao;

    @Override
    public void save(PdBlog pdBlog) {
        int result = pdBlogDao.insert(pdBlog);
        System.out.println("result:" + result);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(PdBlog pdBlog) {

    }

    @Override
    public PdBlog findById(Integer id) {
        return null;
    }

    @Override
    public PageInfo<List<PdBlog>> findAll() {
        return null;
    }
}
