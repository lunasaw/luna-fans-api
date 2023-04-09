package io.github.lunasaw.baidu;

import com.luna.baidu.config.BaiduKeyGenerate;
import io.github.lunasaw.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author weidian
 * @description
 * @date 2023/4/9
 */
public class BaiduTest extends BaseTest {

    @Autowired
    private BaiduKeyGenerate baiduKeyGenerate;

    @Test
    public void test_get_key() {
        baiduKeyGenerate.getAuth();
    }

}
