package com.riffrain.dhlottery.module.user.service;

import com.riffrain.dhlottery.module.user.dto.SignInDTO;

public interface UserService {
    boolean signIn(SignInDTO signInDTO);
}
