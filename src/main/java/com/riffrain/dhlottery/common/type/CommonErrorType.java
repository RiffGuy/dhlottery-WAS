package com.riffrain.dhlottery.common.type;

import lombok.Getter;

@Getter
public enum CommonErrorType {
    USER_NOT_FOUND("USER_NOT_FOUND", "계정을 조회 할 수 없습니다."),
    USER_NOT_SIGN_IN("USER_NOT_SIGN_IN", "로그인이 필요합니다."),

    ;


    private final String code;
    private final String message;

    CommonErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
