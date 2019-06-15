package org.suifeng.baseframework.admin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 当前登录用户注解
 * 常用于请求参数上，用于获取当前用户信息
 * @createTime 2019/6/1 18:36
 * @author luoxc
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}