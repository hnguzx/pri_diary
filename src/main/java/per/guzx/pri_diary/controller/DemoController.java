package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 测试连通性接口
 */
@Controller
@Slf4j
@RequestMapping("/request")
public class DemoController {

    @Value("${server.port}")
    private String port ;

    @Autowired
    Environment environment;

    /**
     * jsp页面
     * @return
     */
    @RequestMapping("/view")
    public String filePage() {
        String local = environment.getProperty("local.server");
        String a = null;
        InetAddress address = null;
        try {
            address = Inet4Address.getLocalHost();
            a = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 返回字符串
     * @return
     */
    @ResponseBody
    @GetMapping("/index")
    public String index() {
        log.trace("追踪日志");
        log.debug("调试日志");
        log.info("重要信息");
        log.warn("警告日志");
        log.error("错误日志");
        return "请求成功";
    }

    /**
     * webSocket
     * @return
     */
    @GetMapping("/webSocket")
    public String webSocketPage() {
        return "webSocket/index";
    }
}
