package per.guzx.pri_diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class DemoController {

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
}
