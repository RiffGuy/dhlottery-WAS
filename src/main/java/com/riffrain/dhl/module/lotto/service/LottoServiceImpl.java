package com.riffrain.dhl.module.lotto.service;

import com.riffrain.dhl.module.lotto.dto.LottoDataDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LottoServiceImpl implements LottoService {

    private final HttpSession session;

    @Override
    public ResponseEntity<String> buyLottoAllRandom(List<LottoDataDTO> data) {
        Map<String, Object> parseData = this.getData();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate today = LocalDate.now();


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("round", String.valueOf(parseData.get("curRound")));
        params.add("direct", "172.17.20.51");
        params.add("nBuyAmount", String.valueOf(data.size() * 1000));
        params.add("param", data.toString() );
        params.add("ROUND_DRAW_DATE", today.format(formatter));
        params.add("WAMT_PAY_TLMT_END_DT", today.plusDays(366).format(formatter));
        params.add("gameCnt", String.valueOf(data.size()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("Connection", "keep-alive");
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("Host", "ol.dhlottery.co.kr");
        headers.add("Origin", "https://ol.dhlottery.co.kr");
        headers.add("Referer", "https://ol.dhlottery.co.kr/olotto/game/game645.do");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
        headers.add("X-Requested-With", "XMLHttpRequest");
        headers.add("Cookie", "JSESSIONID=" + session.getAttribute("dhCookie").toString() + ";");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        return new RestTemplate().exchange(
                "https://ol.dhlottery.co.kr/olotto/game/execBuy.do", //{요청할 서버 주소}
                HttpMethod.POST, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class // {요청시 반환되는 데이터 타입}
        );
    }


    private Map<String ,Object> getData() {
        Map<String ,Object> result = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("Connection", "keep-alive");
        headers.add("Host", "ol.dhlottery.co.kr");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
        headers.add("Cookie", "JSESSIONID=" + session.getAttribute("dhCookie").toString() + ";");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = new RestTemplate().exchange(
                "https://ol.dhlottery.co.kr/olotto/game/game645.do", //{요청할 서버 주소}
                HttpMethod.GET, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class // {요청시 반환되는 데이터 타입}
        );
        String body = response.getBody();

        if (null == body) { throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE); }

        result.put("curRound", body.split("id=\"curRound\">")[1].split("<")[0]);
        result.put("curBalance", body.split("id=\"moneyBalance\">")[1].split("<")[0].replace(",", ""));
        return result;
    }

}
