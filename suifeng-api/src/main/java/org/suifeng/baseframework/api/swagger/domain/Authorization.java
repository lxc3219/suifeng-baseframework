package org.suifeng.baseframework.api.swagger.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * securitySchemes 支持方式之一 ApiKey
 */
@Data
@NoArgsConstructor
public class Authorization {

    /**
     * 鉴权策略ID，对应 SecurityReferences ID
     */
    private String name = "Authorization";

    /**
     * 鉴权传递的Header参数
     */
    private String keyName = "TOKEN";

    /**
     * 需要开启鉴权URL的正则
     */
    private String authRegex = "^.*$";
}
