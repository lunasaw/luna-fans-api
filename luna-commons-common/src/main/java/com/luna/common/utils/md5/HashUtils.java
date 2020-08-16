package com.luna.common.utils.md5;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author Tony
 */
public class HashUtils {

    /**
     * SHA512
     *
     * @param plain
     * @return
     */
    public static String SHA512(String plain) {
        return Hashing.sha512().hashString(plain, StandardCharsets.UTF_8).toString();
    }

    public static String randomHex32() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
