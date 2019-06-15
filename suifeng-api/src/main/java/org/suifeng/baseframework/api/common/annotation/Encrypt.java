package org.suifeng.baseframework.api.common.annotation;

import java.lang.annotation.*;


/**
 * 加密注解，加了此注解的接口将进行数据加密操作
 * @createTime 2019/6/9 18:33
 * @author luoxc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

}
