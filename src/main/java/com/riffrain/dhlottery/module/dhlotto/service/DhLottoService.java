package com.riffrain.dhlottery.module.dhlotto.service;

import com.riffrain.dhlottery.module.dhlotto.dto.DhLottoDataDTO;
import com.riffrain.dhlottery.module.dhlotto.dto.SignInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DhLottoService {

    boolean signIn(SignInDTO signInDTO);

    ResponseEntity<String> buyLottoAllRandom(List<DhLottoDataDTO> data);

}
