package com.luna.common.utils.md5;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.FileException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * Md5加密方法
 *
 * @author luna
 */
public class Md5Utils {
    private static final Logger log       = LoggerFactory.getLogger(Md5Utils.class);

    /** 首先初始化一个字符数组，用来存放每个16进制字符 */
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
        'e', 'f'};

    /**
     * 获得一个字符串的MD5值
     *
     * @param input 输入的字符串
     * @return 输入字符串的MD5值
     *
     */
    public static String md5(String input) {
        if (input == null) {
            return null;
        }
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes("utf-8");
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 生成 HMACSHA256
     * 
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String hmacsha256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 获取文件的MD5值
     *
     * @param file
     * @return
     */
    public static String md5(File file) throws IOException {
        if (!file.isFile()) {
            log.info("文件" + file.getAbsolutePath() + "不存在或者不是文件");
            throw new FileException(ResultCode.PARAMETER_INVALID, "文件" + file.getAbsolutePath() + "不存在或者不是文件");
        }
        FileInputStream in = new FileInputStream(file);
        String result = md5(in);
        in.close();
        return result;
    }

    /**
     * file check
     *
     * @param file
     * @param sha256
     * @return
     */
    public static boolean checkFileWithSHA256(String file, String sha256) {
        try {
            HashCode hash = com.google.common.io.Files.asByteSource(new File(file)).hash(Hashing.sha256());
            String fileHash = hash.toString();
            return StringUtils.equalsIgnoreCase(fileHash, sha256);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取一个输入流的Md5值
     * 
     * @param in
     * @return
     */
    public static String md5(InputStream in) {

        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");

            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = in.read(buffer)) != -1) {
                messagedigest.update(buffer, 0, read);
            }

            in.close();

            String result = byteArrayToHex(messagedigest.digest());

            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String byteArrayToHex(byte[] byteArray) {
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }

        // 字符数组组合成字符串返回
        return new String(resultCharArray);

    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    /**
     * 得到32位的uuid
     * 
     * @return
     */
    public static String getUUID32() {

        return UUID.randomUUID().toString().replace("-", "").toLowerCase();

    }

    /**
     * 获取6位随机验证码
     *
     * @return
     */
    public static String getValidationCode() {

        return String.valueOf((new Random().nextInt(899999) + 100000));

    }
}
