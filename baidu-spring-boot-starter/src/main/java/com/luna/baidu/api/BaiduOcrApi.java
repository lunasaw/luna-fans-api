package com.luna.baidu.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

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
    public static List<WordDTO> baiDuOcr(String key, String image, String languageType)
        throws UnsupportedEncodingException {
        HashMap<String, Object> map = Maps.newHashMap();
        if (HttpUtils.isNetUrl(image)) {
            map.put("url", image);
        } else if (Base64Util.isBase64(image)) {
            map.put("image", image);
        } else {
            map.put("image", Base64Util.encodeBase64(FileTools.read(image)));
        }

        map.put("language_type", languageType);
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.OCR,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key), HttpUtils.urlEncode(map));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        return JSON.parseArray(JSON.parseObject(s).get("words_result").toString(), WordDTO.class);
    }

    /**
     * 百度文字识别Ocr 附带位置返回 高精度版
     *
     * @param image
     * @return
     * @throws IOException
     */
    public static List<WordDTO> baiduOcrAndAddress(String key, String image) throws UnsupportedEncodingException {
        image = getString(image);
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.OCR_ADDRESS,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key), image);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        return JSON.parseArray(JSON.parseObject(s).get("words_result").toString(), WordDTO.class);
    }

    /**
     * 字符串判断操作
     * 
     * @param image
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String getString(String image) throws UnsupportedEncodingException {
        if (HttpUtils.isNetUrl(image)) {
            image = "url=" + URLEncoder.encode(image, CharsetKit.UTF_8);
        } else if (Base64Util.isBase64(image)) {
            image = "image=" + URLEncoder.encode(image, CharsetKit.UTF_8);
        } else {
            image = "image="
                + URLEncoder.encode(Base64Util.encodeBase64(FileTools.read(image)), CharsetKit.UTF_8);
        }
        return image;
    }

    /**
     * 百度文字Ocr 附带位置返回普通版
     *
     * @param image
     * @return
     * @throws IOException
     */
    public static List<WordDTO> baiduOcrAndAddressNormal(String key, String image) throws UnsupportedEncodingException {
        image = getString(image);
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.OCR_ADDRESS_NORMAL,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key), image);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        return JSON.parseArray(JSON.parseObject(s).get("words_result").toString(), WordDTO.class);
    }

}
