package com.luna.tencent.api;

import java.util.HashMap;
import java.util.Map;

import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.encrypt.Base64Util;
import com.luna.tencent.response.card.IdCardPictureCheckInfoResponse;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import com.luna.tencent.response.card.IdCardAndBankCardCheckInfoResponse;
import com.luna.tencent.response.card.IdCardCheckInfoResponse;
import com.luna.tencent.response.card.IdCardOcrResponse;
import com.luna.tencent.response.message.MobileCheckInfoResponse;

public class TencentAuthAPI {

    private static final Logger log = LoggerFactory.getLogger(TencentAuthAPI.class);


    /**
     * 腾讯身份证识别
     * 
     * @param id
     * @param key
     * @param image 身份证照片可为URL,地址,Base64
     * @param cardSide 正反面 可为空 FRONT：身份证有照片的一面（人像面），BACK：身份证有国徽的一面（国徽面），该参数如果不填，将为您自动判断身份证正反面。
     * @param config
     * 以下可选字段均为bool 类型，默认false：
     * CropIdCard，身份证照片裁剪（去掉证件外多余的边缘、自动矫正拍摄角度）
     * CropPortrait，人像照片裁剪（自动抠取身份证头像区域）
     * CopyWarn，复印件告警
     * BorderCheckWarn，边框和框内遮挡告警
     * ReshootWarn，翻拍告警
     * DetectPsWarn，PS检测告警
     * TempIdWarn，临时身份证告警
     * InvalidDateWarn，身份证有效日期不合法告警
     * Quality，图片质量分数（评价图片的模糊程度）
     * MultiCardDetect，是否开启多卡证检测
     * @return
     * @throws Exception
     */
    public static IdCardOcrResponse idOcrCheck(String id, String key, String image, String cardSide,
        HashMap<String, Boolean> config) {
        HashMap<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("ImageBase64", image);
        } else if (HttpUtils.isUrl(image)) {
            map.put("ImageUrl", image);
        } else {
            map.put("ImageBase64", Base64Util.encodeBase64(FileTools.read(image)));
        }
        if (StringUtils.isNotEmpty(cardSide)) {
            map.put("CardSide", cardSide);
        }
        if (MapUtils.isNotEmpty(config)) {
            map.put("Config", JSON.toJSONString(config));
        }
        String body = JSON.toJSONString(map);
        Map postHeader = TencentCloudAPITC3.getPostHeader(id, key, "ocr", TencentConstant.HOST_OCR, "ap-beijing",
            "IDCardOCR", "2018-11-19", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.HOST_OCR, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        log.info("idOcrCheck start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), IdCardOcrResponse.class);
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
    public static MobileCheckInfoResponse mobileCheck(String id, String key, String mobile) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("Mobile", mobile);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "faceid",
                TencentConstant.FACE_CARD, "ap-beijing", "MobileNetworkTimeVerification", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CARD, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        log.info("mobileCheck start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), MobileCheckInfoResponse.class);
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
    public static IdCardCheckInfoResponse idNameCheck(String id, String key, String idCard, String name) {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("IdCard", idCard);
        map.put("Name", name);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "faceid",
                TencentConstant.FACE_CARD, "ap-beijing", "IdCardVerification",
                "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CARD, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        log.info("idNameCheck start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), IdCardCheckInfoResponse.class);
    }

    /**
     * 银行卡三要素 姓名 卡号 办理卡号证件
     * <p>
     * 0.4
     * <p/>
     * 
     * @param id
     * @param name
     * @param bankCard
     * @return 在网时长区间 格式为(a,b]，表示在网时长在a个月以上，b个月以下。若b为+时表示没有上限。
     * @throws Exception
     */
    public static IdCardAndBankCardCheckInfoResponse bankCardIdNameCheck(String id, String key, String idCard,
        String name,
        String bankCard)
    {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("IdCard", idCard);
        map.put("Name", name);
        map.put("BankCard", bankCard);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "faceid",
                TencentConstant.FACE_CARD, "ap-beijing", "BankCardVerification", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CARD, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        log.info("bankCardIdNameCheck start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), IdCardAndBankCardCheckInfoResponse.class);
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
    public static IdCardPictureCheckInfoResponse idAndFaceCheck(String id, String key, String base64Str, String name,
        String idCard)
    {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("IdCard", idCard);
        map.put("Name", name);
        map.put("ImageBase64", base64Str);
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "faceid", TencentConstant.FACE_CARD, "ap-beijing",
                "ImageRecognition", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CARD, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        log.info("idAndFaceCheck start id={}, key={}, response={}", id, key, s);
        return JSON.parseObject(JSON.parseObject(s).getString("Response"), IdCardPictureCheckInfoResponse.class);
    }

}