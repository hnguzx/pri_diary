package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;

/**
 * 测试连通性接口
 */
@Controller
@Slf4j
//@RequestMapping("/request")
public class DemoController {

    @Value("${server.port}")
    private String port;

    @Autowired
    Environment environment;

    /**
     * jsp页面
     *
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
     *
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

    @ResponseBody
    @RequestMapping("/common/hello")
    public String common(){
        return "common";
    }

    @ResponseBody
    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    /**
     * webSocket
     *
     * @return
     */
    @GetMapping("/webSocket")
    public String webSocketPage() {
        return "webSocket/index";
    }

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/send")
    public String send() {
        return "webSocket/send";
    }

    @GetMapping("/receive")
    public String receive() {
        return "webSocket/receive";
    }

    @GetMapping("/sendUser")
    public String sendUser() {
        return "webSocket/sendUser";
    }

    @GetMapping("/receiveUser")
    public String receiveUser() {
        return "webSocket/receiveUser";
    }

    // 定义消息请求路径
    // 定义结果发送到特定路径
    /*@MessageMapping("/send")
    @SendTo("/sub/chat")
    public String sendMessage(String value) {
        return value;
    }*/

    /**
     * 发送消息给特定用户
     *
     * @param principal
     * @param body
     */
    /*@MessageMapping("/sendUser")
    public void sendToUser(Principal principal, String body) {
        String[] args = body.split(",");
        String desUser = args[0];
        String msg = args[1];
        //  发送到用户和监听地址
        simpMessagingTemplate.convertAndSendToUser(desUser, "/queue/customer", msg);
    }*/
}
