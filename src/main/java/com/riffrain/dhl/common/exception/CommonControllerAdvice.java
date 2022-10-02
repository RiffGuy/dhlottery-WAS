package com.riffrain.dhl.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(CommonException.class)
    public Map<String, Object> commonError(CommonException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("error", e.getCommonErrorType().getCode());
        result.put("message", e.getCommonErrorType().getMessage());
        return result;
    }
}
