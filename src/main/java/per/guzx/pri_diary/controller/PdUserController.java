package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.enumeration.UserStateEnum;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdUser;
import per.guzx.pri_diary.service.PdUserService;
import per.guzx.pri_diary.tool.EmailOrMsg;
import per.guzx.pri_diary.tool.JSR_303;
import per.guzx.pri_diary.tool.VerifyCodeFactory;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class PdUserController {

    @Autowired
    private PdUserService userService;

    @Autowired
    private EmailOrMsg emailOrMsg;

    @Autowired
    private VerifyCodeFactory verifyCodeFactory;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("insertUser/{verifyCode}")
    public ApiResp insertUser(@Valid @RequestBody PdUser user, @PathVariable("verifyCode") String verifyCode, Errors errors) {
        Map<String, Object> validResult = JSR_303.validator(errors);
        if (validResult.isEmpty()) {
            userService.insertUser(user);
            return ApiResp.retOk();
        }
        return ApiResp.retFail(ErrorEnum.DATA_VALIDATE, validResult);
    }

    /**
     * @param email
     * @return
     */
    @GetMapping("/verifyCode/{email}")
    public ApiResp getRegisterVerifyCode(@PathVariable("email") String email) {
        String verifyCode = verifyCodeFactory.getCode();
        redisTemplate.opsForValue().set("verifyCode::" + email, verifyCode, 60);

//        String code = (String) redisTemplate.opsForValue().get("verifyCode::" + email);
//        System.out.println(code);
        emailOrMsg.sendVerifyCodeEmail(email, verifyCode);
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

    /**
     * 用户注销
     *
     * @param id
     * @return
     */
    @PatchMapping("/{userId}")
    public ApiResp cancelUser(@PathVariable("userId") int id) {
        PdUser user = userService.cancelUser(id);
        return ApiResp.retOk(user);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ApiResp login(@RequestBody PdUser user) {
        PdUser newUser = userService.login(user);
        return ApiResp.retOk(newUser);
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
