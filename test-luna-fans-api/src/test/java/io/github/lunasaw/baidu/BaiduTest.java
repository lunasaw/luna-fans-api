package io.github.lunasaw.baidu;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.luna.baidu.api.BaiduVoiceApi;
import com.luna.baidu.config.BaiduProperties;
import com.luna.baidu.dto.voice.VoiceSynthesisDetailResponse;
import com.luna.baidu.req.voice.VoiceSynthesisResponse;
import com.luna.common.file.FileTools;
import com.luna.common.os.SystemInfoUtil;
import io.github.lunasaw.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
        BaiduVoiceApi.voiceSynthesis("你好，你好，我是你的智能机器人", accessToken, path + "你好.m4a");
        Assert.assertTrue(FileTools.isExists(path + "你好.m4a"));
    }

    @Test
    public void voice_2_txt() throws FileNotFoundException {
        String accessToken = baiduProperties.getBaiduKey();
        String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + "data/").getPath();
        List<String> list = BaiduVoiceApi.voiceDetailApi(accessToken,  path + "你好.m4a");
        String join = Joiner.on(",").join(list);
        System.out.println(join);
    }

    @Test
    public void txt_2_voive() {
        String accessToken = baiduProperties.getBaiduKey();
        VoiceSynthesisResponse hello = BaiduVoiceApi.voiceSynthesis(Lists.newArrayList("你好，你好，我是你的智能机器人"), accessToken);
        System.out.println(JSON.toJSONString(hello));
    }


    /**
     * {"log_id":16815359839745637,"tasks_info":[{"task_id":"6439843c3064530001cbb76b","task_result":{"speech_url":"http://bj.bcebos.com/aipe-speech/text_to_speech/2023-04-15/6439843c3064530001cbb76b/speech/0.mp3?authorization=bce-auth-v1%2F8a6ca9b78c124d89bb6bca18c6fc5944%2F2023-04-14T16%3A50%3A11Z%2F259200%2F%2Ff6229135e98f509140924fbabefa75ace3a186f91582ba943ee1b35e865eb3a8"},"task_status":"Success"}]}
     */
    @Test
    public void test_query() {
        VoiceSynthesisDetailResponse voiceSynthesisDetailResponse = BaiduVoiceApi.voiceSynthesisQuery(Lists.newArrayList("6439843c3064530001cbb76b", "643a35103064530001cbdebf"), baiduProperties.getBaiduKey());
        System.out.println(JSON.toJSONString(voiceSynthesisDetailResponse));
    }
}
