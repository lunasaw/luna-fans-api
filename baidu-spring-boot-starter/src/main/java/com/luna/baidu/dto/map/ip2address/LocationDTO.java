package com.luna.baidu.dto.map.ip2address;

/**
 * @author luna@mac
 * @className LocationDTO.java
 * @description TODO
 * @createTime 2021年03月29日 19:51:00
 */
public class LocationDTO {

    /** 当前城市中心点经度 */
    private String x;

    /** 当前城市中心点纬度 */
    private String y;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
