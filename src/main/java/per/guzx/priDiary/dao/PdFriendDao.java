package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.priDiary.pojo.PdFriend;

import java.util.List;

@Repository
public interface PdFriendDao {
    /**
     * 新增好友
     *
     * @param pdFriend
     */
    int save(PdFriend pdFriend);

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
    int update(PdFriend pdFriend);

    /**
     * 根据好友id查找好友
     *
     * @param friendUserId
     * @return
     */
    PdFriend findById(int myUserId, int friendUserId);

    /**
     * 根据好友相关信息查询好友
     * @param myUserId
     * @param start
     * @param limit
     * @param global
     * @return
     */
    List<PdFriend> findFriendByInfo(int myUserId, int start, int size, String global);

}