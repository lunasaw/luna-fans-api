package com.luna.tencent.map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.luna.tencent.TencentApplicationTest;
import com.luna.tencent.api.TencentMapApi;
import com.luna.tencent.dto.map.AddressResultDTO;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Package: com.luna.tencent.map
 * @ClassName: MapTest
 * @Author: luna
 * @CreateTime: 2020/8/15 11:30
 * @Description:
 */
public class MapTest extends TencentApplicationTest {

    @Test
    public void atest() {
        AddressResultDTO addr = TencentMapApi.findAddr("PX2BZ-5QB64-QTRUX-DU7Q5-AFCVE-YCBNE", "107.09864", "29.15411");
        System.out.println(JSON.toJSONString(addr));

        TencentMapApi.ip2Address("PX2BZ-5QB64-QTRUX-DU7Q5-AFCVE-YCBNE", "47.92.89.8");

        TencentMapApi.keyWordSearch("PX2BZ-5QB64-QTRUX-DU7Q5-AFCVE-YCBNE", "美食,大学", "重庆");

        TencentMapApi.findCoordinates("PX2BZ-5QB64-QTRUX-DU7Q5-AFCVE-YCBNE", "重庆市南川区南万高速公路");

        ArrayList<String> list = Lists.newArrayList("39.11428542,116.8234023", "30.21,115.43");
        TencentMapApi.translate("PX2BZ-5QB64-QTRUX-DU7Q5-AFCVE-YCBNE", "3", list);
    }
}
