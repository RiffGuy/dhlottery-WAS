package com.riffrain.dhlottery.module.lotto.service;

import com.riffrain.dhlottery.module.lotto.dto.LottoDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LottoService {

    ResponseEntity<String> buyLottoAllRandom(List<LottoDataDTO> data);

}
