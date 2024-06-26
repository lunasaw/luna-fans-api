package io.github.lunasaw.baidu.map;

import com.alibaba.fastjson2.JSON;
import com.luna.baidu.api.BaiduAddressApi;
import com.luna.baidu.config.BaiduProperties;
import com.luna.baidu.dto.map.BaiduMapLocation;
import com.luna.baidu.dto.map.weather.WeatherResultDTO;
import com.luna.baidu.enums.map.CoordinateType;
import com.luna.baidu.enums.map.DataTypeEnum;
import io.github.lunasaw.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.tools.DocumentationTool;

/**
 * @author luna
 * @description
 * @date 2023/4/13
 */
public class BaiduMapTest extends BaseTest {

    @Autowired
    private BaiduProperties baiduProperties;

    @Test
    public void atest() {
        WeatherResultDTO weatherResultDTO =
            BaiduAddressApi.district2Weather(baiduProperties.getMapAk(), "330100", DataTypeEnum.NOW, CoordinateType.BD09MC);
        System.out.println(JSON.toJSONString(weatherResultDTO));
    }

    @Test
    public void city_test() {
        WeatherResultDTO weatherResultDTO = BaiduAddressApi.district2Weather(baiduProperties.getMapAk(), "杭州");
        System.out.println(weatherResultDTO);
    }
}
