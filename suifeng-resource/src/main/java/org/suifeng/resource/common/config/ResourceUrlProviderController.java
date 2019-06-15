package org.suifeng.resource.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;


/**
 * 使用ResourceUrlProvider进行版本管理
 * 并避免在版本发生改变时，由于浏览器缓存而产生资源版本未改变的错误
 * @createTime 2019/6/2 12:41
 * @author luoxc
 */
@ControllerAdvice
public class ResourceUrlProviderController {

    @Autowired
    ResourceUrlProvider resourceUrlProvider;

    @ModelAttribute("urls")
    public ResourceUrlProvider resVersion() {
        return this.resourceUrlProvider;
    }
}
