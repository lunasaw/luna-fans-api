package com.luna.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.luna.jwt.constant.JwtConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.luna.jwt.util
 * @ClassName: JwtUtil
 * @Author: luna
 * @CreateTime: 2020/9/28 15:17
 * @Description:
 * 头部公共部分
 * iss: jwt签发者
 * sub: jwt所面向的用户
 * aud: 接收jwt的一方
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 * iat: jwt的签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 */
public class JwtUtil {

    /**
     * 生成Token
     * 
     * @param claim
     * @param signatur
     * @param ttlMillis
     * @return
     */
    public static String createToken(Map<String, String> claim, String signatur, Long ttlMillis) {
        if (signatur == null) {
            signatur = JwtConstants.JWT_KEY;
        }

        // 当前系统时间
        long nowMillis = System.currentTimeMillis();
        // 如果令牌有效期为null，则默认设置有效期1小时
        if (ttlMillis == null) {
            ttlMillis = JwtConstants.JWT_TTL;
        }
        // 令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        JWTCreator.Builder builder = JWT.create();
        claim.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        builder.withExpiresAt(expDate);

        return builder.sign(Algorithm.HMAC256(signatur));
    }

    /**
     * token 验签
     *
     * @param sign
     */
    public static DecodedJWT isToken(String sign) {
        return isToken(JwtConstants.JWT_KEY, sign);
    }

    /**
     * token 验签
     * 
     * @param signatur
     * @param sign
     */
    public static DecodedJWT isToken(String signatur, String sign) {
        if (signatur == null) {
            signatur = JwtConstants.JWT_KEY;
        }
        return JWT.require(Algorithm.HMAC256(signatur)).build().verify(sign);
    }

    /**
     * 获取Claim数据
     *
     * @param sign
     * @return
     */
    public static Map<String, String> getClaim(String sign) {
        return getClaim(JwtConstants.JWT_KEY, sign);
    }

    /**
     * 获取Claim数据
     * 
     * @param signatur
     * @param sign
     * @return
     */
    public static Map<String, String> getClaim(String signatur, String sign) {
        DecodedJWT token = isToken(signatur, sign);
        HashMap<String, String> map = Maps.newHashMap();
        token.getClaims().forEach((k, v) -> {
            map.put(k, v.asString());
        });
        return map;
    }
}
