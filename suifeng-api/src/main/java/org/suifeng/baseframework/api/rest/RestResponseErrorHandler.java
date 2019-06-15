package org.suifeng.baseframework.api.rest;

import org.suifeng.baseframework.api.common.exception.RestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Slf4j
@Component
public class RestResponseErrorHandler extends DefaultResponseErrorHandler {

	/**
	 * 重写handleError，抛出RestException
	 * @createTime 2019/6/1 20:06
	 * @author luoxc
	 */
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode = HttpStatus.resolve(response.getRawStatusCode());
		log.debug("rest call has error:" + statusCode.toString());
		String body = IOUtils.toString(response.getBody(),"UTF-8");
		throw new RestException(statusCode,body);
	}
	

}
