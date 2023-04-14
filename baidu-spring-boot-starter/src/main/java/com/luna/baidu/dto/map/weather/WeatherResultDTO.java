package com.luna.baidu.dto.map.weather;

import lombok.Data;

import java.util.List;

/**
 * @author luna@mac
 * @className WeatherResultDTO.java
 * @description
 * @createTime 2021年03月29日 20:32:00
 */
@Data
public class WeatherResultDTO {

    private ResultDTO result;

    private Integer   status;

    @Data
    public static class ResultDTO {
        private List<WeatherForecastDTO> forecasts;

        private WeatherNowDTO            now;

        private WeatherLocationDTO       location;
    }

    @Data
    public static class WeatherNowDTO {

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
    }

    @Data
    public static class WeatherLocationDTO {

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
    }

    @Data
    public static class WeatherForecastDTO {

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
    }
}
