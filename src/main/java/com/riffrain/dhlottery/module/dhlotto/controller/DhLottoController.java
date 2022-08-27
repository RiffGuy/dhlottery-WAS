package com.riffrain.dhlottery.module.dhlotto.controller;

import com.riffrain.dhlottery.module.dhlotto.dto.CheckAccountReqDTO;
import com.riffrain.dhlottery.module.dhlotto.dto.SignInDTO;
import com.riffrain.dhlottery.module.dhlotto.service.DhLottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DhLottoController {

    private final DhLottoService dhLottoService;

    @PostMapping("/checkAccount")
    private boolean checkAccount(@RequestBody CheckAccountReqDTO reqDTO) {
        return dhLottoService.signIn(new SignInDTO(reqDTO));
    }


}
