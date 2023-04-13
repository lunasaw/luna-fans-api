package io.github.lunasaw.baidu.map;

import com.alibaba.fastjson.JSON;
import com.luna.baidu.api.BaiduAddressApi;
import com.luna.baidu.config.BaiduProperties;
import com.luna.baidu.dto.map.weather.WeatherResultDTO;
import com.luna.baidu.enums.map.CoordinateType;
import com.luna.baidu.enums.map.DataTypeEnum;
import io.github.lunasaw.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author weidian
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
}
