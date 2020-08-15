package com.luna.tencent.dto.map;

/**
 * @Package: com.luna.tencent.dto.map
 * @ClassName: Ip2AddressResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/15 11:02
 * @Description:
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
