package per.guzx.priDiary.service;

import com.github.pagehelper.PageInfo;
import per.guzx.priDiary.pojo.PdFriend;

import java.util.List;


/**
 *
 * @author Guzx
 * @date 2020/09/07
 */
public interface PdFriendService {

    /**
     * 新增好友
     *
     * @param pdFriend
     */
    boolean save(PdFriend pdFriend);

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
     * @param myUserId 本人id
     * @param friendUserId 好友id
     * @return
     */
    PdFriend findById(int myUserId,int friendUserId);

    /**
     * 通过模糊条件
     * @param myUserId
     * @param start
     * @param size
     * @param global
     * @return
     */
    PageInfo<List<PdFriend>> findFriendByInfo(int myUserId, int start, int size, String global);
}
