package com.riffrain.dhl.module.lotto.controller;

import com.riffrain.dhl.common.utils.UserUtils;
import com.riffrain.dhl.module.lotto.dto.OrderLottoReqDTO;
import com.riffrain.dhl.module.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LottoController {

    private final LottoService lottoService;

    @PostMapping("/order/lotto")
    private ResponseEntity<String> orderLotto(@RequestBody OrderLottoReqDTO reqDTO, HttpSession session) {
        UserUtils.isSignIn(session);
        return lottoService.buyLottoAllRandom(reqDTO.getLottoData());
    }
}
