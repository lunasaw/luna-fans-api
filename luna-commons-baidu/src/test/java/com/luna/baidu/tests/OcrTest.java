package com.luna.baidu.tests;

import com.luna.baidu.BaiduApplicationTest;
import com.luna.baidu.config.BaiduConfigValue;
import com.luna.baidu.config.GetBaiduKey;
import com.luna.common.utils.Base64Util;
import com.luna.common.utils.img.ImageUtils;
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
        // List<Word> words = BaiduOcrApi.baiduOcrAndAddressNormal(baiduConfigValue.getBaiduKey(),
        // Base64Util.encodeBase64String(ImageUtils.getBytes("C:\\Users\\improve\\Pictures\\Saved
        // Pictures\\lunaqr_code.png")));
        // System.out.println(words);
        String s = Base64Util.encodeBase64String(ImageUtils.getBytes("D:\\user-improve\\conf\\net\\luna.jpg"));
        String s2 = Base64Util.encodeBase64String(ImageUtils.getBytes("D:\\user-improve\\conf\\net\\zheng.jpg"));

        // Double aDouble = BaiduFaceApi.faceMathch(baiduConfigValue.getBaiduKey(), s, s2);
        // System.out.println(aDouble);

        // boolean b = BaiduFaceApi.checkLive(baiduConfigValue.getBaiduKey(), s);
        // System.out.println(b);
        // List list = BodySDK.sample(baiduConfigValue.getClient(), "C:\\Users\\improve\\Pictures\\Saved
        // Pictures\\friends2.jpg");
        String s3 = Base64Util
            .encodeBase64String(ImageUtils.getBytes("C:\\Users\\improve\\Pictures\\Saved Pictures\\friends.jpg"));

        // List<Face> faces = BaiduFaceApi.faceDetect(baiduConfigValue.getBaiduKey(), s3);
        //
        // boolean b =
        // PaintImageUtils.paintFace("C:\\Users\\improve\\Pictures\\Saved Pictures\\friends.jpg", faces, "a.jpg");
        // System.out.println(b);
    }
}
