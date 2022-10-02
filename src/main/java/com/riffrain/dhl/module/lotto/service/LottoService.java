package com.riffrain.dhl.module.lotto.service;

import com.riffrain.dhl.module.lotto.dto.LottoDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LottoService {

    ResponseEntity<String> buyLottoAllRandom(List<LottoDataDTO> data);

}
