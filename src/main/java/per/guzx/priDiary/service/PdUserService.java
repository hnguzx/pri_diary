package per.guzx.priDiary.service;

import com.github.pagehelper.PageInfo;
import per.guzx.priDiary.pojo.PdUser;

import java.util.List;

/**
 * @author Administrator
 */
public interface PdUserService {
    PdUser insertUser(PdUser user);

    PdUser login(PdUser user);

    int updateUser(PdUser user);

    PdUser findUserById(int id);

    PdUser deleteUser(int id);

    PdUser cancelUser(int id);

    PageInfo<List<PdUser>> findUsers(PdUser user, int start, int size);

    int findUserCount(PdUser user);

    int updateUserByEmailOrPhone(PdUser user);

    PdUser findByName(String username);
}
