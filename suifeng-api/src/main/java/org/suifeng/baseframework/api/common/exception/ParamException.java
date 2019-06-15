package org.suifeng.baseframework.api.common.exception;

import org.suifeng.baseframework.api.common.enums.ResultEnum;

/**
 * 参数异常
 * @createTime 2019/6/2 11:59
 * @author luoxc
 */
public class ParamException extends BaseException {

    public ParamException(String message) {
        super(ResultEnum.PARAM_EXCEPTION.getCode(), message);
    }
}
