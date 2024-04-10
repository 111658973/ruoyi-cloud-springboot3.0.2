package cn.csxhz.mailservice.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Author: Cbf
 * @Date: 2024/4/9
 * @Description: 测试日志传输到es
 */

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/send")

    public void send() {

        log.error("OK :{}", LocalDateTime.now());
    }
}
