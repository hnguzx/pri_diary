package per.guzx.priDiary.serviceImpl;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.priDiary.dao.PdBlogDao;
import per.guzx.priDiary.enumeration.ErrorEnum;
import per.guzx.priDiary.exception.ServiceException;
import per.guzx.priDiary.pojo.PdBlog;
import per.guzx.priDiary.service.PdBlogService;
import per.guzx.priDiary.tool.DateUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


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

    @Resource
    private DateUtil dateUtil;

    @Override
    public Integer save(PdBlog pdBlog) {
        pdBlog.setBlogCreateTime(dateUtil.getTimeStamp());
        pdBlog.setBlogUpdateTime(dateUtil.getTimeStamp());
        int result = pdBlogDao.insert(pdBlog);
        return result;
    }

    @Override
    public Integer deleteById(Integer id) {
        int result = pdBlogDao.deleteByPrimaryKey(id);
        if (result>0){
            return result;
        }
        throw new ServiceException(ErrorEnum.UPDATE_INFO_FAIL);
    }

    @Override
    public Integer update(PdBlog pdBlog) {
        pdBlog.setBlogUpdateTime(dateUtil.getTimeStamp());
        int result = pdBlogDao.updateByPrimaryKeySelective(pdBlog);
        if (result>0){
            return result;
        }
        throw new ServiceException(ErrorEnum.UPDATE_INFO_FAIL);
    }

    @Override
    public PdBlog findById(Integer id) {
        PdBlog blog = pdBlogDao.selectByPrimaryKey(id);
        if (!Objects.isNull(blog)){
            return blog;
        }
        return null;
    }

    @Override
    public PageInfo<List<PdBlog>> findAll() {
        return null;
    }
}
