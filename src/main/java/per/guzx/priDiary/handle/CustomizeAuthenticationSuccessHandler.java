package per.guzx.priDiary.handle;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import per.guzx.priDiary.pojo.ApiResp;
import per.guzx.priDiary.pojo.PdUser;
import per.guzx.priDiary.service.PdUserService;
import per.guzx.priDiary.tool.DateUtil;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功逻辑处理
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private PdUserService userService;

    @Resource
    private DateUtil dateUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间、更新人、更新时间等字段
        PdUser user = (PdUser) authentication.getPrincipal();
        user.setUserLastLoginTime(dateUtil.getTimeStamp());
        user.setUserUpdateTime(dateUtil.getTimeStamp());
        userService.updateUser(user);

        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展

        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(ApiResp.retOk(user)));
    }
}
