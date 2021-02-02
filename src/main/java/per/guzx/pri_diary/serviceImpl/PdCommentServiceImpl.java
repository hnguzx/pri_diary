package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdCommentDao;
import per.guzx.pri_diary.pojo.PdComment;
import per.guzx.pri_diary.service.PdCommentService;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Transactional
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
    public List<PdComment> findAll() {
        return null;
    }
}
