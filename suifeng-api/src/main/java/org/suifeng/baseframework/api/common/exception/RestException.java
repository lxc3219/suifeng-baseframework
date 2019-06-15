package org.suifeng.baseframework.api.common.exception;

import org.suifeng.baseframework.api.common.enums.BaseExceptionEnum;
import org.springframework.http.HttpStatus;

/**
 * 接口异常类
 */
public class RestException extends BaseException {

	public HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	public RestException(BaseExceptionEnum baseExceptionEnum) {
		super(baseExceptionEnum);
	}

	public RestException() {
		super();
	}

	public RestException(String msg) {
		super(msg);
	}

	public RestException(Integer code, String msg) {
		super(code, msg);
	}

	public RestException(Throwable cause) {
		super(cause);
	}

	public RestException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RestException(Integer code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	public RestException(HttpStatus status) {
		this.status = status;
	}

	public RestException(HttpStatus status, String msg) {
		super(msg);
		this.status = status;
	}

	public RestException(HttpStatus status, Integer code, String msg) {
		super(code, msg);
		this.status = status;
	}

	public String getStatus() {
		return status.toString();
	}

}
