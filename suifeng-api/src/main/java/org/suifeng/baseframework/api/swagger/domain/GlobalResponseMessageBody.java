package org.suifeng.baseframework.api.swagger.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GlobalResponseMessageBody {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应体
     */
    private String modelRef;

}
