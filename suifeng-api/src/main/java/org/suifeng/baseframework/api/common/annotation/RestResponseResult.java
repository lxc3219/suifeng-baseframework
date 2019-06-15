package org.suifeng.baseframework.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import java.lang.annotation.*;

/**
 * 自定义标识注解，用于标识该接口需要被Result包装
 * 常标记于类上，代替@Controller，@ResponseBody，@ResponseResult
 * @createTime 2019/5/26 11:58
 * @author luoxc
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseResult
public @interface RestResponseResult {
    @AliasFor(
            annotation = Controller.class
    )
    String value() default "";

}
