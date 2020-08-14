package com.luna.baidu.voice;

import com.luna.baidu.BaiduApplicationTest;
import com.luna.baidu.api.BaiduVoiceApi;
import com.luna.baidu.dto.voice.VoiceCheckDTO;
import com.luna.baidu.dto.voice.VoiceSynthesisDTO;
import com.luna.common.utils.Base64Util;
import com.luna.common.utils.file.LocalFileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static com.luna.baidu.api.BaiduVoiceApi.CHANNEL;
import static com.luna.baidu.api.BaiduVoiceApi.voiceSynthesis;

/**
 * @Package: com.luna.baidu
 * @ClassName: VoiceTest
 * @Author: luna
 * @CreateTime: 2020/8/14 16:49
 * @Description:
 */
public class VoiceTest extends BaiduApplicationTest {

    @Test
    public void atest() throws IOException {
        VoiceCheckDTO voiceCheckDTO = new VoiceCheckDTO();
        voiceCheckDTO.setCuid(UUID.randomUUID().toString());
        voiceCheckDTO.setRate(BaiduVoiceApi.RATE);
        voiceCheckDTO.setFormat("PCM");
        byte[] bytes = LocalFileUtil
            .readFileByBytes(
                "D:\\myproject\\luna-commons-loc\\luna-commons-baidu\\src\\main\\resources\\static\\16k.pcm");
        voiceCheckDTO.setSpeech(Base64Util.encodeBase64(bytes));
        voiceCheckDTO.setLen(bytes.length);
        voiceCheckDTO.setChannel(CHANNEL);
        voiceCheckDTO.setDev_pid(BaiduVoiceApi.DEV_PID);
        // List<String> list =
        // checkVoice("24.f4b0da25ae8e4925fc157a757d3035ff.2592000.1598949848.282335-19618961", voiceCheckDTO);
        // System.out.println(JSON.toJSONString(list));
        // List<String> list = checkVoiceFast("24.f4b0da25ae8e4925fc157a757d3035ff.2592000.1598949848.282335-19618961",
        // voiceCheckDTO);
        // System.out.println(JSON.toJSONString(list));

        VoiceSynthesisDTO voiceSynthesisDTO = new VoiceSynthesisDTO();
        voiceSynthesisDTO.setPer("103");
        voiceSynthesisDTO.setTex("傻逼");
        voiceSynthesisDTO.setCuid(UUID.randomUUID().toString());
        voiceSynthesisDTO.setAue("mp3");
        voiceSynthesisDTO.setSavePath("D:\\luna");
        voiceSynthesis("24.f4b0da25ae8e4925fc157a757d3035ff.2592000.1598949848.282335-19618961", voiceSynthesisDTO);
    }
}
