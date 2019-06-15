package org.suifeng.baseframework.admin.config.web;

import org.suifeng.baseframework.core.cros.CorsFilter;
import org.suifeng.baseframework.core.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 * @createTime 2019/5/26 10:48
 * @author luoxc
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * xssFilter注册
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        XssFilter xssFilter = new XssFilter();
        FilterRegistrationBean registration = new FilterRegistrationBean(xssFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean CorsFilterRegistration() {
        CorsFilter corsFilter = new CorsFilter();
        FilterRegistrationBean registration = new FilterRegistrationBean(corsFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

}
