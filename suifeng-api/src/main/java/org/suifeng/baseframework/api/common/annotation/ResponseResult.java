package org.suifeng.baseframework.api.common.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 自定义标识注解，用于标识该类或方法需要被Result包装，
 * 常标记于方法上，可代替@ResponseBody使用
 *
 * @createTime 2019/5/26 11:58
 * @author luoxc
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface ResponseResult {

}
