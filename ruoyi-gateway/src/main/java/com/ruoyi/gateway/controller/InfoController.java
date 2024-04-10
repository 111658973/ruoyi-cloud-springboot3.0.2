package com.ruoyi.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Cbf
 * @Date: 2024/4/16
 * @Description:
 */
@RestController
@RequestMapping
public class InfoController {
    @GetMapping
    public String getMessage() {
        return "Hello, this is gateway";
    }
}
