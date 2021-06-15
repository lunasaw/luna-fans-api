package com.luna.tencent.dto.map;

/**
 * @author luna
 * 2021/6/14
 */
public class KeyWordSearchResultDTO {

    /** 提示文字 */
    private String      title;
    /** 地址 */
    private String      address;
    /** 省 */
    private String      province;
    /** 市 */
    private String      city;
    /** 行政区域代码 */
    private String      adcode;
    /** 筛选条件 */
    private String      category;
    /** poi 类型 */
    private String      type;

    private LocationDTO locationDTO;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }
}
