package per.guzx.pri_diary.service;

import per.guzx.pri_diary.pojo.PdFriend;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
public interface PdFriendService {

    /**
     * 新增好友
     * @param pdFriend
     */
    PdFriend save(PdFriend pdFriend);

    /**
     * 删除好友
     * @param id
     */
    int deleteById(Integer id);

    /**
     * 更新好友信息
     * @param pdFriend
     */
    PdFriend update(PdFriend pdFriend);

    /**
     * 根据id查找好友
     * @param id
     * @return
     */
    PdFriend findById(Integer id);

    /**
     * 根据好友相关信息查询好友
     * @param friend
     * @return
     */
    PdFriend findByFriend(PdFriend friend);

    /**
     * 获取所有好友信息
     * @return
     */
    List<PdFriend> findAll();
}
