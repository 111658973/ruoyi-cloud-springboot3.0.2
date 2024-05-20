package com.ruoyi.modules.monitor.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 监控权限配置
 *
 * @author ruoyi
 */
@Configuration
public class WebSecurityConfigurer {
    private final String adminContextPath;

    public WebSecurityConfigurer(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(adminContextPath + "/assets/**",
                                adminContextPath + "/login",
                                adminContextPath + "/actuator/**",
                                adminContextPath + "/instances/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage(adminContextPath + "/login")
                        .successHandler(successHandler)
                )
                .logout(logout -> logout.logoutUrl(adminContextPath + "/logout"))
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
