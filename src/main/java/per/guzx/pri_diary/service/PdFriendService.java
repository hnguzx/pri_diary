package per.guzx.pri_diary.service;

import per.guzx.pri_diary.pojo.PdFriend;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
public interface PdFriendService {

    void save(PdFriend pdFriend);

    void deleteById(Integer id);

    void update(PdFriend pdFriend);

    PdFriend findById(Integer id);

    List<PdFriend> findAll();
}
