package com.luna.baidu.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.luna.common.file.FileUtils;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.text.CharsetKit;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.baidu.dto.voice.VoiceCheckDTO;
import com.luna.baidu.dto.voice.VoiceSynthesisDTO;

/**
 * @Package: com.luna.baidu.api
 * @ClassName: BaiduVoiceApi
 * @Author: luna
 * @CreateTime: 2020/8/10 20:10
 * @Description:
 */
public class BaiduVoiceApi {
    private static final Logger log          = LoggerFactory.getLogger(BaiduVoiceApi.class);

    // 采样率固定值
    public static final int     RATE         = 16000;

    // 仅支持单声道，请填写固定值 1
    public static final int     CHANNEL      = 1;

    // 标准版默认ID
    public static final int     DEV_PID      = 1537;

    // 极速版限定ID
    public static final int     FAST_DEV_PID = 80001;

    /**
     * 语音识别标准版
     * 
     * @param token
     * @param voiceCheckDTO
     * @return
     */
    public static List<String> checkVoice(String token, VoiceCheckDTO voiceCheckDTO) {
        log.info("checkVoice start token={},voiceCheckDTO={}", token, JSON.toJSONString(voiceCheckDTO));
        Map<String, Object> body = Maps.newHashMap();
        body.put("dev_pid", voiceCheckDTO.getDev_pid());
        // body.put("lm_id", voiceCheckDTO.getLm_id());// 测试自训练平台需要打开注释
        body.put("format", voiceCheckDTO.getFormat());
        body.put("rate", voiceCheckDTO.getRate());
        body.put("token", token);
        body.put("cuid", voiceCheckDTO.getCuid());
        body.put("channel", voiceCheckDTO.getChannel());
        body.put("len", voiceCheckDTO.getLen());
        body.put("speech", voiceCheckDTO.getSpeech());
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.VOICE_HOST, BaiduApiConstant.VOICE_SPEECH,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, JSON.toJSONString(body));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        List<String> list = JSON.parseArray(JSON.parseObject(s).getString("result"), String.class);
        log.info("checkVoice success list={}", JSON.toJSONString(list));
        return list;
    }

    /**
     * 极速版限定dev_pid为 80001
     * 语音识别
     * 
     * @param token
     * @param voiceCheckDTO
     * @return
     */
    public static List<String> checkVoiceFast(String token, VoiceCheckDTO voiceCheckDTO) {
        log.info("checkVoice start token={},voiceCheckDTO={}", token, JSON.toJSONString(voiceCheckDTO));
        Map<String, Object> body = Maps.newHashMap();
        body.put("dev_pid", voiceCheckDTO.getDev_pid());
        // body.put("lm_id", voiceCheckDTO.getLm_id());// 测试自训练平台需要打开注释
        body.put("format", voiceCheckDTO.getFormat());
        body.put("rate", voiceCheckDTO.getRate());
        body.put("token", token);
        body.put("cuid", voiceCheckDTO.getCuid());
        body.put("channel", voiceCheckDTO.getChannel());
        body.put("len", voiceCheckDTO.getLen());
        body.put("speech", voiceCheckDTO.getSpeech());
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.VOICE_HOST, BaiduApiConstant.VOICE_SPEECH_FAST,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, JSON.toJSONString(body));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        List<String> list = JSON.parseArray(JSON.parseObject(s).getString("result"), String.class);
        log.info("checkVoiceFast success list={}", JSON.toJSONString(list));
        return list;
    }

    /**
     * 语音合成
     * 
     * @param token
     * @param voiceSynthesisDTO
     * @return
     * @throws IOException
     */
    public static String voiceSynthesis(String token, VoiceSynthesisDTO voiceSynthesisDTO) throws IOException {
        log.info("voiceSynthesis start token={},voiceSynthesisDTO={}", token, JSON.toJSONString(voiceSynthesisDTO));
        Map<String, String> map = Maps.newHashMap();
        map.put("tex", URLEncoder.encode(voiceSynthesisDTO.getTex(), CharsetKit.UTF_8));
        map.put("per", voiceSynthesisDTO.getPer());
        map.put("spd", voiceSynthesisDTO.getSpd());
        map.put("pit", voiceSynthesisDTO.getPit());
        map.put("vol", voiceSynthesisDTO.getVol());
        map.put("cuid", voiceSynthesisDTO.getCuid());
        map.put("tok", token);
        map.put("aue", voiceSynthesisDTO.getAue());
        map.put("lan", voiceSynthesisDTO.getLan());
        map.put("ctp", voiceSynthesisDTO.getCtp());
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiConstant.VOICE_SYNTHESIS, BaiduApiConstant.VOICE_SYNTHESIS_PATH,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), map, "");
        byte[] bytes = HttpUtils.checkResponseStreamAndGetResult(httpResponse);
        String path =
            voiceSynthesisDTO.getSavePath() + "\\" + voiceSynthesisDTO.getCuid() + "." + voiceSynthesisDTO.getAue();
        FileUtils.writeBytesToFile(bytes, path);
        log.info("voiceSynthesis success path={}", path);
        return path;
    }
}
