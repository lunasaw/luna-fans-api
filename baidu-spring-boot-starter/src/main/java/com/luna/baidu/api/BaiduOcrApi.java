package com.luna.baidu.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import com.luna.baidu.constant.ImageConstant;
import com.luna.baidu.dto.word.WordDTO;

import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.text.Base64Util;
import com.luna.common.text.CharsetKit;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * @author Luna@win10
 * @date 2020/5/3 9:58
 */
public class BaiduOcrApi {

    private static final Logger log = LoggerFactory.getLogger(BaiduOcrApi.class);

    /**
     * 百度云OCR识别
     * 
     * @param key
     * @param image
     * @param languageType
     * 识别语言类型，默认为CHN_ENG
     * 可选值包括：
     * - CHN_ENG：中英文混合
     * - ENG：英文
     * - JAP：日语
     * - KOR：韩语
     * - FRE：法语
     * - SPA：西班牙语
     * - POR：葡萄牙语
     * - GER：德语
     * - ITA：意大利语
     * - RUS：俄语
     * @return
     */
    public static List<WordDTO> baiDuOcr(String key, String image, String imageType, String languageType) {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.OCR,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key),
            HttpUtils.urlEncode(ImmutableMap.of(imageType, image, "language_type", languageType)));
        String response = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("baiDuOcr success response={}", response);
        return JSON.parseArray(JSON.parseObject(response).get("words_result").toString(), WordDTO.class);
    }

    public static List<WordDTO> baiDuOcrWithBase64(String key, String image, String languageType) {
        return baiDuOcr(key, image, ImageConstant.IMAGE.getImageStr(), languageType);
    }

    public static List<WordDTO> baiDuOcrWithUrl(String key, String image, String languageType) {
        return baiDuOcr(key, image, ImageConstant.URL.getImageStr(), languageType);
    }

    public static List<WordDTO> baiDuOcrWithFile(String key, String image, String languageType) {
        return baiDuOcr(key, Base64Util.encodeBase64(FileTools.read(image)), ImageConstant.URL.getImageStr(),
            languageType);
    }

    /**
     * 百度文字识别Ocr 附带位置返回 高精度版
     *
     * @param image
     * @return
     * @throws IOException
     */
    public static List<WordDTO> baiduOcrAndAddress(String key, String imageType, String image) {
        try {
            String imagePath = imageType + "=" + URLEncoder.encode(image, CharsetKit.UTF_8);
            HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.OCR_ADDRESS,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
                ImmutableMap.of("access_token", key), imagePath);
            String response = HttpUtils.checkResponseAndGetResult(httpResponse, true);
            log.info("baiduOcrAndAddress success response={}", response);
            return JSON.parseArray(JSON.parseObject(response).get("words_result").toString(), WordDTO.class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<WordDTO> baiduOcrAndAddressWithUrl(String key, String image) {
        return baiduOcrAndAddress(key, ImageConstant.URL.getImageStr(), image);
    }

    public static List<WordDTO> baiduOcrAndAddressWithBase64(String key, String image) {
        return baiduOcrAndAddress(key,
            ImageConstant.IMAGE.getImageStr(), image);
    }

    public static List<WordDTO> baiduOcrAndAddressWithFile(String key, String image) {
        return baiduOcrAndAddress(key,
            ImageConstant.IMAGE.getImageStr(), Base64Util.encodeBase64(FileTools.read(image)));
    }

    /**
     * 百度文字Ocr 附带位置返回普通版
     *
     * @param image
     * @return
     * @throws IOException
     */
    public static List<WordDTO> baiduOcrAndAddressNormal(String key, String imageType, String image) {
        try {
            String imagePath = imageType + "=" + URLEncoder.encode(image, CharsetKit.UTF_8);
            HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.OCR_ADDRESS_NORMAL,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
                ImmutableMap.of("access_token", key), imagePath);
            String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
            return JSON.parseArray(JSON.parseObject(s).get("words_result").toString(), WordDTO.class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<WordDTO> baiduOcrAndAddressNormalWithUrl(String key, String image) {
        return baiduOcrAndAddressNormal(key, ImageConstant.URL.getImageStr(), image);
    }

    public static List<WordDTO> baiduOcrAndAddressNormalWithBase64(String key, String image)
        throws UnsupportedEncodingException {
        return baiduOcrAndAddressNormal(key,
            ImageConstant.IMAGE.getImageStr(), image);
    }

    public static List<WordDTO> baiduOcrAndAddressNormalWithFile(String key, String image) {
        return baiduOcrAndAddressNormal(key, ImageConstant.IMAGE.getImageStr(),
            Base64Util.encodeBase64(FileTools.read(image)));
    }
}
