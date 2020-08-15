package com.luna.tencent.dto.map;

/**
 * @Package: com.luna.tencent.dto.map
 * @ClassName: AddressResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/15 10:53
 * @Description:
 */
public class AddressResultDTO {

    /**
     * 地址描述
     */
    private String              address;

    /**
     * 地址部件，address不满足需求时可自行拼接
     */
    private AddressComponentDTO addressComponent;

    /** 经纬度 */
    private LocationDTO         location;

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AddressComponentDTO getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponentDTO addressComponent) {
        this.addressComponent = addressComponent;
    }
}
