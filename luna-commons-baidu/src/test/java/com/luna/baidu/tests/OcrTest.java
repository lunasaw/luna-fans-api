package com.luna.baidu.tests;

import com.luna.baidu.BaiduApplicationTest;
import com.luna.baidu.config.BaiduConfigValue;
import com.luna.baidu.config.GetBaiduKey;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package: com.luna.baidu.tests
 * @ClassName: OcrTest
 * @Author: luna
 * @CreateTime: 2020/7/16 16:12
 * @Description:
 */
public class OcrTest extends BaiduApplicationTest {

    @Autowired
    private BaiduConfigValue baiduConfigValue;

    @Autowired
    private GetBaiduKey      getBaiduKey;

    @Test
    public void atest() {
        System.out.println(baiduConfigValue.getBaiduKey());
    }
}
