package per.guzx.priDiary.dao;

import org.springframework.stereotype.Repository;
import per.guzx.priDiary.pojo.PdFriend;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface PdFriendDao extends Mapper<PdFriend> {
    int save(PdFriend pdFriend);

    int deleteById(Integer id);

    int update(PdFriend pdFriend);

    PdFriend findById(int myUserId, int friendUserId);

    List<PdFriend> findFriendByInfo(int myUserId, int start, int size, String global);
}