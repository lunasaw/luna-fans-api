package com.luna.tencent.api;

import java.util.Map;

import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.encrypt.Base64Util;
import org.apache.hc.core5.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.luna.tencent.response.group.CompareFaceResponse;

/**
 * @author luna
 * 2021/6/14
 */
public class TencentFaceApi {

    private static final Logger log = LoggerFactory.getLogger(TencentFaceApi.class);

    /**
     * 人脸对比
     *
     * @param id
     * @param key
     * @param imageA 生活照1
     * @param imageB 生活照2
     * @return
     * @throws Exception
     */
    public static CompareFaceResponse faceComparison(String id, String key, String imageA, String imageB) {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(imageA)) {
            map.put("ImageA", imageA);
        } else if (HttpUtils.isUrl(imageA)) {
            map.put("UrlA", imageA);
        } else {
            map.put("ImageA", Base64Util.encodeBase64(FileTools.read(imageA)));
        }
        if (Base64Util.isBase64(imageB)) {
            map.put("ImageB", imageB);
        } else if (HttpUtils.isUrl(imageB)) {
            map.put("UrlB", imageB);
        } else {
            map.put("ImageB", Base64Util.encodeBase64(FileTools.read(imageB)));
        }
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "CompareFace", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(s).getString("Response");
        CompareFaceResponse resultDTO = JSON.parseObject(response, CompareFaceResponse.class);
        log.info("idNameCheck start id={}, key={}, resultDTO={}", id, key, JSON.toJSONString(resultDTO));
        return resultDTO;
    }

    /**
     * 活体检测
     *
     * @param id
     * @param key
     * @param image
     * @return
     * @throws Exception
     */
    public static boolean faceLiveCheck(String id, String key, String image) {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else if (HttpUtils.isUrl(image)) {
            map.put("Url", image);
        } else {
            map.put("Image", Base64Util.encodeBase64(FileTools.read(image)));
        }
        String body = JSON.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "DetectLiveFace", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        log.info("faceLiveCheck start id={}, key={}, response={}", id, key, s);
        JSONObject response = JSON.parseObject(s);
        return JSON.parseObject(response.getString("Response")).getBooleanValue("IsLiveness");
    }
}
