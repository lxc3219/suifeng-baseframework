package org.suifeng.baseframework.api.encrypt;

import org.suifeng.baseframework.api.common.annotation.Decrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 请求数据接收处理类
 * 对加了@Decrypt的方法的数据进行解密操作
 * 只对@RequestBody参数有效
 * @createTime 2019/6/9 23:44
 * @author luoxc
 */
@Slf4j
@ControllerAdvice
public class EncryptRequestBodyAdvice implements RequestBodyAdvice {
	
	@Autowired
	private EncryptProperties encryptProperties;
	
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		if(parameter.getMethod().isAnnotationPresent(Decrypt.class) && !encryptProperties.isDebug()){
			try {
				return new DecryptHttpInputMessage(inputMessage, encryptProperties.getKey(), encryptProperties.getCharset());
			} catch (Exception e) {
				log.error("数据解密失败", e);
			}
		}
		return inputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}
}


