package com.luna.baidu.dto.map.ip2address;

/**
 * @author luna@mac
 * @className AddressContentDTO.java
 * @description TODO
 * @createTime 2021年03月29日 19:55:00
 */
public class AddressContentDTO {

    private AddressDetailDTO addressDetail;

    private LocationDTO      point;

    private String           address;

    public AddressDetailDTO getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(AddressDetailDTO addressDetail) {
        this.addressDetail = addressDetail;
    }

    public LocationDTO getPoint() {
        return point;
    }

    public void setPoint(LocationDTO point) {
        this.point = point;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
