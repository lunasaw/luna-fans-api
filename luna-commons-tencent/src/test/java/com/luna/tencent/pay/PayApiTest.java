package com.luna.tencent.pay;

import com.alibaba.fastjson.JSON;
import com.luna.common.utils.text.AmountUtil;
import com.luna.common.utils.text.ConvertUtil;
import com.luna.tencent.TencentApplicationTest;
import com.luna.tencent.config.TencentPayConfigValue;
import com.luna.tencent.pay.api.TencentPayApi;
import com.luna.tencent.pay.entity.TencentPayEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Package: com.luna.tencent.pay
 * @ClassName: PayApiTest
 * @Author: luna
 * @CreateTime: 2020/8/16 14:40
 * @Description:
 */
public class PayApiTest extends TencentApplicationTest {

    @Autowired
    private TencentPayConfigValue tencentPayConfigValue;

    @Test
    public void atest() throws Exception {
        System.out.println(tencentPayConfigValue.getNotifyurl());

        TencentPayEntity tencentPayEntity = new TencentPayEntity("测试", "123712837197",
            AmountUtil.changeY2F("0.01"), "127.0.0.1");
        String aNative = TencentPayApi.createNative(tencentPayConfigValue, tencentPayEntity);
        System.out.println(aNative);
        // QueryResultDTO queryResultDTO = TencentPayApi.queryStatus(tencentPayConfigValue, "123712837192");

    }

    @Test
    public void btest() throws Exception {
        Map<String, String> map = ConvertUtil.xmlToMap("" +
            "<xml><appid><![CDATA[wx8397f8696b538317]]></appid>;" +
            "<bank_type><![CDATA[OTHERS]]></bank_type>;" +
            "<cash_fee><![CDATA[1]]></cash_fee>;" +
            "<fee_type><![CDATA[CNY]]></fee_type>;" +
            "<is_subscribe><![CDATA[N]]></is_subscribe>;" +
            "<mch_id><![CDATA[1473426802]]></mch_id>;" +
            "<nonce_str><![CDATA[n2s60L1opJvnWheodn4fC1l6s928lebS]]></nonce_str>;" +
            "<openid><![CDATA[oNpSGwTqaYHpXMQ17e1Es0DCgGMs]]></openid>;" +
            "<out_trade_no><![CDATA[123712837196]]></out_trade_no>;" +
            "<result_code><![CDATA[SUCCESS]]></result_code>;" +
            "<return_code><![CDATA[SUCCESS]]></return_code>;" +
            "<sign><![CDATA[FFFCA72A1DC803CB071EB9988F31704F]]></sign>;" +
            "<time_end><![CDATA[20200816164626]]></time_end>;" +
            "<total_fee>1</total_fee>;" +
            "<trade_type><![CDATA[NATIVE]]></trade_type>;" +
            "<transaction_id><![CDATA[4200000717202008160162828803]]></transaction_id>;" +
            "</xml>");

        System.out.println(JSON.toJSONString(map));
    }
}
