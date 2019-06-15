package org.suifeng.baseframework.api.common.helper;

import org.suifeng.baseframework.api.common.domain.Result;
import org.suifeng.baseframework.api.common.enums.BaseExceptionEnum;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;

public class RestHelper {

    /**
     * 请求处理成功
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok() {
        return ok(null);
    }

    /**
     * 请求处理成功
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return new Result<T>()
                .success(Boolean.TRUE)
                .status(HttpStatus.OK.value())
                .data(data)
                .putTimeStamp();
    }

    /**
     * 请求处理失败（默认500错误）
     * @param msg 调用结果消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    /**
     * 请求处理失败
     * @param status 状态码
     * @param msg 调用结果消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Integer status, String msg) {
        return error(status, null, msg);
    }

    /**
     * 业务处理失败（默认500错误）
     * @param code 业务代码
     * @param msg 调用结果消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> bizError(Integer code, String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), code, msg);
    }

    /**
     * 业务处理失败（默认500错误）
     * @param baseExceptionEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> bizError(BaseExceptionEnum baseExceptionEnum) {
        return bizError(baseExceptionEnum.getCode(), baseExceptionEnum.getMsg());
    }

    /**
     * 请求处理失败
     * @param status 状态码
     * @param code 业务代码
     * @param msg 调用结果消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Integer status, Integer code, String msg) {
        return new Result<T>()
                .success(Boolean.FALSE)
                .status(status)
                .code(code)
                .msg(msg)
                .putTimeStamp();
    }

    /**
     * 判断是否是ajax请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

}
