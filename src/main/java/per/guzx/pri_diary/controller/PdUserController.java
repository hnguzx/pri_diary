package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.enumeration.UserStateEnum;
import per.guzx.pri_diary.exception.CommonException;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdUser;
import per.guzx.pri_diary.service.PdUserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class PdUserController {

    @Autowired
    private PdUserService userService;

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("insertUser")
    public ApiResp insertUser(@RequestBody PdUser user) throws CommonException {
        PdUser newUser = userService.insertUser(user);
        if (newUser != null) {
            return ApiResp.retOk(newUser);
        }
        return ApiResp.retFail(ErrorEnum.DATA_EXCEPTION);
    }

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResp findUserById(@PathVariable("id") int id) {
        PdUser user = userService.findUserById(id);
        if (user != null) {
            return ApiResp.retOk();
        }
        return ApiResp.retFail(ErrorEnum.USER_NOTFOUND);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{userId}")
    public ApiResp deleteUser(@PathVariable("userId") int id) {
        PdUser user = userService.deleteUser(id);
        if (user != null) {
            return ApiResp.retOk();
        }
        return ApiResp.retFail(ErrorEnum.USER_NOTFOUND);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PatchMapping("/updateUser")
    public ApiResp updateUser(@RequestBody PdUser user) {
        int result = userService.updateUser(user);
        if (result > 0) {
            PdUser newUser = userService.findUserById(user.getUserId());
            return ApiResp.retOk(newUser);
        }
        return ApiResp.retFail(ErrorEnum.UPDATE_INFO_FAIL);
    }

    /**
     * 分页查询用户信息
     * 这个请求类型时特殊的！！！
     *
     * @param user
     * @param start
     * @param limit
     * @return
     */
    @PostMapping("/{start}/{limit}")
    public ApiResp findUsers(@RequestBody PdUser user, @PathVariable("start") int start, @PathVariable("limit") int limit) {
        List<PdUser> users = userService.findUsers(user, start, limit);
        if (users.size() > 0) {
            return ApiResp.retOk(users);
        }
        return null;
    }

    @PatchMapping("/{userId}")
    public ApiResp cancleUser(@PathVariable("userId") int id) {
        PdUser user = userService.cancleUser(id);
        if (user != null) {
            return ApiResp.retOk(user);
        }
        return ApiResp.retFail(ErrorEnum.USER_NOTFOUND);
    }

    /**
     * 判断指定用户是否已激活
     *
     * @param id
     * @return
     */
    public boolean isActivate(int id) {
        if (id != 0) {
            PdUser user = userService.findUserById(id);
            UserStateEnum userStateEnum = user.getUserState();
            return userStateEnum.getId() == 3;
        }
        return false;
    }

}
