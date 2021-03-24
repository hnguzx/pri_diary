package per.guzx.priDiary.handle;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import per.guzx.priDiary.pojo.ApiResp;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        String username = parameterMap.get("username")[0];
        redisTemplate.opsForHash().delete("loggedUser", username);
        log.trace("用户退出系统：" + username);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(ApiResp.retOk()));
    }
}
