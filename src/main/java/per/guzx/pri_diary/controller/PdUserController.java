package per.guzx.pri_diary.controller;

import io.github.yedaxia.apidocs.ApiDoc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import per.guzx.pri_diary.pojo.PageInfo;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.enumeration.UserStateEnum;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdUser;
import per.guzx.pri_diary.service.PdUserService;
import per.guzx.pri_diary.tool.EmailOrMsg;
import per.guzx.pri_diary.tool.Validator;
import per.guzx.pri_diary.tool.VerifyCodeFactory;

import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户接口
 */
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

    @Autowired
    private Validator validator;


    /**
     * 用户注册
     *
     * @param user
     * @param verifyCode
     * @param errors
     * @return
     */
    @PostMapping("insertUser/{verifyCode}")
    @ApiDoc()
    public ApiResp insertUser(@Valid @RequestBody PdUser user, @PathVariable("verifyCode") String verifyCode, Errors errors) {
        if (checkVerifyCode(user, verifyCode)) {
            Map<String, Object> validResult = validator.validator(errors);
            if (validResult.isEmpty()) {
                userService.insertUser(user);
                return ApiResp.retOk(user);
            }
            return ApiResp.retFail(ErrorEnum.DATA_VALIDATE, validResult);
        }
        return ApiResp.retFail(ErrorEnum.VERIFY_ERROR);

    }

    /**
     * 获取验证码
     *
     * @param emailOrPhone
     * @return
     */
    @GetMapping("/verifyCode/{emailOrPhone}")
    public ApiResp getRegisterVerifyCode(@PathVariable("emailOrPhone") String emailOrPhone) {
        String verifyCode = verifyCodeFactory.getCode();
        if (emailOrMsg.isEmail(emailOrPhone)) {
            emailOrMsg.sendVerifyCodeEmail(emailOrPhone, verifyCode);
        } else {
            emailOrMsg.sendVerifyCodeMsg(emailOrPhone, verifyCode);
        }
        redisTemplate.opsForValue().set("verifyCode::" + emailOrPhone, verifyCode, 300, TimeUnit.SECONDS);

        return ApiResp.retOk();
    }

    /**
     * 校验验证码
     *
     * @param receiverUser
     * @param verifyCode
     * @return
     */
    public boolean checkVerifyCode(PdUser receiverUser, String verifyCode) {
        String receiverPath = "";

        if (receiverUser.getUserPhone() != "" && receiverUser.getUserPhone() != null) {
            receiverPath = receiverUser.getUserPhone();
        } else {
            receiverPath = receiverUser.getUserEmail();
        }
        String serverCode = (String) redisTemplate.opsForValue().get("verifyCode::" + receiverPath);
        if (!serverCode.equals(verifyCode)) {
            return false;
        }
        redisTemplate.opsForValue().set("verifyCode::" + receiverPath, verifyCode, 1, TimeUnit.SECONDS);
        return true;
    }


    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ApiResp<PdUser> findUserById(@PathVariable("id") int id) {
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
     * 重置密码获取验证码
     *
     * @param emailOrPhone
     * @return
     */
    @GetMapping("/resetPassword/verifyCode/{emailOrPhone}")
    public ApiResp forgetPasswordGetVerifyCode(@PathVariable("emailOrPhone") String emailOrPhone) {
        PdUser user = new PdUser();
        String verifyCode = verifyCodeFactory.getCode();
        if (emailOrMsg.isEmail(emailOrPhone)) {
            user.setUserEmail(emailOrPhone);
            int result = userService.findUserCount(user);
            if (result == 0) {
                return ApiResp.retFail(ErrorEnum.USER_NOTFOUND);
            }
            emailOrMsg.sendVerifyCodeEmail(emailOrPhone, verifyCode);
        } else {
            emailOrMsg.sendVerifyCodeMsg(emailOrPhone, verifyCode);
            user.setUserPhone(emailOrPhone);
            int result = userService.findUserCount(user);
            if (result == 0) {
                return ApiResp.retFail(ErrorEnum.USER_NOTFOUND);
            }
            emailOrMsg.sendVerifyCodeMsg(emailOrPhone, verifyCode);
        }
        redisTemplate.opsForValue().set("verifyCode::" + emailOrPhone, verifyCode, 300, TimeUnit.SECONDS);
        return ApiResp.retOk();
    }

    /**
     * 重置密码
     *
     * @param user
     * @param verifyCode
     * @return
     */
    @PatchMapping("/resetPassword/{verifyCode}")
    public ApiResp<ErrorEnum> forgetPassword(@RequestBody PdUser user, @PathVariable("verifyCode") String verifyCode) {
        int result = userService.findUserCount(user);
        if (result > 0) {
            if (checkVerifyCode(user, verifyCode)) {
                userService.updateUserByEmailOrPhone(user);
                return ApiResp.retOk();
            }
            return ApiResp.retFail(ErrorEnum.VERIFY_ERROR);
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
    public ApiResp<PageInfo> findUsers(@RequestBody PdUser user, @PathVariable("start") int start, @PathVariable("limit") int limit) {
        PageInfo result = userService.findUsers(user, start, limit);
        return ApiResp.retOk(result);
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
    public ApiResp<PdUser> login(@RequestBody PdUser user) {
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
