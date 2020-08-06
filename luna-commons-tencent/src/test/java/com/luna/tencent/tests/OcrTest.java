package com.luna.tencent.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.luna.tencent.TencentApplicationTest;
import com.luna.tencent.config.TencentConfigValue;

/**
 * @Package: com.luna.baidu.tests
 * @ClassName: OcrTest
 * @Author: luna
 * @CreateTime: 2020/7/16 16:12
 * @Description:
 */
public class OcrTest extends TencentApplicationTest {

    @Autowired
    private TencentConfigValue tencentConfigValue;

    @Test
    public void atest() {
        System.out.println(tencentConfigValue.getSecretKey());
    }
}
