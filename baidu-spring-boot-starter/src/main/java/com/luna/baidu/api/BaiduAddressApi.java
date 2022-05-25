package com.luna.baidu.api;

import java.util.HashMap;

import com.luna.common.net.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.baidu.dto.map.ip2address.AddressResultDTO;
import com.luna.baidu.dto.map.weather.WeatherResultDTO;

/**
 * @author Luna@win10
 * @date 2020/5/4 9:18
 */
public class BaiduAddressApi {

    /**
     * ip 地址获取地图地址
     * 
     * @param ak 密钥
     * @param coor coor 不出现、或为空：百度墨卡托坐标，即百度米制坐标
     * coor = bd09ll：百度经纬度坐标，在国测局坐标基础之上二次加密而来
     * coor = gcj02：国测局02坐标，在原始GPS坐标基础上，按照国家测绘行业统一要求，加密后的坐标
     * @param ip ip地址
     * @return
     */
    public static AddressResultDTO ip2Address(String ak, String coor, String ip) {
        HashMap<String, String> map = Maps.newHashMap();
        if (StringUtils.isNotBlank(coor)) {
            map.put("coor", coor);
        }
        if (StringUtils.isNotBlank(ip)) {
            map.put("ip", ip);
        }
        map.put("ak", ak);
        HttpResponse httpResponse =
            HttpUtils.doGet(BaiduApiConstant.MAP_HOST, BaiduApiConstant.IP_TO_ADDRESS, null, map);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        return JSON.parseObject(s, AddressResultDTO.class);
    }

    /**
     * 国内经纬度天气查询 https://lbsyun.baidu.com/index.php?title=webapi/weather
     * 
     * @param ak 密钥
     * @param districtId 区县的行政区划编码，和location二选一
     * @param location 经纬度，经度在前纬度在后，逗号分隔。支持类型：bd09mc/bd09ll/wgs84/gcj02。开通高级权限后才能使用
     * @param dataType 请求数据类型。数据类型有：now/fc/index/alert/fc_hour/all，控制返回内容
     * @param coordType 支持类型:wgs84/bd09ll/bd09mc/gcj02
     */
    public static WeatherResultDTO district2Weather(String ak, String districtId, String location, String dataType,
        String coordType) {
        HashMap<String, String> map = Maps.newHashMap();
        if (StringUtils.isNotBlank(districtId)) {
            map.put("district_id", districtId);
        }
        if (StringUtils.isNotBlank(location)) {
            map.put("location", location);
        }
        if (StringUtils.isNotBlank(coordType)) {
            map.put("coordtype", coordType);
        }
        if (StringUtils.isNotBlank(dataType)) {
            map.put("data_type", dataType);
        }
        map.put("ak", ak);
        HttpResponse httpResponse =
            HttpUtils.doGet(BaiduApiConstant.MAP_HOST, BaiduApiConstant.FIND_WEARHER, ImmutableMap.of(), map);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, false);
        return JSON.parseObject(JSON.parseObject(s).getString("result"), WeatherResultDTO.class);
    }
}
