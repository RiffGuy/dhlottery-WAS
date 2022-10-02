package com.riffrain.dhl.module.user.service;

import com.riffrain.dhl.module.user.dto.SignInDTO;

public interface UserService {
    boolean signIn(SignInDTO signInDTO);
}
