package cn.csxhz.mailservice;

import cn.csxhz.mailservice.sevice.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class MailServiceApplicationTests {
//    @Autowired
    MailService mailService;
    @Test
    void contextLoads() {


    }


    void doTest(MultipartFile file) {

        System.out.printf(file.getName());
        return;

    }

}
