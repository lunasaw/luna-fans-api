package com.luna.common.utils.md5;

import java.util.regex.Pattern;

/**
 * @author Luna@win10
 * @date 2020/4/28 20:33
 */
public class Base64Util {

    private static final char[] ALPHABET    = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
        .toCharArray();

    private static final char   last2byte   = (char)Integer
        .parseInt("00000011", 2);
    private static final char   last4byte   = (char)Integer
        .parseInt("00001111", 2);
    private static final char   last6byte   = (char)Integer
        .parseInt("00111111", 2);
    private static final char   lead6byte   = (char)Integer
        .parseInt("11111100", 2);
    private static final char   lead4byte   = (char)Integer
        .parseInt("11110000", 2);
    private static final char   lead2byte   = (char)Integer
        .parseInt("11000000", 2);
    private static final char[] encodeTable = new char[] {'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', '+', '/'};

    private static int[]        toInt       = new int[128];

    static {
        for (int i = 0; i < ALPHABET.length; i++) {
            toInt[ALPHABET[i]] = i;
        }
    }

    /**
     * Base64 encoding.
     *
     * @param from
     * The src data.
     * @return cryto_str
     */
    public static String encodeBase64(byte[] from) {
        StringBuilder to = new StringBuilder((int)(from.length * 1.34) + 3);
        int num = 0;
        char currentByte = 0;
        for (int i = 0; i < from.length; i++) {
            num = num % 8;
            while (num < 8) {
                switch (num) {
                    case 0:
                        currentByte = (char)(from[i] & lead6byte);
                        currentByte = (char)(currentByte >>> 2);
                        break;
                    case 2:
                        currentByte = (char)(from[i] & last6byte);
                        break;
                    case 4:
                        currentByte = (char)(from[i] & last4byte);
                        currentByte = (char)(currentByte << 2);
                        if ((i + 1) < from.length) {
                            currentByte |= (from[i + 1] & lead2byte) >>> 6;
                        }
                        break;
                    case 6:
                        currentByte = (char)(from[i] & last2byte);
                        currentByte = (char)(currentByte << 4);
                        if ((i + 1) < from.length) {
                            currentByte |= (from[i + 1] & lead4byte) >>> 4;
                        }
                        break;
                    default:
                        break;
                }
                to.append(encodeTable[currentByte]);
                num += 6;
            }
        }
        if (to.length() % 4 != 0) {
            for (int i = 4 - to.length() % 4; i > 0; i--) {
                to.append("=");
            }
        }
        return to.toString();
    }

    /**
     * Translates the specified Base64 string into a byte array.
     *
     * @param s the Base64 string (not null)
     * @return the byte array (not null)
     */
    public static byte[] decodeBase64(String s) {
        int delta = s.endsWith("==") ? 2 : s.endsWith("=") ? 1 : 0;
        byte[] buffer = new byte[s.length() * 3 / 4 - delta];
        int mask = 0xFF;
        int index = 0;
        for (int i = 0; i < s.length(); i += 4) {
            int c0 = toInt[s.charAt(i)];
            int c1 = toInt[s.charAt(i + 1)];
            buffer[index++] = (byte)(((c0 << 2) | (c1 >> 4)) & mask);
            if (index >= buffer.length) {
                return buffer;
            }
            int c2 = toInt[s.charAt(i + 2)];
            buffer[index++] = (byte)(((c1 << 4) | (c2 >> 2)) & mask);
            if (index >= buffer.length) {
                return buffer;
            }
            int c3 = toInt[s.charAt(i + 3)];
            buffer[index++] = (byte)(((c2 << 6) | c3) & mask);
        }
        return buffer;
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
