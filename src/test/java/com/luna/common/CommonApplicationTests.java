package com.luna.common;

import com.luna.common.baidu.BaiduFaceApi;
import com.luna.common.baidu.Config.GetBaiduKey;
import com.luna.common.baidu.PaintImage;
import com.luna.common.baidu.entity.Face;
import com.luna.common.config.TencentConfigValue;
import com.luna.common.ffmpeg.FfmpegConfigValue;
import com.luna.common.ffmpeg.FfmpegUtil;
import com.luna.common.tencent.TencentFaceApi;
import com.luna.common.utils.Base64Util;
import com.luna.common.utils.ImageUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class CommonApplicationTests {

    @Autowired
    FfmpegConfigValue ffmpegConfigValue;

    @Test
    void contextLoads() {
        System.out.println(ffmpegConfigValue.getPath());
    }

}
