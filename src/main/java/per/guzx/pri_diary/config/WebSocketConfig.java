package per.guzx.pri_diary.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import per.guzx.pri_diary.handle.MyAuthenticationSuccessHandler;
import per.guzx.pri_diary.serviceImpl.PdUserServiceImpl;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

@Configuration
@Slf4j
@EnableWebSocketMessageBroker
//@EnableWebSecurity
public class WebSocketConfig extends WebSecurityConfigurerAdapter implements WebSocketMessageBrokerConfigurer {

    @Profile({"dev"})
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 注册服务器端点
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 聊天服务端点
        registry.addEndpoint("/ws_chat").withSockJS();
        // 用户服务端点
        registry.addEndpoint("/ws_user").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 客户端订阅路径前缀
        registry.enableSimpleBroker("/sub","/queue");
        // 服务端点请求前缀
        registry.setApplicationDestinationPrefixes("/request");
    }

    @Autowired
    private PdUserServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeRequests().
                antMatchers("/static/**","/common/**","/login/**","/").permitAll().
                antMatchers("/admin/**").hasRole("ADMIN").
                antMatchers("/user/**").hasAnyRole("ADMIN","USER").
//                antMatchers("/user/**").access("hasRole('USER') and hasRole('DBA')").
                anyRequest().authenticated().
                and().anonymous().
                and().rememberMe().tokenValiditySeconds(604800).key("remember-me-key").
                and().formLogin().
                    loginPage("").successHandler(null).defaultSuccessUrl("").
                and().logout().
//                    logoutUrl("").addLogoutHandler(null).logoutSuccessHandler(null).logoutSuccessUrl("").invalidateHttpSession(true).
                and().httpBasic();
//                and().
//                logout().   //开启登出功能
//                    logoutUrl("/logout").   // 登出页面的处理地址
//                    logoutSuccessUrl("/index"). // 登出成功跳转地址
//                    logoutSuccessHandler(null).   // 登出成功后的处理器，与logoutSuccessUrl互斥
//                    invalidateHttpSession(true).    // 登出的时候使session失效，默认为true
//                addLogoutHandler(null).    // 登出的处理器
//                deleteCookies("testCookie", "testCookie2"). // 删除指定cookie
//                permitAll();
    }

//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
}
