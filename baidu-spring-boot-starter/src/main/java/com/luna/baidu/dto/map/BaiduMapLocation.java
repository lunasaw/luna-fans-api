package com.luna.baidu.dto.map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.luna.common.file.FileTools;
import com.luna.common.io.IoUtil;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author weidian
 */
@Data
public class BaiduMapLocation {

    /**
     * 缓存一个Map 用于存储经纬度
     */
    public static final Map<String, BaiduMapLocation> LOCATION_MAP            = new HashMap<>();

    public static final String                        WEATHER_DISTRICT_ID_CSV = "map_data/weather_district_id.csv";

    public static BaiduMapLocation getByDistrict(String district) {
        if (MapUtils.isEmpty(LOCATION_MAP)) {
            // 读取CVS文件
            try {
                String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + WEATHER_DISTRICT_ID_CSV).getPath();
                BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));
                // 跳过标题行
                bufferedReader.readLine();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] split = line.split(",");
                    BaiduMapLocation baiduMapLocation = new BaiduMapLocation(split);
                    LOCATION_MAP.put(baiduMapLocation.getDistrict(), baiduMapLocation);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return LOCATION_MAP.get(district);
    }

    public BaiduMapLocation(String[] data) {
        this.areaCode = data[0];
        this.districtCode = data[1];
        this.cityGeoCode = data[2];
        this.city = data[3];
        this.districtGeoCode = data[4];
        this.district = data[5];
        this.longitude = Double.parseDouble(data[6]);
        this.latitude = Double.parseDouble(data[7]);
        this.stationFc = data[8];
        this.stationRt = data[9];
        this.province = data[10];
        this.fcLatitude = Double.parseDouble(data[11]);
        this.fcLongitude = Double.parseDouble(data[12]);
        this.rtLatitude = Double.parseDouble(data[13]);
        this.rtLongitude = Double.parseDouble(data[14]);
        this.originAreaCode = data[15];
        this.exclude = "1".equals(data[16]);
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