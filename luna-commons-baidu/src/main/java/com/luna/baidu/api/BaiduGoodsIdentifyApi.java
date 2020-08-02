package com.luna.baidu.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
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
 * @date 2020/4/30 13:48
 */
public class BaiduGoodsIdentifyApi {

    /**
     * 物品人像识别 可联系百度百科
     *
     * @param base64Str
     * @return List<String>
     * @throws IOException
     */
    public static List<String> goodsIdentify(String key,String base64Str) throws UnsupportedEncodingException {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.GOODS_IDENTIFY,
            ImmutableMap.of(
                "Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED, "Connection", "Keep-Alive"),
            ImmutableMap.of("access_token", key),
            "image=" + URLEncoder.encode(base64Str, CharsetKit.UTF_8));
        JSONObject response = HttpUtils.getResponse(httpResponse);
        List<JSONObject> datas = null;
        try {
            datas = JSON.parseArray(response.get("result").toString(), JSONObject.class);
        } catch (Exception e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, response.toString());
        }
        List<String> list = new ArrayList();
        for (JSONObject data : datas) {
            list.add(data.get("keyword").toString());
        }
        return list;
    }

}
