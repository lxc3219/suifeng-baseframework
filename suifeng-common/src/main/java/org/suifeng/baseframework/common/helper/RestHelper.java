package org.suifeng.baseframework.common.helper;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 不推荐使用
 * @createTime 2019/6/1 8:22
 * @author luoxc
 */
@Deprecated
public class RestHelper {
	
	public static final String MSG	= "msg";
	
	public static final String RESULT = "result";
	
	public static final String CODE = "code";
	
	public static final String DATA	= "data";
	
	public static final String COUNT = "count";
	
	public static final String SUCCESS_CODE = "0";
	
	public static final String ERROR_CODE = "1";
	
	public static final String NO_ERROR_CODE = "2";
	
	public static final String FORBIDDEN_CODE = "3";
	
	public static final String VALIDATE_CODE = "4";

	/**
	 * 操作成功，达到预期效果
	 * @return
	 */
	public static Map<String, Object> success() {
		return success(null);
	}

	/**
	 * 操作成功，达到预期效果
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> success(String msg) {
		return success(msg, null);
	}

	/**
	 * 操作成功，达到预期效果
	 * @param data
	 * @return
	 */
	public static Map<String, Object> success(Object data) {
		return success(null, data);
	}

	/**
	 * 操作成功，达到预期效果
	 * @param msg
	 * @param data
	 * @return
	 */
	public static Map<String, Object> success(String msg, Object data) {
		Map<String, Object> result = Maps.newHashMap();
		result.put(CODE, SUCCESS_CODE);
		result.put(MSG, msg);
		result.put(DATA, data);
		return result;
	}

	/**
	 * 操作成功，达到预期效果
	 * @param msg
	 * @param list
	 * @param count
	 * @return
	 */
	public static Map<String, Object> success(String msg, List<?> list, Long count) {
		Map<String, Object> result = Maps.newHashMap();
		result.put(CODE, SUCCESS_CODE);
		result.put(MSG, msg);
		result.put(DATA, list);
		result.put(COUNT, count);
		return result;
	}
	
	/**
	 * FuncName: noError <br/>
	 * Description: 操作结束，未达到预期效果，未报错
	 * <pre>如：删除某用户，而该用户并不存在</pre>
	 * @param msg
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> noError(String msg) {
		Map<String, Object> result = Maps.newHashMap();
		result.put(CODE, NO_ERROR_CODE);
		result.put(MSG, msg);
		return result;
	}

	/**
	 * 操作失败，返回异常编码和信息
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> error(String msg) {
		return error(ERROR_CODE, msg);
	}
	
	/**
	 * 操作失败，返回异常编码和信息
	 * @param code
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> error(String code, String msg) {
		Map<String, Object> result = Maps.newHashMap();
		result.put(CODE, code);
		result.put(MSG, msg);
		return result;
	}
	
	/**
	 * FuncName: validateError <br/>
	 * Description: 验证数据错误
	 * @param msg
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> validateError(String msg) {
		return error(VALIDATE_CODE, msg);
	}
	
	/**
	 * FuncName: forbiddenError <br/>
	 * Description: 403无权限错误
	 * @param msg
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> forbiddenError(String msg) {
		return error(FORBIDDEN_CODE, msg);
	}
	
	/**
	 * 判断是否是ajax请求
	 */
	public static boolean isAjax(HttpServletRequest request) {
	    return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
	}
}
