package cn.csxhz.logservice.controller;


import cn.csxhz.common.util.SnowflakeIdWorker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Cbf
 * @Date: 2024/4/9
 * @Description: 测试日志传输到es
 */

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0, 0);

    @GetMapping("/send")
    public void send() {

        log.error("Test File log save :{}", snowflakeIdWorker.nextId());
    }
}
