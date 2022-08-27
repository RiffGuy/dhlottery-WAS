package com.riffrain.dhlottery.common.exception;

import com.riffrain.dhlottery.common.type.CommonErrorType;
import lombok.Getter;

@Getter
public class CommonException extends IllegalArgumentException{
    private final CommonErrorType commonErrorType;

    public CommonException(CommonErrorType commonErrorType) {
        super(commonErrorType.getMessage());
        this.commonErrorType = commonErrorType;
    }
}
