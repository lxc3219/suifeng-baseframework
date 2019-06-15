package org.suifeng.baseframework.core.boot.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "resource")
public class ResourceProperties {

    private Map<String,String> resourceHandler;

}
