package per.guzx.pri_diary.service;

import per.guzx.pri_diary.pojo.PageInfo;
import per.guzx.pri_diary.pojo.PdFriend;
import per.guzx.pri_diary.pojo.PdUser;

import java.util.List;


/**
 * Created by Guzx on 2020/09/07.
 */
public interface PdFriendService {

    /**
     * 新增好友
     *
     * @param pdFriend
     */
    PdFriend save(PdFriend pdFriend);

    /**
     * 删除好友
     *
     * @param id
     */
    int deleteById(Integer id);

    /**
     * 更新好友信息
     *
     * @param pdFriend
     */
    PdFriend update(PdFriend pdFriend);

    /**
     * 根据id查找好友
     *
     * @param id
     * @return
     */
    PdFriend findById(Integer id);

    /**
     * 通过模糊条件
     * @param myUserId
     * @param start
     * @param limit
     * @param global
     * @return
     */
    PageInfo findFriendByInfo(int myUserId, int start, int size, String global);
}
