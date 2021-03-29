package com.luna.baidu.dto.map.weather;

import java.util.List;

/**
 * @author luna@mac
 * @className WeatherResultDTO.java
 * @description TODO
 * @createTime 2021年03月29日 20:32:00
 */
public class WeatherResultDTO {

    private List<WeatherForecastDTO> forecasts;

    private WeatherNowDTO            now;

    private WeatherLocationDTO       location;

    public List<WeatherForecastDTO> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<WeatherForecastDTO> forecasts) {
        this.forecasts = forecasts;
    }

    public WeatherNowDTO getNow() {
        return now;
    }

    public void setNow(WeatherNowDTO now) {
        this.now = now;
    }

    public WeatherLocationDTO getLocation() {
        return location;
    }

    public void setLocation(WeatherLocationDTO location) {
        this.location = location;
    }
}
