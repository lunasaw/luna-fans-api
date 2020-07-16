package com.luna.tencent.api;

import java.io.IOException;

import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.luna.common.http.HttpUtils;

/**
 * @author Luna@win10
 * @date 2020/4/26 13:21
 */
public class TencentMapApi {

    /**
     * 腾讯地图经纬度返回位置
     * 
     * @param longitude 经线
     * @param latitude 纬线
     * @return
     */
    public static JSONObject findAddr(String key, String longitude, String latitude) throws IOException {
        // 腾讯坐标反查
        String address = longitude + "," + latitude;

        HttpResponse httpResponse =
            HttpUtils.doGet(TencentConstant.HOST_MAP, TencentConstant.LONGITUDE_AND_LATITUDE_2_ADDRESS, null,
                ImmutableMap.of("location", address, "key", key, "get_poi", "0"));
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return response;
    }

    /**
     * 腾讯地图ip返回位置信息
     * 
     * @param ip ip地址
     * @return
     */
    public static JSONObject ip2Address(String key, String ip) throws IOException {
        HttpResponse httpResponse = HttpUtils.doGet(TencentConstant.HOST_MAP, TencentConstant.IP_2_ADDRESS, null,
            ImmutableMap.of("ip", ip, "key", key));
        JSONObject response = HttpUtils.getResponse(httpResponse);
        return response;
    }

}
