package com.luna.baidu.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.text.Base64Util;
import com.luna.common.text.CharsetUtil;
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
     * @param image 图像数据，base64编码后进行urlencode，要求base64编码和urlencode后大小不超过4M。
     * 图片的base64编码是不包含图片头的，如(data:image/jpg;base64,)，
     * 支持图片格式：jpg、bmp、png，最短边至少50px，最长边最大4096px
     * @return
     */
    public static List<BodyCheckDTO> checkBodies(String key, String image) {
        HttpResponse response = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.BODIES,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key), image);
        String s = HttpUtils.checkResponseAndGetResult(response, true);
        return JSON.parseArray(JSON.parseObject(s).getString("person_info"), BodyCheckDTO.class);
    }

    public static List<BodyCheckDTO> checkBodiesWithBase64(String key, String image)
        throws UnsupportedEncodingException {
        return checkBodies(key, URLEncoder.encode(image, CharsetUtil.UTF_8));
    }

    public static List<BodyCheckDTO> checkBodiesWithFile(String key, String image) throws UnsupportedEncodingException {
        return checkBodies(key, URLEncoder.encode(Base64Util.encodeBase64(FileTools.read(image)), CharsetUtil.UTF_8));
    }
}
