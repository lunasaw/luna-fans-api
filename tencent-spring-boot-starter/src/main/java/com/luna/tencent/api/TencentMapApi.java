package com.luna.tencent.api;

import java.util.HashMap;
import java.util.List;

import com.luna.common.exception.BaseException;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.IPAddressUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.common.dto.constant.ResultCode;
import com.luna.tencent.dto.map.AddressResultDTO;
import com.luna.tencent.dto.map.Ip2AddressResultDTO;
import com.luna.tencent.dto.map.KeyWordSearchResultDTO;
import com.luna.tencent.dto.map.LocationDTO;

/**
 * @author Luna@win10
 * @date 2020/4/26 13:21
 */
public class TencentMapApi {

    private static final Logger log = LoggerFactory.getLogger(TencentMapApi.class);

    /**
     * 腾讯地图经纬度返回位置
     * 
     * @param longitude 经度
     * @param latitude 纬线
     * @return
     */
    public static AddressResultDTO findAddr(String key, String longitude, String latitude) {
        if (StringUtils.isEmpty(key)) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "缺少可用的key");
        }
        log.info("findAddr start key={},longitude={},latitude={}", key, longitude, latitude);
        // 腾讯坐标反查
        String address = latitude + "," + longitude;
        HttpResponse httpResponse =
            HttpUtils.doGet(TencentConstant.HOST_MAP, TencentConstant.LONGITUDE_LATITUDE_FOR_ADDRESS, null,
                ImmutableMap.of("location", address, "key", key, "get_poi", "0"));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        AddressResultDTO result = JSON.parseObject(JSON.parseObject(s).getString("result"), AddressResultDTO.class);
        log.info("findAddr success result={},longitude={},latitude={}", JSON.toJSONString(result), longitude, latitude);
        return result;
    }

    /**
     * 腾讯地图ip返回位置信息
     * 
     * @param ip ip地址
     * @return
     */
    public static Ip2AddressResultDTO ip2Address(String key, String ip) {
        if (StringUtils.isEmpty(key)) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "缺少可用的key");
        }
        if (!IPAddressUtil.isIPv4LiteralAddress(ip) || IPAddressUtil.isIPv6LiteralAddress(ip)) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "ip地址非法");
        }
        HttpResponse httpResponse = HttpUtils.doGet(TencentConstant.HOST_MAP, TencentConstant.IP_2_ADDRESS, null,
            ImmutableMap.of("ip", ip, "key", key));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        Ip2AddressResultDTO result =
            JSON.parseObject(JSON.parseObject(s).getString("result"), Ip2AddressResultDTO.class);
        log.info("findAddr success result={},ip={}", JSON.toJSONString(result), ip);
        return result;
    }

    /**
     * 关键词搜搜
     *
     * @param key
     * @param keyWord 可多词逗号分割 关键字见
     * [腾讯地图POI分类关键词](https://lbs.qq.com/service/webService/webServiceGuide/webServiceAppendix)
     * @param region 城市,默认本地城市搜索,没有结果则扩散全国
     */
    public static List<KeyWordSearchResultDTO> keyWordSearch(String key, String keyWord, String region) {
        if (StringUtils.isEmpty(key)) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "缺少可用的key");
        }
        HashMap<String, String> param = Maps.newHashMap();
        param.put("key", key);
        param.put("region", region);
        param.put("keyword", keyWord);
        HttpResponse httpResponse = HttpUtils.doGet(TencentConstant.HOST_MAP, TencentConstant.SUGGESTION, null,
            param);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        List<KeyWordSearchResultDTO> result =
            JSON.parseArray(JSON.parseObject(s).getString("data"), KeyWordSearchResultDTO.class);
        log.info("findAddr success result={},keyWord={},region={}", JSON.toJSONString(result), keyWord, region);
        return result;
    }

    /**
     * 腾讯地图位置返回经纬度坐标
     *
     * @param address 地址
     * @return
     */
    public static LocationDTO findCoordinates(String key, String address) {
        log.info("findAddr start key={},address={},", key, address);
        if (StringUtils.isEmpty(key)) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "缺少可用的key");
        }
        if (StringUtils.isEmpty(address)) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "地址不能为空");
        }
        HttpResponse httpResponse =
            HttpUtils.doGet(TencentConstant.HOST_MAP, TencentConstant.LONGITUDE_LATITUDE_FOR_ADDRESS, null,
                ImmutableMap.of("address", address, "key", key));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String string = JSON.parseObject(s).getString("result");
        LocationDTO result = JSON.parseObject(JSON.parseObject(string).getString("location"), LocationDTO.class);
        log.info("findAddr success result={},address={}", JSON.toJSONString(result), address);
        return result;
    }

    /**
     * 腾讯地图坐标转换
     * 实现从其它地图供应商坐标系或标准GPS坐标系，批量转换到腾讯地图坐标系
     *
     * @param key
     * @param type
     * 1 GPS坐标
     * 2 sogou经纬度
     * 3 baidu经纬度
     * 4 mapbar经纬度
     * 5 [默认]腾讯、google、高德坐标
     * 6 sogou墨卡托
     * @param key
     * @param type
     * @param addresses 逗号分割
     * <p>
     * 纬度在前,经度在后
     * <p/>
     * @return
     */
    public static List<LocationDTO> translate(String key, String type, List<String> addresses) {
        log.info("translate start key={},addresses={},type={}", key, addresses, type);
        if (StringUtils.isEmpty(key)) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "缺少可用的key");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (CollectionUtils.isEmpty(addresses)) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "地址不能为空");
        } else {
            for (String address : addresses) {
                stringBuilder = stringBuilder.append(address + ";");
            }
        }
        if (StringUtils.isEmpty(type)) {
            type = "5";
        }
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        HttpResponse httpResponse =
            HttpUtils.doGet(TencentConstant.HOST_MAP, TencentConstant.TRANSLATE, null,
                ImmutableMap.of("locations", substring, "key", key, "type", type));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        List<LocationDTO> location = JSON.parseArray(JSON.parseObject(s).getString("locations"), LocationDTO.class);
        log.info("translate success key={},addresses={},type={},locations={}", key, addresses, type,
            JSON.toJSONString(location));
        return location;
    }
}
