package com.luna.commons.utils;

import org.apache.commons.codec.binary.Base64;

import java.util.regex.Pattern;

/**
 * @author Luna@win10
 * @date 2020/4/28 20:33
 */
public class Base64Util {

    /**
     * 加密
     * 
     * @param bytes
     * @return
     */
    public static String encodeBase64String(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 解密
     * 
     * @param base64
     * @return
     */
    public static byte[] decodeBase64(String base64) {
        return Base64.decodeBase64(base64);
    }

    /**
     * 检测是否为base64编码
     * 
     * @param str
     * @return
     */
    public static boolean isBase64(String str) {
        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(base64Pattern, str);
    }

}
