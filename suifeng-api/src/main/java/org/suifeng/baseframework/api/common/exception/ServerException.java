package org.suifeng.baseframework.api.common.exception;

import org.suifeng.baseframework.api.common.enums.ResultEnum;

/**
 * 服务器异常
 * @createTime 2019/6/2 11:59
 * @author luoxc
 */
public class ServerException extends BaseException {

    public ServerException(String message) {
        super(ResultEnum.SERVICE_EXCEPTION.getCode(), message);
    }

}
