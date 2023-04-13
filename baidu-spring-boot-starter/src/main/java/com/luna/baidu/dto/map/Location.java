package com.luna.baidu.dto.map;

import com.alibaba.fastjson.annotation.JSONField;
import com.luna.common.file.FileTools;
import lombok.Data;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weidian
 */
@Data
public class Location {

    // 缓存一个Map 用于存储经纬度
    private static Map<String, Location> LOCATION_MAP            = new HashMap<>();

    private static final String          WEATHER_DISTRICT_ID_CSV = "map_data/weather_district_id.csv";

    static {
        // 读取CVS文件
        try {
            String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + WEATHER_DISTRICT_ID_CSV).getPath();
            String cvsString = FileTools.readFileToString(path);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @JSONField(name = "areacode")
    private String  areaCode;

    @JSONField(name = "districtcode")
    private String  districtCode;

    @JSONField(name = "city_geocode")
    private String  cityGeoCode;

    @JSONField(name = "city")
    private String  city;

    @JSONField(name = "district_geocode")
    private String  districtGeoCode;

    @JSONField(name = "district")
    private String  district;

    @JSONField(name = "lon")
    private double  longitude;

    @JSONField(name = "lat")
    private double  latitude;

    @JSONField(name = "sta_fc")
    private String  stationFc;

    @JSONField(name = "sta_rt")
    private String  stationRt;

    @JSONField(name = "province")
    private String  province;

    @JSONField(name = "fc_lon")
    private double  fcLongitude;

    @JSONField(name = "fc_lat")
    private double  fcLatitude;

    @JSONField(name = "rt_lon")
    private double  rtLongitude;

    @JSONField(name = "rt_lat")
    private double  rtLatitude;

    @JSONField(name = "origin_areacode")
    private String  originAreaCode;

    @JSONField(name = "exclude")
    private boolean exclude;

    // constructor, getters, setters, toString method
}