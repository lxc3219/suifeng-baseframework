package org.suifeng.baseframework.api.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class DecryptHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;

    private InputStream body;

    public DecryptHttpInputMessage(HttpInputMessage inputMessage, String key, String charset) throws Exception {
        this.headers = inputMessage.getHeaders();
        String content = IOUtils.toString(inputMessage.getBody(), charset);
        long startTime = System.currentTimeMillis();
        // JSON 数据格式的不进行解密操作
        String decryptBody = "";
        if (content.startsWith("{")) {
            decryptBody = content;
        } else {
            decryptBody = AesEncryptUtils.aesDecrypt(content, key);
        }
        long endTime = System.currentTimeMillis();
        log.debug("Decrypt Time:" + (endTime - startTime));
        this.body = IOUtils.toInputStream(decryptBody, charset);
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
