package per.guzx.pri_diary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class WebMvcConfig extends WebMvcConfigurerAdapter implements AsyncConfigurer {

    /**
     * 文件请求相关
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 和页面相关的静态文件
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        // 服务器路径
        registry.addResourceHandler("/File/**").addResourceLocations("file:/home/files/");
        // 本地路径
//        registry.addResourceHandler("/File/**").addResourceLocations("file:F:/Files/");

    }

    /**
     * 异步线程池相关
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(30);
        taskExecutor.setQueueCapacity(1000);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Redis序列化相关
     *
     * @return
     */
    @PostConstruct
    public void redisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    /**
     * 跨域问题
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 配置可以被跨域的路径，可以具体到请求路径
                .allowedOrigins("*")        // 允许访问本网站的域名，可以多个
                .allowCredentials(true)     // 是否可以将请求的响应暴露给页面
                .allowedMethods("GET", "POST", "DELETE", "PATCH")       // 允许进行跨域请求方式，可以多个
                .allowedHeaders("*")        // 允许进行跨域请求的header
                .maxAge(60 * 60);     // 客户端缓存预检请求的响应时间？
        super.addCorsMappings(registry);
    }
}
