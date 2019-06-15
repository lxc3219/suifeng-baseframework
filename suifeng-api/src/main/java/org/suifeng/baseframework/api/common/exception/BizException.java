package org.suifeng.baseframework.api.common.exception;

import org.suifeng.baseframework.api.common.enums.BaseExceptionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 * @createTime 2019/5/24 12:43
 * @author luoxc
 */
@Setter
@Getter
public class BizException extends BaseException  {

    public BizException(BaseExceptionEnum baseExceptionEnum) {
        super(baseExceptionEnum);
    }

    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(Integer code, String msg) {
        super(code, msg);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BizException(Integer code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
}
