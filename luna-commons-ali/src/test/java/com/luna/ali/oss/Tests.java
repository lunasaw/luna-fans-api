package com.luna.ali.oss;

import com.luna.ali.AliApplicationTest;
import com.luna.ali.config.AliConfigValue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package: com.luna.ali.oss
 * @ClassName: Tests
 * @Author: luna
 * @CreateTime: 2020/8/21 21:10
 * @Description:
 */
public class Tests extends AliApplicationTest {

    @Autowired
    private AliConfigValue aliConfigValue;

    @Test
    public void atest() throws Exception {
        // String s = AliOssUtil.uploadImg(new MockMultipartFile("luna.jpg",
        // ImageUtils.getBytes("C:\\Users\\improve\\Pictures\\Camera Roll\\logo.png")), "img", "luna97",
        // aliConfigValue);
        // System.out.println(s);

        String img = AliOssUploadApi.uploadByFilePath("C:\\Users\\improve\\Pictures\\Camera Roll\\logo.png",
            aliConfigValue.getBucketName(), "img", "", "", aliConfigValue);
        System.out.println(img);
    }

    @Test
    public void btest() {
        AliOssUtil.createOssWithCname(aliConfigValue);
    }
}
