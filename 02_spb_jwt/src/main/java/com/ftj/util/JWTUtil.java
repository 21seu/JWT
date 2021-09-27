package com.ftj.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by fengtj on 2021/9/27 8:09
 */
public class JWTUtil {

    private static final String SIGN = "!Q@W#E$R";

    /**
     * 生成token header.payload.sign
     *
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7); //默认七天过期

        //创建jwt bulider
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String token = builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    /**
     * 验证token合法性，且获取token信息
     *
     * @param token
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * 获取token信息方法
     *
     * @param token
     * @return
     */
//    public static DecodedJWT getTokenInfos(String token) {
//        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
//        return verify;
//    }
}
