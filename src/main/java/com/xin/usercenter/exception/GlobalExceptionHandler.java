package com.xin.usercenter.exception;

import com.xin.usercenter.common.BaseResponse;
import com.xin.usercenter.common.ErrorCode;
import com.xin.usercenter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: TDA
 * @date: 2024/1/5 18:09
 * @description:
 */
@Slf4j
@RestControllerAdvice //todo ?w
public class GlobalExceptionHandler {

    public BaseResponse businessExceptionHandler(BusinessException e){
        log.error("businessException:" + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    public BaseResponse runtimeExceptionHandler(BusinessException e){
        log.error("businessException:" + e.getMessage(), e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR.getCode(), e.getMessage(), "");
    }
}
