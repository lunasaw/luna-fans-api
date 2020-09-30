package com.luna.jwt.util;

import com.google.common.collect.Maps;
import com.luna.common.utils.text.RandomStr;
import com.luna.common.utils.text.StringUtils;
import com.luna.jwt.constant.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.luna.jwt.util
 * @ClassName: JjwtUtil
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
public class JjwtUtil {

    /**
     * 生成令牌
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJwt(String id, String user, String subject, Map<String, Object> claims, Long ttlMillis) {
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

        // 默认用户
        if (StringUtils.isEmpty(user)) {
            user = JwtConstants.JWT_ADMIN;
        }

        // id 生成
        if (StringUtils.isEmpty(id)) {
            id = RandomStr.generateNonceStr();
        }

        if (StringUtils.isEmpty(subject)) {
            subject = JwtConstants.JWT_SUBJECT;
        }

        // 令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        // 生成秘钥
        SecretKey secretKey = generalKey();

        // 封装Jwt令牌信息
        JwtBuilder builder = Jwts.builder()
            // 唯一的ID
            .setId(id)
            // 主题 可以是JSON数据
            .setSubject(subject)
            // 签发者
            .setIssuer(user)
            // 签发时间
            .setIssuedAt(now)
            // 签名算法以及密匙
            .signWith(signatureAlgorithm, secretKey)
            // 设置过期时间
            .setExpiration(expDate)
            // 私密信息部分
            .addClaims(claims);

        return builder.compact();
    }

    public static String createJwt(String id, String user, String subject, Map<String, Object> claims) {
        return createJwt(id, user, subject, claims);
    }

    public static String createJwt(String id, String user, String subject) {
        return createJwt(id, user, subject);
    }

    public static String createJwt(String id, String subject) {
        return createJwt(id, subject);
    }

    /**
     * 使用密钥生成密钥串 secretKey
     *
     * @param key 密钥
     * @return
     */
    public static SecretKey generalKey(String key) {
        if (key == null) {
            key = JwtConstants.JWT_KEY;
        }
        return generalKey(key);
    }

    /**
     * 使用默认密钥生成密钥串
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JwtConstants.JWT_KEY.getBytes());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 使用默认密钥解析令牌数据
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
            .setSigningKey(generalKey())
            .parseClaimsJws(jwt)
            .getBody();
    }

    /**
     * 解析令牌
     * 
     * @param key 密钥
     * @param jwt 令牌
     * @return
     */
    public static Claims parseJwt(String key, String jwt) {
        Claims body = Jwts.parser()
            .setSigningKey(generalKey(key))
            .parseClaimsJws(jwt)
            .getBody();
        return body;
    }

    /**
     * 解析公共头部信息
     * 
     * @param body
     */
    private static Map<String, Object> getPublicBody(Claims body) {
        HashMap<String, Object> map = Maps.newHashMap();
        // 创建JWT时的时间戳
        map.put("iat", body.getIssuedAt());
        // jwt签发者
        map.put("iss", body.getIssuer());
        // jwt主题
        map.put("sub", body.getSubject());
        // 接收jwt的一方
        map.put("aud", body.getAudience());
        // jwt的过期时间，这个过期时间必须要大于签发时间
        map.put("exp", body.getExpiration());
        // 定义在什么时间之前，该jwt都是不可用的.
        map.put("nbf", body.getNotBefore());
        // jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
        map.put("jti", body.getId());
        return map;
    }

    public static void main(String[] args) {
        String jwt = JjwtUtil.createJwt("weiyibiaoshi", "admin", "aaaaaa", null, null);
        System.out.println(jwt);
        try {
            Claims claims = JjwtUtil.parseJwt(jwt);
            System.out.println(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
