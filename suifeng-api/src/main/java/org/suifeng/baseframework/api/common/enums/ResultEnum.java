package org.suifeng.baseframework.api.common.enums;

/**
 * 返回编码枚举类
 * TODO 待梳理
 * @createTime 2019/6/2 11:22
 * @author luoxc
 */
public enum ResultEnum implements BaseExceptionEnum {

    // 自定义异常
    SYSTEM_EXCEPTION(-1, "系统异常"),
    UNKNOWN_EXCEPTION(01, "未知异常"),
    SERVICE_EXCEPTION(02, "服务异常"),
    BIZ_EXCEPTION(03, "业务异常"),
    INFO_EXCEPTION(04, "提示级错误"),
    DB_EXCEPTION(05, "数据库操作异常"),
    PARAM_EXCEPTION(06, "参数验证错误"),
    //
    OK(200, "处理成功"),
    // 客户端错误
    BAD_REQUEST(400, "服务器不理解客户端的请求，未做任何处理"),
    UNAUTHORIZED(401, "用户未提供身份验证凭据，或者没有通过身份验证"),
    FORBIDDEN(403, "用户通过了身份验证，但是不具有访问资源所需的权限"),
    NOT_FOUND(404, "所请求的资源不存在，或不可用"),
    METHOD_NOT_ALLOWED(405, "用户已经通过身份验证，但是所用的 HTTP 方法不在他的权限之内"),
    GONE(410, "所请求的资源已从这个地址转移，不再可用"),
    UNPROCESSABLE_ENTITY(422, "客户端上传的附件无法处理，导致请求失败"),
    TOO_MANY_REQUESTS(429, "客户端的请求次数超过限额"),
    // 服务端错误
    INTERNAL_SERVER_ERROR(500, "暂停提供服务"),
    SERVICE_UNAVAILABLE(503, "服务器无法处理请求");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
