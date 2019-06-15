package org.suifeng.baseframework.api.result;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 方法体封装拦截器配置
 * @createTime 2019/6/1 17:11
 * @author luoxc
 */
@Configuration
@ConditionalOnProperty(prefix = "api.result", name = "enabled", havingValue = "true")
public class ResultConfiguration implements WebMvcConfigurer {

    /**
     * 资源返回体封装
     * @createTime 2019/5/29 15:11
     * @author luoxc
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResultHandlerInterceptor()).addPathPatterns("/**");
    }

}
