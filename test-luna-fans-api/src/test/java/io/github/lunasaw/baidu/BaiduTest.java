package io.github.lunasaw.baidu;

import com.luna.baidu.api.BaiduVoiceApi;
import com.luna.baidu.config.BaiduKeyGenerate;
import com.luna.baidu.config.BaiduProperties;
import com.luna.common.file.FileTools;
import com.luna.common.os.SystemInfoUtil;
import io.github.lunasaw.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author weidian
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
        System.out.println(path);
        BaiduVoiceApi.voiceSynthesis("你好", accessToken, path + "你好.mp3");
    }

    @Test
    public void voice_2_txt() throws FileNotFoundException {
        String accessToken = baiduProperties.getBaiduKey();
        String path = ResourceUtils.getURL("").getPath();
        List<String> list = BaiduVoiceApi.voiceDetailApi(accessToken, FileTools.read(path + "16k.pcm"));
        System.out.println(list);
    }
}
