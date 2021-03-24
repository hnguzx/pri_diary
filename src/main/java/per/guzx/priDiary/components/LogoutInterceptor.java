package per.guzx.priDiary.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/3/24 17:33
 * @describe
 */
@Slf4j
//@Component
public class LogoutInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.trace("用户退出登录");
        String username = request.getParameter("username");
        redisTemplate.opsForHash().delete("loggedUser", request.getParameter("username"));
        return true;
    }
}
