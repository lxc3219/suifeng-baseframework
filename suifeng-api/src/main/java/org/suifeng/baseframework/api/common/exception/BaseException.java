package org.suifeng.baseframework.api.common.exception;

import org.suifeng.baseframework.api.common.enums.BaseExceptionEnum;

public class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(BaseExceptionEnum baseExceptionEnum) {
        this(baseExceptionEnum.getCode(), baseExceptionEnum.getMsg());
    }

    public BaseException() {
        super();
    }

    public BaseException(String msg) {
        this(null, msg);
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BaseException(Throwable cause) {
        this(null, cause);
    }

    public BaseException(String msg, Throwable cause) {
        this(null, msg, cause);
    }

    public BaseException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
