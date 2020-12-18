package per.guzx.pri_diary.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import per.guzx.pri_diary.handle.*;
import per.guzx.pri_diary.serviceImpl.PdUserServiceImpl;

@Configuration
@Slf4j
@EnableWebSocketMessageBroker
//@EnableWebSecurity
public class WebSocketConfig extends WebSecurityConfigurerAdapter implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private PdUserServiceImpl userDetailsService;

    @Autowired
    private CustomizeLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomizeAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;

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
        registry.addEndpoint("/webSocketServer").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 客户端订阅路径前缀
        // queue点对点，topic群发
        registry.enableSimpleBroker("/queue","/topic");
        // 客户端请求服务端点请求前缀
        registry.setApplicationDestinationPrefixes("/webSocketRequest");
        // 点对点使用的订阅前缀
        registry.setUserDestinationPrefix("/user/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.headers().frameOptions().sameOrigin().disable();
        http.authorizeRequests().
                antMatchers("/static/**","/common/**","/demo/**").permitAll().
                antMatchers("/user/verifyCode/**","/user/insertUser/**","/user/resetPassword/**").permitAll().
//                antMatchers("/admin/**").hasRole("ADMIN").
//                antMatchers("/user/**").hasAnyRole("ADMIN","USER").
//                anyRequest().authenticated().
                anyRequest().permitAll().
                and().anonymous().
                and().rememberMe().tokenValiditySeconds(604800).key("remember-me-key").
                and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).
                and().formLogin().permitAll().successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).
                and().logout().permitAll().logoutSuccessHandler(logoutSuccessHandler).deleteCookies("JSESSIONID").
                and().httpBasic().
                and().sessionManagement().maximumSessions(1).expiredSessionStrategy(sessionInformationExpiredStrategy);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
