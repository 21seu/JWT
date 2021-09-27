package com.ftj.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.util.Calendar;

/**
 * Created by fengtj on 2021/9/27 8:09
 */
public class JWTUtil {

    private static final String SING = "!Q@W#E$R";

    public static String getToken() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 100);
        String token = JWT.create()
                .withExpiresAt(calendar.getTime())
                .withClaim("userId", 25)
                .withClaim("username", "ftj")
                .sign(Algorithm.HMAC256(SING));
        return token;
    }
}
