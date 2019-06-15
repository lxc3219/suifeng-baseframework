package org.suifeng.baseframework.api.encrypt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "api.encrypt", name = "enabled", havingValue = "true")
public class EncryptConfiguration {

	/**
	 * 配置请求解密
	 * @return
	 */
	@Bean
	public EncryptResponseBodyAdvice encryptResponseBodyAdvice() {
		return new EncryptResponseBodyAdvice();
	}
	
	/**
	 * 配置请求加密
	 * @return
	 */
	@Bean
	public EncryptRequestBodyAdvice encryptRequestBodyAdvice() {
		return new EncryptRequestBodyAdvice();
	}
	
}
