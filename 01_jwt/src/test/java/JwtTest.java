import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengtj on 2021/9/26 22:46
 */
public class JwtTest {

    @Test
    public void contextLoads() {
        Map<String, Object> map = new HashMap<String, Object>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 20);
        String token = JWT.create()
                .withHeader(map) //head 这里header可以不写 不写默认也是它
                .withClaim("userId", 21) //payload
                .withExpiresAt(instance.getTime()) //指定令牌的过期时间
                .withClaim("username", "zhangsan")
                .sign(Algorithm.HMAC256("!Q2W#E$RW"));//签名

        /**
         * eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
         * eyJleHAiOjE2MzI2NjgwMzMsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.
         * eeS4z6JxnLfH337te59gKM7kC4Qw92E0Xp5vQL6aTcA
         */
        System.out.println(token);
    }

    @Test
    public void test() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!Q2W#E$RW")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzI2NjgwMzMsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.eeS4z6JxnLfH337te59gKM7kC4Qw92E0Xp5vQL6aTcA");
        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("username").asString());
        System.out.println(verify.getExpiresAt());
    }

}
