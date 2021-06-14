package com.luna.tencent.api;

import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.text.Base64Util;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class TencentCloudAPITC3 {

    /**
     * 加密规则
     * 
     * @param source
     * @param secretId
     * @param secretKey
     * @param datetime
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public static String calcAuthorization(String source, String secretId, String secretKey, String datetime) {
        try {
            String signStr = "x-date: " + datetime + "\n" + "x-source: " + source;
            Mac mac = Mac.getInstance("HmacSHA1");
            Key sKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
            mac.init(sKey);
            byte[] hash = mac.doFinal(signStr.getBytes(StandardCharsets.UTF_8));
            String sig = Base64Util.encodeBase64(hash);

            String auth =
                "hmac id=\"" + secretId + "\", algorithm=\"hmac-sha1\", headers=\"x-date x-source\", signature=\""
                    + sig + "\"";
            return auth;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hmac256(byte[] key, String msg) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
            mac.init(secretKeySpec);
            return mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hmac1(String originalText, String secretKey) {
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec secretKeySpec =
                new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
            mac.init(secretKeySpec);
            return mac.doFinal(originalText.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }

    /**
     * 获取请求头
     * 
     * @param id
     * @param key
     * @param service
     * @param host
     * @param region 地域参数，用来标识希望操作哪个地域的数据。接口接受的地域取值参考接口文档中输入参数公共参数 Region
     * 的说明。注意：某些接口不需要传递该参数，接口文档中会对此特别说明，此时即使传递该参数也不会生效。
     * @param action 操作的接口名称。取值参考接口文档中输入参数公共参数 Action 的说明。例如云服务器的查询实例列表接口，取值为 DescribeInstances。
     * @param version 操作的 API 的版本。取值参考接口文档中入参公共参数 Version 的说明。例如云服务器的版本 2017-03-12。
     * @param body
     * @return
     * @throws Exception
     */
    public static TreeMap getPostHeader(String id, String key, String service, String host, String region,
        String action, String version,
        String body) {
        try {
            String algorithm = "TC3-HMAC-SHA256";
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 注意时区，否则容易出错
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            String date = sdf.format(new Date(Long.parseLong(timestamp + "000")));

            // ************* 步骤 1：拼接规范请求串 *************
            String httpRequestMethod = "POST";
            String canonicalUri = "/";
            String canonicalQueryString = "";
            String canonicalHeaders = "content-type:application/json; charset=utf-8\n" + "host:" + host + "\n";
            String signedHeaders = "content-type;host";

            // 请求正文
            String payload = body;
            String hashedRequestPayload = sha256Hex(payload);
            String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n"
                + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;

            // ************* 步骤 2：拼接待签名字符串 *************
            String credentialScope = date + "/" + service + "/" + "tc3_request";
            String hashedCanonicalRequest = sha256Hex(canonicalRequest);
            String stringToSign =
                algorithm + "\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;

            // ************* 步骤 3：计算签名 *************
            byte[] secretDate = hmac256(("TC3" + key).getBytes(StandardCharsets.UTF_8), date);
            byte[] secretService = hmac256(secretDate, service);
            byte[] secretSigning = hmac256(secretService, "tc3_request");
            String signature = DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();

            // ************* 步骤 4：拼接 Authorization *************
            String authorization = algorithm + " " + "Credential=" + id + "/" + credentialScope + ", "
                + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;

            TreeMap<String, String> headers = new TreeMap<String, String>();
            headers.put("Authorization", authorization);
            headers.put("Content-Type", HttpUtilsConstant.JSON);
            headers.put("Host", host);
            headers.put("X-TC-Action", action);
            headers.put("X-TC-Timestamp", timestamp);
            headers.put("X-TC-Version", version);
            headers.put("X-TC-Region", region);
            return headers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}