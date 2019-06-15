package org.suifeng.baseframework.api.common.enums;

/**
 * 基础异常枚举
 * @createTime 2019/6/2 11:10
 * @author luoxc
 */
public interface BaseExceptionEnum {

    /**
     * 获取异常编码
     * @createTime 2019/5/26 14:21
     * @author luoxc
     */
    Integer getCode();

    /**
     * 获取异常信息
     * @createTime 2019/5/26 14:21
     * @author luoxc
     */
    String getMsg();
}
