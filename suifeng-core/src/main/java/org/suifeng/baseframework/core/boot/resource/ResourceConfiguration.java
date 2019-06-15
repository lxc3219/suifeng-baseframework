package org.suifeng.baseframework.core.boot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Iterator;
import java.util.Map;

@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {

	@Autowired
	private ResourceProperties resourceProperties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Map<String,String> resourceHandler = resourceProperties.getResourceHandler();
		Iterator<Map.Entry<String,String>> iterator = resourceHandler.entrySet().iterator();
		// 自定义资源映射
		while(iterator.hasNext()){
			Map.Entry<String,String> entry = iterator.next();
			ResourceHandlerRegistration rhr = registry.addResourceHandler(entry.getKey());
			if(entry.getValue().indexOf(",") > 0){
				String[] values = entry.getValue().split(",");
				rhr.addResourceLocations(values);
			}else{
				rhr.addResourceLocations(entry.getValue());
			}
		}
		// 默认资源映射
	/*	registry.addResourceHandler("doc.html")
		    .addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
		    .addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/**")
		    .addResourceLocations("classpath:/static/ie8/");*/
	}
}
