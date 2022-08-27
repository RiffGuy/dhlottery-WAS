package com.riffrain.dhlottery.common.utils;

import com.riffrain.dhlottery.common.exception.CommonException;
import com.riffrain.dhlottery.common.type.CommonErrorType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class UserUtils {

    public static void isSignIn(HttpSession session) {
        if ("null".equals(String.valueOf(session.getAttribute("userId")))) {
            throw new CommonException(CommonErrorType.USER_NOT_SIGN_IN);
        }
    }
}
