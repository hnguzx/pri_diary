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
        userService.insertUser(user);
        return ApiResp.retOk();
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
        return ApiResp.retOk(user);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{userId}")
    public ApiResp deleteUser(@PathVariable("userId") int id) {
        userService.deleteUser(id);
        return ApiResp.retOk();
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PatchMapping("/updateUser")
    public ApiResp updateUser(@RequestBody PdUser user) {
        userService.updateUser(user);
        PdUser newUser = userService.findUserById(user.getUserId());
        return ApiResp.retOk(newUser);
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
        return ApiResp.retOk(users);
    }

    @PatchMapping("/{userId}")
    public ApiResp cancelUser(@PathVariable("userId") int id) {
        PdUser user = userService.cancelUser(id);
        return ApiResp.retOk(user);
    }

    /**
     * 判断指定用户是否已激活
     *
     * @param code
     * @return
     */
    public boolean isActivate(int code) {
        if (code != 0) {
            PdUser user = userService.findUserById(code);
            UserStateEnum userStateEnum = user.getUserState();
            return userStateEnum.getCode() == 3;
        }
        return false;
    }

}
