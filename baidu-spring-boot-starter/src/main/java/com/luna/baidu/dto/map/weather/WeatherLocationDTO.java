package com.luna.baidu.dto.map.weather;

/**
 * @author luna@mac
 * @className WeatherLocationDTO.java
 * @description TODO
 * @createTime 2021年03月29日 20:42:00
 */
public class WeatherLocationDTO {

    /**
     * 国家名称
     */
    private String country;
    /**
     * 省份名称
     */
    private String province;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 区县名称
     */
    private String name;
    /**
     * 区县id
     */
    private String id;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
