package org.suifeng.baseframework.api.rest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix ="api.rest")
public class RestProperties {

	/**
	 * 是否启用rest
	 */
	private Boolean enabled;

	/**
	 * 接口服务器地址
	 */
	private String serverAddr;

	/**
	 * Http请求配置
	 */
	private HttpRequestProperties http;

}
