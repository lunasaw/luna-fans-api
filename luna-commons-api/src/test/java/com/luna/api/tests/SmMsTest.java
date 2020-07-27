package com.luna.api.tests;

import org.junit.Test;

import com.luna.api.ApiApplicationTest;
import com.luna.api.smMs.config.SmMsConfigValue;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package: com.luna.api.tests
 * @ClassName: SmMsTest
 * @Author: luna
 * @CreateTime: 2020/7/27 16:27
 * @Description:
 */
public class SmMsTest extends ApiApplicationTest {

    @Autowired
    private SmMsConfigValue smMsConfigValue;

    @Test
    public void atest() {
        System.out.println(smMsConfigValue.getUsername());
    }
}
