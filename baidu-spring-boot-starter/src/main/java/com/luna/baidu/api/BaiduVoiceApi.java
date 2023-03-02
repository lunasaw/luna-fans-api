package com.luna.baidu.api;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.luna.baidu.dto.voice.VoiceWriteResultDTO;
import com.luna.baidu.req.VoiceCheckReq;
import com.luna.baidu.req.VoiceSynthesisReq;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.os.SystemInfoUtil;
import com.luna.common.encrypt.Base64Util;
import com.luna.common.text.CharsetUtil;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * @Package: com.luna.baidu.api
 * @ClassName: BaiduVoiceApi
 * @Author: luna
 * @CreateTime: 2020/8/10 20:10
 * @Description:
 */
public class BaiduVoiceApi {
    private static final Logger log          = LoggerFactory.getLogger(BaiduVoiceApi.class);

    // 极速版限定ID
    public static final int     FAST_DEV_PID = 80001;

    /**
     * 语音识别标准版
     *
     * @param voiceCheckReq
     * @return
     */
    public static List<String> checkVoice(VoiceCheckReq voiceCheckReq) {
        log.info("checkVoice start voiceCheckReq={}", JSON.toJSONString(voiceCheckReq));
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.VOICE_HOST, BaiduApiConstant.VOICE_SPEECH,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, JSON.toJSONString(voiceCheckReq));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        List<String> list = JSON.parseArray(JSON.parseObject(s).getString("result"), String.class);
        log.info("checkVoice success list={}", JSON.toJSONString(list));
        return list;
    }

    public static List<String> checkVoice(String token, Integer lmId, String path) {
        byte[] read = FileTools.read(path);
        VoiceCheckReq voiceCheckReq =
            new VoiceCheckReq(token, SystemInfoUtil.getMac(), lmId, Base64Util.encodeBase64(read), read.length);
        return checkVoice(voiceCheckReq);
    }

    public static List<String> checkVoiceFast(String token, Integer lmId, String path) {
        byte[] read = FileTools.read(path);
        VoiceCheckReq voiceCheckReq =
            new VoiceCheckReq(token, SystemInfoUtil.getMac(), lmId, Base64Util.encodeBase64(read), read.length);
        return checkVoiceFast(voiceCheckReq);
    }

    /**
     * 极速版限定dev_pid为 80001
     * 语音识别
     * 
     * @param voiceCheckReq
     * @return
     */
    public static List<String> checkVoiceFast(VoiceCheckReq voiceCheckReq) {
        voiceCheckReq.setDevPid(FAST_DEV_PID);
        log.info("checkVoice start, voiceCheckReq={}", JSON.toJSONString(voiceCheckReq));
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.VOICE_HOST, BaiduApiConstant.VOICE_SPEECH_FAST,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, JSON.toJSONString(voiceCheckReq));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        List<String> list = JSON.parseArray(JSON.parseObject(s).getString("result"), String.class);
        log.info("checkVoiceFast success list={}", JSON.toJSONString(list));
        return list;
    }

    /**
     * 语音合成
     * 
     * @param tex 合成文字
     * @param tok token
     * @param savePath 保存目录
     */
    public static void voiceSynthesis(String tex, String tok, String savePath) {
        try {
            VoiceSynthesisReq voiceSynthesisReq = new VoiceSynthesisReq(SystemInfoUtil.getMac(), tex, tok);
            FileTools.write(voiceSynthesis(voiceSynthesisReq), savePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 语音合成
     *
     * @param voiceSynthesisReq 合成配置
     * @param savePath 保存目录
     */
    public static void voiceSynthesis(VoiceSynthesisReq voiceSynthesisReq, String savePath) {
        try {
            FileTools.write(voiceSynthesis(voiceSynthesisReq), savePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 语音合成
     * 
     * @param voiceSynthesisReq
     * @return
     * @throws IOException
     */
    public static byte[] voiceSynthesis(VoiceSynthesisReq voiceSynthesisReq) throws IOException {
        log.info("voiceSynthesis start voiceSynthesisReq={}", JSON.toJSONString(voiceSynthesisReq));
        Map<String, String> map = Maps.newHashMap();
        map.put("tex", URLEncoder.encode(voiceSynthesisReq.getTex(), CharsetUtil.UTF_8));
        map.put("per", voiceSynthesisReq.getPer());
        map.put("spd", voiceSynthesisReq.getSpd());
        map.put("pit", voiceSynthesisReq.getPit());
        map.put("vol", voiceSynthesisReq.getVol());
        map.put("cuid", voiceSynthesisReq.getCuid());
        map.put("tok", voiceSynthesisReq.getTok());
        map.put("aue", voiceSynthesisReq.getAue());
        map.put("lan", voiceSynthesisReq.getLan());
        map.put("ctp", voiceSynthesisReq.getCtp());
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiConstant.VOICE_SYNTHESIS, BaiduApiConstant.VOICE_SYNTHESIS_PATH,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, HttpUtils.urlEncode(map));
        return HttpUtils.checkResponseStreamAndGetResult(httpResponse);
    }

    /**
     * 创建音频转写任务
     * 
     * @param token
     * @param speechUrl 可使用百度云对象存储进行音频存储，生成云端可外网访问的url链接，音频大小不超过500MB
     * @param format ["mp3", "wav", "pcm","m4a","amr"]单声道，编码 16bits 位深
     * @param pid [80001（中文普通话输入法模型）, 1737（英文模型）]
     * rare [16000] 固定值
     */
    public static String voiceCreate(String token, String speechUrl, String format, Integer pid) {
        ImmutableMap<String, Object> map =
            ImmutableMap.of("speech_url", speechUrl, "format", format, "pid", pid, "rate", 16000);
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.VOICE_TO_WRITE,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), ImmutableMap.of("access_token", token),
                JSON.toJSONString(map));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        return JSON.parseObject(s).getString("task_id");
    }

    /**
     * 检查任务完成情况
     * 
     * @param token
     * @param taskIds task_ids为空，返回空任务结果列表；单次查询任务数不超过200个
     */
    public static VoiceWriteResultDTO voiceQuery(String token, List<String> taskIds) {
        HttpResponse httpResponse =
            HttpUtils.doPost(BaiduApiConstant.HOST, BaiduApiConstant.VOICE_TO_QUERY,
                ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), ImmutableMap.of("access_token", token),
                JSON.toJSONString(ImmutableMap.of("task_ids", taskIds)));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        System.out.println(s);
        VoiceWriteResultDTO resultDTO =
            JSON.parseObject(JSON.parseObject(s).getString("tasks_info"), VoiceWriteResultDTO.class);
        log.info("voiceQuery end resultDTO={}", JSON.toJSONString(resultDTO));
        return resultDTO;
    }
}
