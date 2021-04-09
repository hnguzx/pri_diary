package per.guzx.priDiary.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.*;
import per.guzx.priDiary.components.LoginInterceptor;
import per.guzx.priDiary.components.LogoutInterceptor;
import per.guzx.priDiary.enumeration.ErrorEnum;
import per.guzx.priDiary.exception.ServiceException;
import per.guzx.priDiary.pojo.ApiResp;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;


@Configuration
@EnableAsync
@Slf4j
public class WebMvcConfig implements AsyncConfigurer, WebMvcConfigurer {

    @Value("${spring.profiles.active}")
    private String env;

    @Value("${image.baseImagePath}")
    private String imageBasePath;

    /**
     * 文件请求相关
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 和页面相关的静态文件
        // registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // addResourceHandler是指你想在url请求的路径
        // addResourceLocations是图片存放的真实路
        // 本地路径
        log.debug("本地文件系统地址" + imageBasePath);
        registry.addResourceHandler("/File/**").addResourceLocations("file:" + imageBasePath);

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
        redisTemplate.setHashValueSerializer(stringSerializer);
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
//                .exposedHeaders("Content-Type")     // 前端可以获得的额外响应头详细
                .maxAge(60 * 60 * 24);     // 客户端缓存预检请求的响应时间
    }

    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//保留空的字段
        //SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
        //SerializerFeature.WriteNullNumberAsZero//Number null -> 0
        // 按需配置，更多参考FastJson文档哈

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
//        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/login");
//        registry.addInterceptor(new LogoutInterceptor()).addPathPatterns("/logout");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    //添加拦截器
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
        if (!"dev".equals(env)) { //开发环境忽略签名认证
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                    //验证签名
                    boolean pass = validateSign(request);
                    if (pass) {
                        return true;
                    } else {
                        logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                                request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));

                        Result result = new Result();
                        result.setCode(ResultCode.UNAUTHORIZED).setMessage("签名认证失败");
                        responseResult(response, result);
                        return false;
                    }
                }
            });
        }
    }*/

    /**
     * 一个简单的签名认证，规则：
     * 1. 将请求参数按ascii码排序
     * 2. 拼接为a=value&b=value...这样的字符串（不包含sign）
     * 3. 混合密钥（secret）进行md5获得签名，与请求的签名进行比较
     */
    private boolean validateSign(HttpServletRequest request) {
        /*String requestSign = request.getParameter("sign");//获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
        if (StringUtils.isEmpty(requestSign)) {
            return false;
        }
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        keys.remove("sign");//排除sign参数
        Collections.sort(keys);//排序

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(request.getParameter(key)).append("&");//拼接字符串
        }
        String linkString = sb.toString();
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);//去除最后一个'&'

        String secret = "Potato";//密钥，自己修改
        String sign = DigestUtils.md5Hex(linkString + secret);//混合密钥md5

        return StringUtils.equals(sign, requestSign);//比较*/
        return true;
    }

    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                ApiResp apiResp = new ApiResp();
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    apiResp.setCode(ErrorEnum.COMMON_BUSINESS_ERROR.getCode());
                    apiResp.setMsg(e.getMessage());
                    log.info(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    apiResp.setCode(ErrorEnum.NOT_FOUND.getCode());
                    apiResp.setMsg("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    apiResp.setCode(ErrorEnum.SERVICE_UNAVAILABLE.getCode());
                    apiResp.setMsg(e.getMessage());
                } else if (e instanceof MethodArgumentNotValidException) {
                    BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
                    Map<String, String> errorMap = new HashMap<>(16);
                    bindingResult.getFieldErrors().forEach((fieldError) ->
                            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage())
                    );
                    apiResp.setCode(ErrorEnum.DATA_VALIDATE.getCode());
                    apiResp.setMsg("非法参数");
                    apiResp.setData(errorMap);
                } else if (e instanceof BindException) {
                    BindingResult bindingResult = ((BindException) e).getBindingResult();
                    Map<String, String> errorMap = new HashMap<>(16);
                    bindingResult.getFieldErrors().forEach((fieldError) ->
                            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage())
                    );
                    apiResp.setCode(ErrorEnum.DATA_VALIDATE.getCode());
                    apiResp.setMsg("非法参数");
                    apiResp.setData(errorMap);
                } else {
                    apiResp.setCode(ErrorEnum.INTERNAL_SERVER_ERROR.getCode());
                    apiResp.setMsg("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    log.error(message, e);
                }
                responseResult(response, apiResp);
                return new ModelAndView();
            }

        });
    }

    private void responseResult(HttpServletResponse response, ApiResp apiResp) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(apiResp));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    /*@Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }*/

}
