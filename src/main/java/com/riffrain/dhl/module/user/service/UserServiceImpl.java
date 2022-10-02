package com.riffrain.dhl.module.user.service;

import com.riffrain.dhl.common.exception.CommonException;
import com.riffrain.dhl.common.type.CommonErrorType;
import com.riffrain.dhl.module.user.dto.SignInDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final HttpSession session;

    @Override
    public boolean signIn(SignInDTO dto) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("userId", dto.getUserId());
        params.add("password", dto.getPassword());


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Host", "www.dhlottery.co.kr");
        headers.add("Origin", "https://www.dhlottery.co.kr");
        headers.add("Referer", "https://www.dhlottery.co.kr/user.do?method=login&returnUrl=%2F");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);


        ResponseEntity<String> response = new RestTemplate().exchange(
                "https://www.dhlottery.co.kr/userSsl.do?method=login", //{요청할 서버 주소}
                HttpMethod.POST, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class // {요청시 반환되는 데이터 타입}
        );

        String headerStr = response.getHeaders().toString();
        String jSessionId = headerStr.split("JSESSIONID=")[1].split(";")[0];
        session.setAttribute("dhCookie", jSessionId);
        this.checkSignIn(dto.getUserId());
        session.setAttribute("userId", dto.getUserId());

        return true;

    }

    private void checkSignIn(String userId) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "JSESSIONID=" + session.getAttribute("dhCookie").toString() + ";");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);


        ResponseEntity<String> response = new RestTemplate().exchange(
                "https://dhlottery.co.kr/common.do?method=main", //{요청할 서버 주소}
                HttpMethod.GET, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class // {요청시 반환되는 데이터 타입}
        );

        String body = response.getBody();
        if (null == body) { throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE); }
        String checkUserId = body.split("var userId = \"")[1].split("\"")[0];

        if (!userId.equals(checkUserId)) { throw new CommonException(CommonErrorType.USER_NOT_FOUND); }
    }
}
