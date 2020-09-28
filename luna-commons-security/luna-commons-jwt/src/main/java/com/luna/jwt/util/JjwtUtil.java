package com.luna.jwt.util;

import com.luna.jwt.constant.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JjwtUtil {

    /**
     * 生成令牌
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        // 指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 当前系统时间
        long nowMillis = System.currentTimeMillis();
        // 令牌签发时间
        Date now = new Date(nowMillis);

        // 如果令牌有效期为null，则默认设置有效期1小时
        if (ttlMillis == null) {
            ttlMillis = JwtConstants.JWT_TTL;
        }

        // 令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        // 生成秘钥
        SecretKey secretKey = generalKey();

        // 封装Jwt令牌信息
        JwtBuilder builder = Jwts.builder()
            .setId(id)
            // 唯一的ID
            .setSubject(subject)
            // 主题 可以是JSON数据
            .setIssuer("admin")
            // 签发者
            .setIssuedAt(now)
            // 签发时间
            .signWith(signatureAlgorithm, secretKey)
            // 签名算法以及密匙
            .setExpiration(expDate);
        // 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JwtConstants.JWT_KEY.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析令牌数据
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(jwt)
            .getBody();
    }

    public static void main(String[] args) {
        String jwt = JjwtUtil.createJWT("weiyibiaoshi", "aaaaaa", null);
        System.out.println(jwt);
        try {
            Claims claims = JjwtUtil.parseJWT(jwt);
            System.out.println(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
