package com.luna.baidu.dto.map.weather;

/**
 * @author luna@mac
 * @className WeatherForecastDTO.java
 * @description TODO
 * @createTime 2021年03月29日 20:44:00
 */
public class WeatherForecastDTO {

    /**
     * 白天天气现象
     */
    private String textDay;

    /**
     * 晚上天气现象
     */
    private String textNight;

    /**
     * 最高温度(℃)
     */
    private String high;

    /**
     * 最低温度(℃)
     */
    private String low;

    /**
     * 白天风力
     */
    private String wcDay;

    /**
     * 白天风向
     */
    private String wdDay;

    /**
     * 晚上风力
     */
    private String wcNight;

    /**
     * 晚上风向
     */
    private String wdNight;

    /**
     * 日期，北京时区
     */
    private String date;

    /**
     * 星期，北京时区
     */
    private String week;

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getWcDay() {
        return wcDay;
    }

    public void setWcDay(String wcDay) {
        this.wcDay = wcDay;
    }

    public String getWdDay() {
        return wdDay;
    }

    public void setWdDay(String wdDay) {
        this.wdDay = wdDay;
    }

    public String getWcNight() {
        return wcNight;
    }

    public void setWcNight(String wcNight) {
        this.wcNight = wcNight;
    }

    public String getWdNight() {
        return wdNight;
    }

    public void setWdNight(String wdNight) {
        this.wdNight = wdNight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
