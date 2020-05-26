package com.luna.common;

import com.luna.common.baidu.Config.GetBaiduKey;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class CommonApplicationTests {

    @Autowired
    GetBaiduKey getBaiduKey;

    @Test
    void contextLoads() {
        getBaiduKey.getAuth();
    }

}
