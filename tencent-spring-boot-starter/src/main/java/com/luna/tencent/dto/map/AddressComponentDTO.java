package com.luna.tencent.dto.map;

/**
 * @Package: com.luna.tencent.dto.map
 * @ClassName: AddressComponentDTO
 * @Author: luna
 * @CreateTime: 2020/8/15 10:48
 * @Description:
 */
public class AddressComponentDTO {

    /**
     * 国家
     */
    private String nation;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区，可能为空字串
     */
    private String district;

    /**
     * 街道，可能为空字串
     */
    private String street;

    /**
     * 门牌，可能为空字串
     */
    private String streetNumber;

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
