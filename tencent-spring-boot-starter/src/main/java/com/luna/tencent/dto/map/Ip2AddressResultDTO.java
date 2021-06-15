package com.luna.tencent.dto.map;

/**
 * @author luna
 * 2021/6/14
 */
public class Ip2AddressResultDTO {

    /** 定位行政区划信息 */
    private AddressComponentDTO adInfo;

    /** 经纬度 */
    private LocationDTO         location;

    public AddressComponentDTO getAdInfo() {
        return adInfo;
    }

    public void setAdInfo(AddressComponentDTO adInfo) {
        this.adInfo = adInfo;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}
