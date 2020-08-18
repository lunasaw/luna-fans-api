package com.luna.ali.tests;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.luna.ali.AliApplicationTest;
import com.luna.ali.api.AlipayApi;
import com.luna.ali.config.AliConfigValue;
import com.luna.ali.config.AlipayConfigValue;
import com.luna.ali.dto.AlipayOrderDTO;
import com.luna.ali.dto.QueryBillDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package: com.luna.ali.tests
 * @ClassName: AliPayTest
 * @Author: luna
 * @CreateTime: 2020/7/16 16:24
 * @Description:
 */
public class AliPayTest extends AliApplicationTest {

    @Autowired
    private AliConfigValue    aliConfigValue;

    @Autowired
    private AlipayConfigValue alipayConfigValue;

    @Test
    public void atest() throws AlipayApiException {
        AlipayOrderDTO alipayOrderDTO = new AlipayOrderDTO();
        alipayOrderDTO.setBody("测试");
        alipayOrderDTO.setOutTradeNo("1231234218786");
        alipayOrderDTO.setSubject("测试");
        alipayOrderDTO.setTotalAmount("0.10");

        String trade = AlipayApi.payDownloadQuery(alipayConfigValue, new QueryBillDTO("trade", "2020-08-17"));
        System.out.println(JSON.toJSONString(trade));
    }
}
