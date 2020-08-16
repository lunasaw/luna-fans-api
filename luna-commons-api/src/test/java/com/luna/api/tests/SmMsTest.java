package com.luna.api.tests;

import com.luna.api.ApiApplicationTest;
import com.luna.api.smms.api.ImageApiFromString;
import com.luna.api.smms.config.SmMsConfigValue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

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

    @Test
    public void btest() throws IOException {
        // File file = ResourceUtils.getFile("classpath:conf.json");
        // JSONObject jsonObject1 = ImageApiFromRoot.uploadFromRoot(file.getAbsolutePath(),
        // "C:\\Users\\improve\\Pictures\\Camera Roll\\Pikachu.jpg");
        // System.out.println(jsonObject1);
        String upload = ImageApiFromString.upload("NFfqBMvYH6RfXaqEgjo79oDfQ7Ckrchg",
            "C:\\Users\\improve\\Pictures\\Camera Roll\\luna-logo.png");
        System.out.println(upload);
    }
}
