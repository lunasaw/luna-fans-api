package com.luna.baidu.dto.map.ip2address;

/**
 * @author luna@mac
 * @className AddressDetailDTO.java
 * @description TODO
 * @createTime 2021年03月29日 19:49:00
 */
public class AddressDetailDTO {

    /**
     * 城市
     */
    private String city;

    /**
     * 百度城市代码
     */
    private String cityCode;

    /**
     * 省份
     */
    private String province;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
