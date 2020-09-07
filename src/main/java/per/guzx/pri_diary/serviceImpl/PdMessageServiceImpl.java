package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.core.AbstractService;
import per.guzx.pri_diary.dao.PdMessageMapper;
import per.guzx.pri_diary.pojo.PdMessage;
import per.guzx.pri_diary.service.PdMessageService;

import javax.annotation.Resource;


/**
 * Created by Guzx on 2020/09/07.
 */
@Service
@Transactional
public class PdMessageServiceImpl extends AbstractService<PdMessage> implements PdMessageService {
    @Autowired
    private PdMessageMapper pdMessageMapper;

}
