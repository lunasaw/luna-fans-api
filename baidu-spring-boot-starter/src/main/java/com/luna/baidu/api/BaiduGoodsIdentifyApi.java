package com.luna.baidu.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import com.luna.baidu.constant.ImageConstant;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.text.Base64Util;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.baidu.dto.goods.GoodsInfoDTO;

/**
 * @author Luna@win10
 * @date 2020/4/30 13:48
 */
public class BaiduGoodsIdentifyApi {

    private static final Logger log = LoggerFactory.getLogger(BaiduGoodsIdentifyApi.class);

    /**
     * 物品人像识别 可联系百度百科
     * 
     * @param key
     * @param image
     * @param baikeNum 联系百度百科条目结果数
     * @return
     */
    public static List<GoodsInfoDTO> goodsIdentify(String key, String image, Integer baikeNum) {
        log.info("goodsIdentify start");

        ImmutableMap<String, Object> map =
            ImmutableMap.<String, Object>builder().put("image", image).put("baike_num", baikeNum).build();

        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.GOODS_IDENTIFY,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key),
            HttpUtils.urlEncode(map));
        String response = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        List<GoodsInfoDTO> goodsInfos =
            JSON.parseArray(JSON.parseObject(response).getString("result"), GoodsInfoDTO.class);
        log.info("goodsIdentify success goodsInfoDTOS={}, response={}", goodsInfos, response);
        return goodsInfos;
    }

    public static List<GoodsInfoDTO> goodsIdentifyWithBase64(String key, String image, Integer baikeNum) {
        return goodsIdentify(key, image, baikeNum);
    }

    public static List<GoodsInfoDTO> goodsIdentifyWithFile(String key, String image, Integer baikeNum) {
        return goodsIdentify(key, Base64Util.encodeBase64(FileTools.read(image)), baikeNum);
    }
}
