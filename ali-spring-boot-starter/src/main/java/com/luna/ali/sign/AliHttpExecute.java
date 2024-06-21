package com.luna.ali.sign;

import com.google.common.collect.ImmutableMap;
import com.luna.common.encrypt.Base64Util;
import com.luna.common.net.base.HttpBaseUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author luna
 */
@Data
public class AliHttpExecute {

    private String                                  apiEndpoint;

    private String                                  apiVersion;

    private static final java.text.SimpleDateFormat DF = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    static {
        DF.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
    }

    static final String API_HTTP_METHOD = "POST";

    public static String getSignature(String action, String accessKeyId, String accessSecret, Map<String, String> bizParams, String apiVersion)
        throws Exception {
        java.util.Map<String, String> params = new java.util.HashMap<>();
        // 1. 系统参数
        params.put("SignatureMethod", "HMAC-SHA1");
        // //防止重放攻击
        params.put("SignatureNonce", java.util.UUID.randomUUID().toString());
        params.put("AccessKeyId", accessKeyId);
        params.put("SignatureVersion", "1.0");
        params.put("Timestamp", DF.format(new java.util.Date()));
        params.put("Format", "JSON");
        // 2. 业务API参数
        params.put("RegionId", "cn-shanghai");
        params.put("Version", apiVersion);
        params.put("Action", action);
        if (bizParams != null && !bizParams.isEmpty()) {
            params.putAll(bizParams);
        }
        // 3. 去除签名关键字Key
        if (params.containsKey("Signature")) {
            params.remove("Signature");
        }
        // 4. 参数KEY排序
        java.util.TreeMap<String, String> sortParams = new java.util.TreeMap<>();
        sortParams.putAll(params);
        // 5. 构造待签名的字符串
        java.util.Iterator<String> it = sortParams.keySet().iterator();
        StringBuilder sortQueryStringTmp = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(params.get(key)));
        }
        // // 去除第一个多余的&符号
        String sortedQueryString = sortQueryStringTmp.substring(1);
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(API_HTTP_METHOD).append("&");
        stringToSign.append(specialUrlEncode("/")).append("&");
        stringToSign.append(specialUrlEncode(sortedQueryString));
        String sign = sign(accessSecret + "&", stringToSign.toString());
        // 6. 签名最后也要做特殊URL编码
        String signature = specialUrlEncode(sign);
        return signature + sortQueryStringTmp;
    }

    public static String execute(String action, String accessKeyId, String accessSecret, Map<String, String> bizParams, String apiVersion,
        String apiEndpoint) {
        try {
            String signature = getSignature(action, accessKeyId, accessSecret, bizParams, apiVersion);
            String string = HttpBaseUtils.doURLWithString("https://" + apiEndpoint, "/?Signature=" + signature, API_HTTP_METHOD, ImmutableMap.of(), null, StringUtils.EMPTY);
            return string;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String specialUrlEncode(String value) throws Exception {
        return java.net.URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    public static String sign(String accessSecret, String stringToSign) throws Exception {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
        mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA1"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return Base64Util.encodeBase64(signData);
    }

}
