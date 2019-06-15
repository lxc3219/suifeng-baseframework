package org.suifeng.baseframework.api.common.domain;

import org.suifeng.baseframework.api.common.enums.BaseExceptionEnum;


public class ResultCode implements BaseExceptionEnum {

    private Integer code;
    private String msg;

    public ResultCode() {};

    public ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
