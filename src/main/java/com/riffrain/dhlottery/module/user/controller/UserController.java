package com.riffrain.dhlottery.module.user.controller;

import com.riffrain.dhlottery.module.user.dto.CheckAccountReqDTO;
import com.riffrain.dhlottery.module.user.dto.SignInDTO;
import com.riffrain.dhlottery.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signIn")
    private boolean signIn(@RequestBody CheckAccountReqDTO reqDTO) {
        return userService.signIn(new SignInDTO(reqDTO));
    }
}
