package org.suifeng.baseframework.api.swagger;

import org.suifeng.baseframework.api.swagger.domain.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "api.swagger")
public class SwaggerProperties {

    /**
     * 是否开启swagger
     */
    private Boolean enabled;

    /**
     * 标题
     */
    private String title = "";

    /**
     * 描述
     */
    private String description = "";

    /**
     * 版本
     */
    private String version = "";

    /**
     * 许可证
     */
    private String license = "";

    /**
     * 许可证URL
     */
    private String licenseUrl = "";

    /**
     * 维护人
     */
    private Contact contact = new Contact();

    /**
     * 服务条款URL
     */
    private String termsOfServiceUrl = "";

    /**
     * swagger会解析的包路径
     */
    private String basePackage = "";

    /**
     * swagger会解析的url规则
     */
    private List<String> basePath = new ArrayList<>();

    /**
     * 在basePath基础上需要排除的url规则
     */
    private List<String> excludePath = new ArrayList<>();

    /**
     * host信息
     */
    private String host = "";

    /**
     * 分组文档
     */
    private Map<String, DocketInfo> docket = new LinkedHashMap<>();

    /**
     * 忽略的参数类型
     */
    private List<Class<?>> ignoredParameterTypes = new ArrayList<>();

    /**
     * 全局参数配置
     */
    private List<GlobalOperationParameter> globalOperationParameters;

    /**
     * 是否使用默认预定义的响应消息 ，默认 true
     */
    private Boolean applyDefaultResponseMessages = true;

    /**
     * 全局响应消息
     */
    private GlobalResponseMessage globalResponseMessage;

    /**
     * 全局统一鉴权配置
     */
    private Authorization authorization = new Authorization();

}
