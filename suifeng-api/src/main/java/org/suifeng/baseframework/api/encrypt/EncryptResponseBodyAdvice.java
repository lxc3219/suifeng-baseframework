package org.suifeng.baseframework.api.encrypt;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.suifeng.baseframework.api.common.annotation.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 请求响应处理类
 * 对加了@Encrypt的方法的数据进行加密操作
 * @createTime 2019/6/9 23:43
 * @author luoxc
 */
@Slf4j
@Order(99)
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private EncryptProperties encryptProperties;
	
	private static ThreadLocal<Boolean> encryptLocal = new ThreadLocal<Boolean>();
	
	public static void setEncryptStatus(boolean status) {
		encryptLocal.set(status);
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		// 可以通过调用EncryptResponseBodyAdvice.setEncryptStatus(false);来动态设置不加密操作
		Boolean status = encryptLocal.get();
		if (status != null && status == false) {
			encryptLocal.remove();
			return body;
		}
		long startTime = System.currentTimeMillis();
		if (returnType.getMethod().isAnnotationPresent(Encrypt.class) && !encryptProperties.isDebug()) {
			try {
				String content = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
				if (!StringUtils.hasText(encryptProperties.getKey())) {
					throw new NullPointerException("请配置api.encrypt.key");
				}
				String result =  AesEncryptUtils.aesEncrypt(content, encryptProperties.getKey());
				long endTime = System.currentTimeMillis();
				log.debug("Encrypt Time:" + (endTime - startTime));
				return result;
			} catch (Exception e) {
				log.error("加密数据异常", e);
			}
		}
		return body;
	}

}
