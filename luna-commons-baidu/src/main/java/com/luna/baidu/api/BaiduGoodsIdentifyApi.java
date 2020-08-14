package com.luna.baidu.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.baidu.dto.goods.GoodsInfoDTO;
import com.luna.common.http.HttpUtils;
import com.luna.common.http.HttpUtilsConstant;
import com.luna.common.utils.Base64Util;
import com.luna.common.utils.img.ImageUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

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
     * @throws UnsupportedEncodingException
     */
    public static List<GoodsInfoDTO> goodsIdentify(String key, String image, Integer baikeNum)
        throws UnsupportedEncodingException {
        log.info("goodsIdentify start");
        HashMap<String, Object> params = Maps.newHashMap();
        if (Base64Util.isBase64(image)) {
            params.put("image", image);
        } else {
            params.put("image", Base64Util.encodeBase64(ImageUtils.getBytes(image)));
        }

        if (baikeNum != null) {
            params.put("baike_num", baikeNum);
        }
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.GOODS_IDENTIFY,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED),
            ImmutableMap.of("access_token", key),
            HttpUtils.urlencode(params));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        List<GoodsInfoDTO> goodsInfoDTOS = JSON.parseArray(JSON.parseObject(s).getString("result"), GoodsInfoDTO.class);
        log.info("goodsIdentify success goodsInfoDTOS={}", goodsInfoDTOS);

        return goodsInfoDTOS;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        List<GoodsInfoDTO> goodsInfoDTOS =
            goodsIdentify("24.f4b0da25ae8e4925fc157a757d3035ff.2592000.1598949848.282335-19618961",
                "C:\\Users\\improve\\Pictures\\Camera Roll\\Pikachu.jpg", 3);
        System.out.println(JSON.toJSONString(goodsInfoDTOS));
    }

}
