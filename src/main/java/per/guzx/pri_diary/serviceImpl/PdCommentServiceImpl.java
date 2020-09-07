package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.core.AbstractService;
import per.guzx.pri_diary.dao.PdCommentMapper;
import per.guzx.pri_diary.pojo.PdComment;
import per.guzx.pri_diary.service.PdCommentService;

import javax.annotation.Resource;


/**
 * Created by Guzx on 2020/09/07.
 */
@Service
@Transactional
public class PdCommentServiceImpl extends AbstractService<PdComment> implements PdCommentService {
    @Autowired
    private PdCommentMapper pdCommentMapper;

}
