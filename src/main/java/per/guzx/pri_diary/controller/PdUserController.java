package per.guzx.pri_diary.controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.enumeration.StateEnum;
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
    public ResultVo insertUser(@RequestBody PdUser user) {
        if (user.getUserId() == null) {
            user.setUserState(StateEnum.getStateEnumById(3));
        }
        PdUser newUser = userService.insertUser(user);
        return dealResult(true, "新增用户成功!!!", newUser);
    }

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVo findUserById(@PathVariable("id") int id) {
        PdUser user = userService.findUserById(id);
        if (user != null) {
            return dealResult(true, "查询到用户：" + user.getUserName(), null);
        }
        return dealResult(true, "用户不存在", null);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultVo deleteUser(@PathVariable("id") int id) {
        PdUser user = userService.deleteUser(id);
        if (user != null) {
            return dealResult(true, "用户：" + user.getUserName() + "删除成功", null);
        }
        return dealResult(true, "删除失败", null);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PatchMapping("/updateUser")
    public ResultVo updateUser(@RequestBody PdUser user) {
        if (user == null || user.getUserId() == null) {
            return dealResult(true, "要更新的用户信息不存在", null);
        }
        PdUser updateUserAfter = userService.updateUser(user);
        return dealResult(true, "更新用户信息成功", updateUserAfter);
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
    public ResultVo findUsers(@RequestBody PdUser user, @PathVariable("start") int start, @PathVariable("limit") int limit) {
        List<PdUser> users = userService.findUsers(user, start, limit);
        if (users.size() > 0) {
            return dealResult(true, "获取到的用户信息有：", users);
        }
        return null;
    }

    @PatchMapping("/{id}")
    public ResultVo cancleUser(@PathVariable("id") int id) {
        PdUser user = userService.cancleUser(id);
        if (user != null) {
            return dealResult(true, "用户：" + user.getUserName() + "已注销", user);
        }
        return null;
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
     * @param isSuccess
     * @param message
     * @param object
     * @return
     */
    public ResultVo dealResult(boolean isSuccess, String message, Object object) {
        ResultVo result = new ResultVo();
        result.setSuccess(isSuccess);
        result.setMessage(message);
        result.setObject(object);
        return result;
    }


}
