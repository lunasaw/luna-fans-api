package com.luna.baidu.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.luna.baidu.entity.Word;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import com.luna.common.utils.text.CharsetKit;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/5/3 9:58
 */
public class BaiduOcrApi {

    /**
     * 百度云OCR识别
     *
     * @param base64String
     * @return
     * @throws IOException
     */
    public static List<String> baiDuOcr(String key, String base64String) {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.OCR,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED), null,
            ImmutableMap.of("access_token", key, "image", base64String));
        JSONObject response = HttpUtils.getResponse(httpResponse);
        List<String> words = new ArrayList<>();
        List<JSONObject> datas = null;
        try {
            datas = JSON.parseArray(response.get("words_result").toString(), JSONObject.class);
        } catch (Exception e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, response.toString());
        }
        for (JSONObject data : datas) {
            words.add(data.get("words").toString());
        }
        return words;
    }

    /**
     * 百度文字识别Ocr 附带位置返回 高精度版
     * 
     * @param base64String
     * @return
     * @throws IOException
     */
    public static List<Word> baiduOcrAndAddress(String key, String base64String) {
        HttpResponse httpResponse = null;
        try {
            httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.OCR_ADDRESS,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
                ImmutableMap.of("access_token", key),
                "image=" + URLEncoder.encode(base64String, CharsetKit.UTF_8));
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }
        return getWords(httpResponse);
    }

    /**
     * 百度文字Ocr 附带位置返回普通版
     * 
     * @param base64String
     * @return
     * @throws IOException
     */
    public static List<Word> baiduOcrAndAddressNormal(String key, String base64String) {
        HttpResponse httpResponse = null;
        try {
            httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.OCR_ADDRESS_NORMAL,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
                ImmutableMap.of("access_token", key), "image=" + URLEncoder.encode(base64String, CharsetKit.UTF_8));
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }
        return getWords(httpResponse);
    }

    /**
     * 文字结果解析
     * 
     * @param httpResponse
     * @return
     * @throws IOException
     */
    private static List<Word> getWords(HttpResponse httpResponse) {
        JSONObject response = HttpUtils.getResponse(httpResponse);
        List<Word> words = new ArrayList<>();
        List<JSONObject> datas = null;
        try {
            datas = JSON.parseArray(response.get("words_result").toString(), JSONObject.class);
        } catch (Exception e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, response.toString());
        }
        for (JSONObject data : datas) {
            Word word = JSON.parseObject(data.get("location").toString(), Word.class);
            word.setWold(data.get("words").toString());
            words.add(word);
        }
        return words;
    }
}
