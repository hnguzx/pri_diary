package per.guzx.pri_diary.controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.enumeration.StateEnum;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdUser;
import per.guzx.pri_diary.pojo.ResultVo;
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
    public ApiResp insertUser(@RequestBody PdUser user) {
        if (user.getUserId() == null) {
            user.setUserState(StateEnum.getStateEnumById(3));
        }
        PdUser newUser = userService.insertUser(user);
        return ApiResp.retOk(newUser);
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
        return ApiResp.retFail(ErrorEnum.ACTIVATION);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ApiResp deleteUser(@PathVariable("id") int id) {
        PdUser user = userService.deleteUser(id);
        if (user != null) {
            return ApiResp.retOk();
        }
        return ApiResp.retFail(ErrorEnum.ACTIVATION);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PatchMapping("/updateUser")
    public ApiResp updateUser(@RequestBody PdUser user) {
        if (user == null || user.getUserId() == null) {
            return ApiResp.retFail(ErrorEnum.ACTIVATION);
        }
        PdUser updateUserAfter = userService.updateUser(user);
        return ApiResp.retOk(updateUserAfter);
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

    @PatchMapping("/{id}")
    public ApiResp cancleUser(@PathVariable("id") int id) {
        PdUser user = userService.cancleUser(id);
        if (user != null) {
            return ApiResp.retOk(user);
        }
        return ApiResp.retFail(ErrorEnum.ACTIVATION);
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
            StateEnum stateEnum = user.getUserState();
            return stateEnum.getId() == 3;
        }
        return false;
    }

    /**
     * 交易处理结果
     *
     * @param isSuccess 是否成功
     * @param message   返回的信息
     * @param object    返回的对象
     * @return
     */
    /*public ResultVo dealResult(boolean isSuccess, String message, Object object) {
        ResultVo result = new ResultVo();
        result.setSuccess(isSuccess);
        result.setMessage(message);
        result.setObject(object);
        return result;
    }*/


}
