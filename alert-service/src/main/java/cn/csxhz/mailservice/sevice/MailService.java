package cn.csxhz.mailservice.sevice;

import cn.csxhz.common.entity.EmailRequest;

import java.util.List;

/**
 * @Author: Cbf
 * @Date: 2024/3/19
 * @Description:
 */
public interface MailService {
    void sendMail(String content);

    void sendMailToGroup(List<String> mails);



    String sendEmail(EmailRequest emailInfo);
}
