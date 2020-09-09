package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdBlogDao;
import per.guzx.pri_diary.pojo.PdBlog;
import per.guzx.pri_diary.service.PdBlogService;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Service
@Transactional
public class PdBlogServiceImpl implements PdBlogService {

    @Autowired
    private PdBlogDao pdBlogDao;

    @Override
    public void save(PdBlog pdBlog) {
        int result = pdBlogDao.insertBlog(pdBlog);
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
    public List<PdBlog> findAll() {
        return null;
    }
}
