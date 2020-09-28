package com.luna.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/**
 * @Package: com.luna.jwt
 * @ClassName: JwtTest
 * @Author: luna
 * @CreateTime: 2020/9/28 14:55
 * @Description:
 */
public class JwtTest {

    /**
     * 令牌生成
     */
    @Test
    public void create() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 20);

        String sign = JWT.create()
            .withClaim("name", "luna")
            .withClaim("userId", "101")
            .withExpiresAt(calendar.getTime())
            .sign(Algorithm.HMAC256("luna"));

        System.out.println(sign);
    }

    @Test
    public void verify() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 20);

        String sign = JWT.create()
            .withClaim("name", "luna")
            .withClaim("userId", "101")
            .withExpiresAt(calendar.getTime())
            .sign(Algorithm.HMAC256("luna"));

        System.out.println(sign);

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("luna")).build();

        DecodedJWT verify = verifier.verify(sign);

        System.out.println(verify.getExpiresAt());

        Map<String, Claim> claims = verify.getClaims();
        Iterator<Map.Entry<String, Claim>> entries = claims.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Claim> entry = entries.next();
            System.out.println(entry.getKey() + ">>" + claims.get(entry.getKey()).asString());
        }
    }
}
