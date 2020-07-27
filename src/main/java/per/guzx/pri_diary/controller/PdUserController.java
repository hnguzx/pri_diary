package per.guzx.pri_diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.guzx.pri_diary.enumeration.StateEnum;
import per.guzx.pri_diary.pojo.PdUser;
import per.guzx.pri_diary.pojo.ResultVo;
import per.guzx.pri_diary.service.PdUserService;

@Controller
@RequestMapping("/user")
public class PdUserController {

    @Autowired
    private PdUserService userService;

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("insertUser")
    public ResultVo insertUser(@RequestBody PdUser user) {
        if (user.getUserId() == null) {
            user.setUserState(StateEnum.getStateEnumById(3));
        }
        PdUser newUser = userService.insertUser(user);
        return dealResult(true, "新增用户成功!!!", newUser);
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
