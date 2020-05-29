package com.luna.commons;

import com.luna.commons.ffmpeg.FfmpegConfigValue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
