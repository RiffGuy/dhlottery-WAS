package com.riffrain.dhlottery.module.dhlotto.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDTO {
    private String userId;
    private String password;

    public SignInDTO(CheckAccountReqDTO reqDTO) {
        this.userId = reqDTO.getUserId();
        this.password = reqDTO.getPassword();
    }
}
