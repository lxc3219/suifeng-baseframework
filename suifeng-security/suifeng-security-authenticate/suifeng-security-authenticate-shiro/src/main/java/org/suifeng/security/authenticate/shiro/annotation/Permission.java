package org.suifeng.security.authenticate.shiro.annotation;

import java.lang.annotation.*;

/**
 * 权限注解
 * @createTime 2019/6/13 14:29
 * @author luoxc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    String[] value();
}