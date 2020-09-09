package per.guzx.pri_diary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.guzx.pri_diary.dao.PdFriendDao;
import per.guzx.pri_diary.pojo.PdFriend;
import per.guzx.pri_diary.service.PdFriendService;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
@Service
@Transactional
public class PdFriendServiceImpl implements PdFriendService {

    @Override
    public void save(PdFriend pdFriend) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(PdFriend pdFriend) {

    }

    @Override
    public PdFriend findById(Integer id) {
        return null;
    }

    @Override
    public List<PdFriend> findAll() {
        return null;
    }
}
