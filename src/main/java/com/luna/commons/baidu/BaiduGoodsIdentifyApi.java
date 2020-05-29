package com.luna.commons.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.luna.commons.http.HttpUtils;
import com.luna.commons.http.HttpUtilsConstant;
import com.luna.commons.utils.text.CharsetKit;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

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
    public static List<String> goodsIdentify(String base64Str) throws UnsupportedEncodingException {
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiContent.HOST, BaiduApiContent.GOODS_IDENTIFY,
            ImmutableMap.of(
                "Content-Type", HttpUtilsConstant.X_WWW_FORM_URLENCODED, "Connection", "Keep-Alive"),
            ImmutableMap.of("access_token", BaiduApiContent.BAIDU_KEY),
            "image=" + URLEncoder.encode(base64Str, CharsetKit.UTF_8));
        JSONObject response = HttpUtils.getResponse(httpResponse);
        List<JSONObject> datas = JSON.parseArray(response.get("result").toString(), JSONObject.class);
        List<String> list = new ArrayList();
        for (int i = 0; i < datas.size(); i++) {
            list.add(datas.get(i).get("keyword").toString());
        }
        return list;
    }

}
