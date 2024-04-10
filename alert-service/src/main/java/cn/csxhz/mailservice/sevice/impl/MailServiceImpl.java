package cn.csxhz.mailservice.sevice.impl;

import cn.csxhz.common.entity.EmailRequest;
import cn.csxhz.mailservice.sevice.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import java.util.List;

/**
 * @Author: Cbf
 * @Date: 2024/3/19
 * @Description:
 */


@Service
@RefreshScope
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String username;


    @Override
    public void sendMail(String mail) {

    }

    @Override
    public void sendMailToGroup(List<String> mails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(mails.toArray(new String[0]));
        message.setSubject("邮箱测试发送");
        message.setText("这是一封测试邮件");
        javaMailSender.send(message);
    }

    @Override
    public String sendEmail(EmailRequest emailInfo) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailInfo.getFrom());
            message.setTo(emailInfo.getTo());
            message.setSubject(emailInfo.getSubject());
            message.setText(emailInfo.getContent());
            javaMailSender.send(message);
        } catch (Exception e) {
            return e.getMessage();
        }

        return null;
    }
}
