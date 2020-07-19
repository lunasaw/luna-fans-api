package com.luna.baidu.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.luna.baidu.BaiduApplicationTest;
import com.luna.baidu.api.BaiduApiContent;
import com.luna.baidu.api.BaiduFaceApi;
import com.luna.baidu.config.BaiduConfigValue;
import com.luna.baidu.config.GetBaiduKey;
import com.luna.common.utils.Base64Util;
import com.luna.common.utils.img.ImageUtils;

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
        System.out.println(baiduConfigValue.getAppKey());
        BaiduApiContent.BAIDU_KEY="25.2634aa914e737196878361a42128d998.315360000.1910247307.282335-19618961";
        boolean b = BaiduFaceApi.checkLive(Base64Util.encodeBase64String(ImageUtils.getBytes("C:\\Users\\improve\\Pictures\\Saved Pictures\\girl.png")));
    }
}
