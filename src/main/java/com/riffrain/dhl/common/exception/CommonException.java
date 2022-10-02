package com.riffrain.dhl.common.exception;

import com.riffrain.dhl.common.type.CommonErrorType;
import lombok.Getter;

@Getter
public class CommonException extends IllegalArgumentException{
    private final CommonErrorType commonErrorType;

    public CommonException(CommonErrorType commonErrorType) {
        super(commonErrorType.getMessage());
        this.commonErrorType = commonErrorType;
    }
}
