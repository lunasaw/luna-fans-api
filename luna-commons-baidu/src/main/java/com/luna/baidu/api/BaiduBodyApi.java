package com.luna.baidu.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.luna.baidu.dto.body.BodyCheckDTO;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import com.luna.common.utils.md5.Base64Util;
import com.luna.common.utils.img.ImageUtils;
import com.luna.common.utils.text.CharsetKit;
import org.apache.http.HttpResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

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
     * @param key
     * @param image
     * @return
     * @throws UnsupportedEncodingException
     */
    public static List<BodyCheckDTO> checkBodies(String key, String image) throws UnsupportedEncodingException {
        if (Base64Util.isBase64(image)) {
            image = "image=" + URLEncoder.encode(image, CharsetKit.UTF_8);
        } else {
            image = "image=" + URLEncoder.encode(Base64Util.encodeBase64(ImageUtils.getBytes(image)), CharsetKit.UTF_8);
        }
        HttpResponse response = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.BODIES,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key), image);
        String s = HttpUtils.checkResponseAndGetResult(response, true);
        return JSON.parseArray(JSON.parseObject(s).getString("person_info"), BodyCheckDTO.class);
    }
}
