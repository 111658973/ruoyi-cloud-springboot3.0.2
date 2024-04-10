package cn.csxhz.mailservice.controller;


import cn.csxhz.common.entity.EmailRequest;
import cn.csxhz.mailservice.sevice.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Cbf
 * @Date: 2024/3/19
 * @Description:
 */
@Api(tags = "邮件服务")
@RestController
@RequestMapping("/mail")
public class EmailController {
    @Autowired
    MailService mailService;

    @ApiOperation("发送邮件")
    @PostMapping("/send")
    public String sendMail(@RequestBody EmailRequest info) {
        try {

            mailService.sendEmail(info);
            return "发送成功";


        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/test")
    public String getMessage() {
        return "Hello, this is mail-service";
    }

}
