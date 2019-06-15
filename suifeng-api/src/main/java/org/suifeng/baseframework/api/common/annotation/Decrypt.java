package org.suifeng.baseframework.api.common.annotation;

import java.lang.annotation.*;


/**
 * 解密注解，加了此注解的接口将进行数据解密操作
 * @createTime 2019/6/9 18:34
 * @author luoxc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {

}
