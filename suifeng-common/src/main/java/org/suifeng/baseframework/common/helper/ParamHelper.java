package org.suifeng.baseframework.common.helper;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * 参数工具类
 * @createTime 2019/5/24 13:37
 * @author luoxc
 */
public class ParamHelper {

	/**
	 * get方法参数utf-8转码
	 * @param request
	 * @return
	 * @createTime 2019/5/24 13:37
	 * @author luoxc
	 */
	public static Map<String, Object> getParam(HttpServletRequest request) {
		String method = request.getMethod();
		Map<String, Object> param = getParametersStartingWith(request, null);
		if ("GET".equals(method)) {
			Set<String> keys = param.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = param.get(key);
				if (value instanceof String) {
					String temp = (String) value;
					try {
						temp = URLDecoder.decode(temp, "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					param.put(key, temp);
				}
			}
		}
		return param;
	}
	
	public static Map<String, Object> getParametersStartingWith(HttpServletRequest request,String prefix) {
		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				}
				else if (values.length > 1) {
					params.put(unprefixed, values);
				}
				else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}
}
