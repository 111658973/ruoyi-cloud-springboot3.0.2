package cn.csxhz.mailservice.config;

/**
 * @Author: Cbf
 * @Date: 2024/4/16
 * @Description:
 */

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 对Swagger2的配置信息
 *
 * @author
 */
@Configuration
@EnableSwagger2   // 必需
@EnableKnife4j    // 必需
public class SwaggerConfig {
    private static final String VERSION = "1.0.0";

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.csxhz"))   // 配置扫描的包路径， 可以把范围放大些
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("log-service-系统接口文档")
                .contact(new Contact("test","","xxxx@163.com"))
                .description("project接口文档")
                .version(VERSION)
                .build();
    }
}