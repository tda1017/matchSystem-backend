package com.xin.usercenter.exception;

import com.xin.usercenter.common.ErrorCode;

/**
 * @author: TDA
 * @date: 2024/1/4 19:10
 * @description:
 */
public class BusinessException extends RuntimeException {
    private final int code;
    private final String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }
}
