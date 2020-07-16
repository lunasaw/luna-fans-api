package com.luna.ali.tests;

import com.luna.ali.AliApplicationTest;
import com.luna.ali.config.AliConfigValue;
import com.luna.ali.config.AlipayConfigValue;
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
    public void atest() {
        System.out.println(aliConfigValue.getOssKey());
        System.out.println(alipayConfigValue.getAppId());
    }
}
