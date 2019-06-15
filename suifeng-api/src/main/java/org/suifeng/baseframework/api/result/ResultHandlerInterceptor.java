package org.suifeng.baseframework.api.result;

import org.suifeng.baseframework.api.common.annotation.ResponseResult;
import org.suifeng.baseframework.api.common.annotation.RestResponseResult;
import org.suifeng.baseframework.api.constant.ApiConfigConsts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 根据方法或类是否有@ResponseResult、@RestResponseResult打标记，
 * 后续环节会根据该标记决定是否封装返回体
 * @createTime 2019/6/1 7:55
 * @author luoxc
 */
@Component
@ConditionalOnProperty(prefix = "api.result", name = "enabled", havingValue = "true")
public class ResultHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            // TODO 优化，第一次请求解析，将是否需要包装加进缓存，以后均从缓存读取
            // 判断是否在类对象上面加了注解
            if (clazz.isAnnotationPresent(ResponseResult.class) || clazz.isAnnotationPresent(RestResponseResult.class)) {
                // 设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                Object var = null;
                if (clazz.isAnnotationPresent(ResponseResult.class)) {
                    var = clazz.getAnnotation(ResponseResult.class);
                } else {
                    var = clazz.getAnnotation(RestResponseResult.class);
                }
                request.setAttribute(ApiConfigConsts.RESPONSE_RESULT_ANN, var);
            }
            // 方法体上是否有注解
            else if (method.isAnnotationPresent(ResponseResult.class)) {
                // 设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(ApiConfigConsts.RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
