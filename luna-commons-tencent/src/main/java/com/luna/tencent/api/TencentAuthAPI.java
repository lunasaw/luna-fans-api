package com.luna.tencent.api;

import com.alibaba.fastjson.JSONObject;
import com.luna.common.http.HttpUtils;
import com.luna.common.utils.Base64Util;
import com.luna.common.utils.img.ImageUtils;
import org.apache.http.HttpResponse;

import java.util.Map;

public class TencentAuthAPI {

    /**
     * 腾讯身份证识别
     * 
     * @param str 身份证照片可为URL
     * @return
     * @throws Exception
     */
    public static JSONObject idOcrCheck(String id, String key, String str) throws Exception {
        String body;
        // 判断是否为base64编码
        if (Base64Util.isBase64(str)) {
            body = "{" + "\"ImageBase64\": \"" + str + "\"" + "}";
        } else {
            body = "{" + "\"ImageBase64\": \"" + Base64Util.encodeBase64String(ImageUtils.getBytes(str)) + "\""
                + "}";
        }
        Map postHeader = TencentCloudAPITC3.getPostHeader(id, key, "ocr",
            TencentConstant.HOST_OCR, "ap-beijing", "IDCardOCR",
            "2018-11-19", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.HOST_OCR, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return response;
    }

    /**
     * 腾讯云手机号在网检查 不支持电信手机号
     * <p>
     * 0.4
     * <p/>
     * 
     * @param mobile
     * @return
     * @throws Exception
     */
    public static JSONObject mobileCheck(String id, String key, String mobile) throws Exception {
        String body = "{\n" +
            "   \"Mobile\":\"" + mobile + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "faceid",
                TencentConstant.FACE_CARD, "ap-beijing",
                "MobileNetworkTimeVerification",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CARD, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return response;
    }

    /**
     * 身份证两要素
     * <p>
     * 0.3
     * <p/>
     * 建议测试采用云市场Api
     *
     * @param id
     * @param name
     * @return
     * @throws Exception
     */
    public static JSONObject idNameCheck(String id, String key, String idCard, String name) throws Exception {
        String body = "{\n" +
            "\t\"IdCard\":\"" + idCard + "\",\n" +
            "   \"Name\":\"" + name + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "faceid",
                TencentConstant.FACE_CARD, "ap-beijing", "IdCardVerification",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CARD, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return response;
    }

    /**
     * 银行卡三要素 姓名 卡号 办理卡号证件
     * <p>
     * 0.4
     * <p/>
     * 
     * @param id
     * @param name
     * @param bank
     * @return 在网时长区间 格式为(a,b]，表示在网时长在a个月以上，b个月以下。若b为+时表示没有上限。
     * @throws Exception
     */
    public static JSONObject bankCardIdNameCheck(String id, String key, String idCard, String name, String bank)
        throws Exception {
        String body = "{\n" +
            "\t\"IdCard\":\"" + idCard + "\",\n" +
            "   \"Name\":\"" + name + "\",\n" +
            "   \"BankCard\":\"" + bank + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "faceid",
                TencentConstant.FACE_CARD, "ap-beijing", "BankCardVerification",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CARD, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return response;
    }

    /**
     * 腾讯人脸照片核身
     * 
     * @param base64Str 人脸照片
     * @param name 姓名
     * @param idCard 身份证号
     * @return
     * @throws Exception
     */
    public static JSONObject idAndFaceCheck(String id, String key, String base64Str, String name, String idCard)
        throws Exception {
        String body = "{\n" +
            "\t\"IdCard\":\"" + idCard + "\",\n" +
            "   \"Name\":\"" + name + "\",\n" +
            "   \"ImageBase64\":\"" + base64Str + "\"\n" +
            "}";
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "faceid",
                TencentConstant.FACE_CARD, "ap-beijing", "ImageRecognition",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CARD, "/", postHeader, null, body);
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return response;
    }

}