package com.luna.common.utils.text;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * @Package: com.luna.common.utils.text
 * @ClassName: RandomStrUtil
 * @Author: luna
 * @CreateTime: 2020/8/16 13:43
 * @Description:
 */
public class RandomStrUtil {

    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM  = new SecureRandom();

    public static String generateNonceStrWithUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

}
