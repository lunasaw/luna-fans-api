package com.luna.tencent.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.luna.common.http.HttpUtils;
import com.luna.common.utils.md5.Base64Util;
import com.luna.common.utils.img.ImageUtils;
import com.luna.tencent.dto.group.CompareFaceResultDTO;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Package: com.luna.tencent.api
 * @ClassName: TecentPersonGroupApi
 * @Author: luna
 * @CreateTime: 2020/8/14 23:31
 * @Description:
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
    public static CompareFaceResultDTO faceComparison(String id, String key, String imageA, String imageB)
        throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(imageA)) {
            map.put("ImageA", imageA);
        } else if (HttpUtils.isNetUrl(imageA)) {
            map.put("UrlA", imageA);
        } else {
            map.put("ImageA", Base64Util.encodeBase64(ImageUtils.getBytes(imageA)));
        }
        if (Base64Util.isBase64(imageB)) {
            map.put("ImageB", imageB);
        } else if (HttpUtils.isNetUrl(imageB)) {
            map.put("UrlB", imageB);
        } else {
            map.put("ImageB", Base64Util.encodeBase64(ImageUtils.getBytes(imageB)));
        }
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "iai",
                TencentConstant.FACE_CHECK, "", "CompareFace", "2018-03-01", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(s).getString("Response");
        CompareFaceResultDTO resultDTO = JSON.parseObject(response, CompareFaceResultDTO.class);
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
    public static boolean faceLiveCheck(String id, String key, String image)
        throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            map.put("Image", image);
        } else if (HttpUtils.isNetUrl(image)) {
            map.put("Url", image);
        } else {
            map.put("Image", Base64Util.encodeBase64(ImageUtils.getBytes(image)));
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
        return JSON.parseObject(response.getString("Response")).getBoolean("IsLiveness");
    }

}
