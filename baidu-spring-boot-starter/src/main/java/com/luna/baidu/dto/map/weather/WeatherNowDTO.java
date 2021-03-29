package com.luna.baidu.dto.map.weather;

/**
 * @author luna@mac
 * @className WeatherNowDTO.java
 * @description TODO
 * @createTime 2021年03月29日 20:43:00
 */
public class WeatherNowDTO {

    /**
     * 天气现象
     */
    private String text;

    /**
     * 温度（℃）
     */
    private String temp;

    /**
     * 体感温度(℃)
     */
    private String feelsLike;

    /**
     * 相对湿度(%)
     */
    private String rh;

    /**
     * 风力等级
     */
    private String windClass;

    /**
     * 风向描述
     */
    private String windDir;

    /**
     * 数据更新时间，北京时间
     */
    private String uptime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getWindClass() {
        return windClass;
    }

    public void setWindClass(String windClass) {
        this.windClass = windClass;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }
}
