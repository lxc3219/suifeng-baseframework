package org.suifeng.baseframework.api.common.domain;

import java.util.Map;


public class ResMessage<T> {
	
	/**
	 * 对应的http状态码
	 */
	private String statusCode;
	
	/**
	 * 错误代码
	 */
	private String code;

	/**
	 * 错误消息
	 */
	private String message;
	
	/**
	 * 携带数据
	 */
	private T data;
	
	/**
	 * 额外信息
	 */
	protected final Map<String, Object> extraInfo;
	
	public ResMessage(String code,String message,T data){
		this.code=code;
		this.message=message;
		this.data=data;
		this.extraInfo=null;
	}
	
	public ResMessage(ErrorType errorType){
		this.code = errorType.getValue();
		this.message = errorType.getName();
		this.extraInfo=null;
	}
	
	public ResMessage(ErrorType errorType,T data){
		this.code = errorType.getValue();
		this.message = errorType.getName();
		this.data = data;
		this.extraInfo=null;
	}
	
	public ResMessage(){
		this.extraInfo=null;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statuscode) {
		this.statusCode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	} 
}
