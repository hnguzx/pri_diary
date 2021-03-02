package per.guzx.priDiary.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import per.guzx.priDiary.enumeration.ErrorEnum;
import per.guzx.priDiary.enumeration.UserStateEnum;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdUser;
import per.guzx.priDiary.service.PdUserService;
import per.guzx.priDiary.tool.EmailOrMsg;
import per.guzx.priDiary.tool.Validator;
import per.guzx.priDiary.tool.VerifyCodeFactory;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 用户接口
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户")
public class UserController {

    @Resource
    private PdUserService userService;

    @Resource
    private EmailOrMsg emailOrMsg;

    @Resource
    private VerifyCodeFactory verifyCodeFactory;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private Validator validator;


    /**
     * 用户注册
     *
     * @param user
     * @param verifyCode
     * @return
     */
    @PostMapping("insertUser/{verifyCode}")
    @ApiOperation("用户注册")
    public ApiResp insertUser(@RequestBody PdUser user, @PathVariable("verifyCode") String verifyCode) {
        if (checkVerifyCode(user, verifyCode)) {
            userService.insertUser(user);
            return ApiResp.retOk(user);
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
    @ApiOperation("获取验证码")
    public ApiResp getRegisterVerifyCode(@PathVariable("emailOrPhone") String emailOrPhone) {
        String verifyCode = verifyCodeFactory.getCode();
        if (emailOrMsg.isEmail(emailOrPhone)) {
            emailOrMsg.sendVerifyCodeEmail(emailOrPhone, verifyCode);
        } else {
            emailOrMsg.sendVerifyCodeMsg(emailOrPhone, verifyCode);
        }
        redisTemplate.opsForValue().set("verifyCode::" + emailOrPhone, verifyCode, 600, TimeUnit.SECONDS);

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
        if (!verifyCode.equals(serverCode)) {
            return false;
        }
        redisTemplate.opsForValue().set("verifyCode::" + receiverPath, verifyCode, 1, TimeUnit.SECONDS);
        return true;
    }


    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    @ApiOperation("查找用户")
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
    @ApiOperation("删除用户")
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
    @ApiOperation("重置密码获取验证码")
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
    @ApiOperation("重置密码")
    public ApiResp forgetPassword(@RequestBody PdUser user, @PathVariable("verifyCode") String verifyCode) {
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
    @ApiOperation("更新用户信息")
    public ApiResp<PdUser> updateUser(@RequestBody PdUser user) {
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
     * @param size
     * @return
     */
    @PostMapping("/{start}/{size}")
    @ApiOperation("查询用户列表")
    public ApiResp<PageInfo> findUsers(@RequestBody PdUser user, @PathVariable("start") int start, @PathVariable("size") int size) {
        PageInfo result = userService.findUsers(user, start, size);
        return ApiResp.retOk(result);
    }

    /**
     * 用户注销
     *
     * @param id
     * @return
     */
    @PatchMapping("/{userId}")
    @ApiOperation("用户注销")
    public ApiResp<PdUser> cancelUser(@PathVariable("userId") int id) {
        PdUser user = userService.cancelUser(id);
        return ApiResp.retOk(user);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    /*@PostMapping("/login")
    public ApiResp<PdUser> login(@RequestBody PdUser user) {
        PdUser newUser = userService.login(user);
        return ApiResp.retOk(newUser);
    }*/

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
