package com.luna.baidu.goodsTest;

import com.alibaba.fastjson.JSON;
import com.luna.baidu.BaiduApplicationTest;
import com.luna.baidu.dto.goods.GoodsInfoDTO;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.luna.baidu.api.BaiduGoodsIdentifyApi.goodsIdentify;

/**
 * @Package: com.luna.baidu.goodsTest
 * @ClassName: GoodTest
 * @Author: luna
 * @CreateTime: 2020/8/14 18:53
 * @Description:
 */
public class GoodTestIdentify extends BaiduApplicationTest {

    @Test
    public void atest() throws UnsupportedEncodingException {
        List<GoodsInfoDTO> goodsInfoDTOS =
            goodsIdentify("24.f4b0da25ae8e4925fc157a757d3035ff.2592000.1598949848.282335-19618961",
                "C:\\Users\\improve\\Pictures\\Camera Roll\\Pikachu.jpg", 3);
        System.out.println(JSON.toJSONString(goodsInfoDTOS));
    }
}
