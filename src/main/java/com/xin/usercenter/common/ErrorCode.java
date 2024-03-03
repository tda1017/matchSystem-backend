package com.xin.usercenter.common;

import lombok.Data;

/**
 * @author: TDA
 * @date: 2024/1/4 18:50
 * @description:
 */
public enum ErrorCode {
    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),

    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),

    SYSTEM_ERROR(50000, "系统内部异常", "");
    private int code;
    /**
     * 状态码信息
     */
    private String message;
    /**
     * 详情
     */
    private String description;


    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
