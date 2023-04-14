package io.github.lunasaw.baidu;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.luna.baidu.api.BaiduVoiceApi;
import com.luna.baidu.config.BaiduKeyGenerate;
import com.luna.baidu.config.BaiduProperties;
import com.luna.baidu.req.voice.VoiceSynthesisResponse;
import com.luna.common.file.FileTools;
import com.luna.common.os.SystemInfoUtil;
import io.github.lunasaw.BaseTest;
import io.github.lunasaw.FansApi;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author luna
 * @description
 * @date 2023/4/9
 */
public class BaiduTest extends BaseTest {


    @Autowired
    private BaiduProperties baiduProperties;

    @Test
    public void test_get_key() {
        System.out.println(baiduProperties.getBaiduKey());
    }

    @Test
    public void atest() {
        System.out.println(SystemInfoUtil.getMac());
    }

    @Test
    public void test_txt_2_voice() throws FileNotFoundException {
        String accessToken = baiduProperties.getBaiduKey();
        String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + "data/").getPath();
        BaiduVoiceApi.voiceSynthesis("你好", accessToken, path + "你好.m4a");
        Assert.assertTrue(FileTools.isExists(path + "你好.m4a"));
    }

    @Test
    public void voice_2_txt() throws FileNotFoundException {
        String accessToken = baiduProperties.getBaiduKey();
        String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + "data/").getPath();
        List<String> list = BaiduVoiceApi.voiceDetailApi(accessToken,  path + "你好.m4a");
        String join = Joiner.on(",").join(list);
        Assert.assertTrue(join.contains("你好"));
    }

    @Test
    public void txt_2_voive() {
        String accessToken = baiduProperties.getBaiduKey();
        VoiceSynthesisResponse hello = BaiduVoiceApi.voiceSynthesis(Lists.newArrayList("你好"), accessToken);
        System.out.println(JSON.toJSONString(hello));
    }
}
