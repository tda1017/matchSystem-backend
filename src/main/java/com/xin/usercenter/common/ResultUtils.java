package com.xin.usercenter.common;

/**
 * @author: TDA
 * @date: 2024/1/4 18:11
 * @description:
 */
public class ResultUtils {
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0, data, "ok");
    }

    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse error(int code, String message, String description){
        return new BaseResponse<>(code, null, message, description);
    }

    public static BaseResponse error(ErrorCode errorCode, String description){
        return new BaseResponse<>(errorCode.getCode(), null, errorCode.getMessage(), description);
    }
}
