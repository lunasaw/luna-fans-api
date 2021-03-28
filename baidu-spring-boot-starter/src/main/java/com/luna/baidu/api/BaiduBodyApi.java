package com.luna.baidu.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.luna.common.file.FileUtils;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.text.Base64Util;
import com.luna.common.text.CharsetKit;
import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.luna.baidu.dto.body.BodyCheckDTO;

/**
 * @Package: com.luna.baidu.api
 * @ClassName: BaiduBodyApi
 * @Author: luna
 * @CreateTime: 2020/8/10 20:01
 * @Description:
 */
public class BaiduBodyApi {

    /**
     * 人体检测
     * 
     * @param key 获取的Key
     * @param image 图像 URL或者Base64编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static List<BodyCheckDTO> checkBodies(String key, String image) throws UnsupportedEncodingException {
        if (Base64Util.isBase64(image)) {
            image = "image=" + URLEncoder.encode(image, CharsetKit.UTF_8);
        } else {
            image = "image="
                + URLEncoder.encode(Base64Util.encodeBase64(FileUtils.readFileToBytes(image)), CharsetKit.UTF_8);
        }
        HttpResponse response = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.BODIES,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key), image);
        String s = HttpUtils.checkResponseAndGetResult(response, true);
        return JSON.parseArray(JSON.parseObject(s).getString("person_info"), BodyCheckDTO.class);
    }
}
