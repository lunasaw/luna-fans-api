package com.luna.tencent.dto.map;

/**
 * @Package: com.luna.tencent.dto.map
 * @ClassName: LocationDTO
 * @Author: luna
 * @CreateTime: 2020/8/15 11:03
 * @Description:
 */
public class LocationDTO {

    /** 纬度 */
    private Double lat;

    /** 经度 */
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
