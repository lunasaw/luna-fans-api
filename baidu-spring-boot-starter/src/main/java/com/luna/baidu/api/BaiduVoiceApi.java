package com.luna.baidu.api;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.luna.baidu.config.BaiduApiConstant;
import com.luna.baidu.dto.voice.VoiceDetailResult;
import com.luna.baidu.dto.voice.VoiceSynthesisDetailResponse;
import com.luna.baidu.dto.voice.VoiceWriteResultDTO;
import com.luna.baidu.enums.voice.AudioFormat;
import com.luna.baidu.enums.voice.EnableSubtitle;
import com.luna.baidu.enums.voice.PersonVoice;
import com.luna.baidu.enums.voice.VideoFormat;
import com.luna.baidu.hander.ByteResponseHandler;
import com.luna.baidu.hander.StringResponseHandler;
import com.luna.baidu.req.voice.VoiceCheckReq;
import com.luna.baidu.req.voice.VoiceSynthesisReq;
import com.luna.baidu.req.voice.VoiceSynthesisResponse;
import com.luna.baidu.req.voice.VoiceSynthesisV2Req;
import com.luna.common.file.FileNameUtil;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpConnectionPoolUtil;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.os.SystemInfoUtil;
import com.luna.common.encrypt.Base64Util;
import com.luna.common.text.CharsetUtil;
import com.luna.common.utils.Assert;
import org.apache.commons.collections4.CollectionUtils;
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

    /**
     * 极速版限定ID
     */
    public static final int     FAST_DEV_PID = 80001;

    /**
     * 语音识别标准版
     *
     * @param voiceCheckReq
     * @return
     */
    public static VoiceDetailResult voiceDetailApi(VoiceCheckReq voiceCheckReq) {
        String response = HttpConnectionPoolUtil.doPost(BaiduApiConstant.VOICE_HOST, BaiduApiConstant.VOICE_SPEECH,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, JSON.toJSONString(voiceCheckReq),
            new StringResponseHandler());

        System.out.println(response);
        return JSON.parseObject(response, VoiceDetailResult.class);
    }

    /**
     * 语音识别标准版
     *
     * @param token token
     * @param read 语音文件
     * @return
     */
    public static List<String> voiceDetailApi(String token, String format, Integer lmId, byte[] read) {
        VoiceCheckReq voiceCheckReq =
            new VoiceCheckReq(token, SystemInfoUtil.getRandomMac(), lmId, Base64Util.encodeBase64(read), read.length);
        voiceCheckReq.setFormat(format);
        VoiceDetailResult voiceDetailResult = voiceDetailApi(voiceCheckReq);
        List<String> result = voiceDetailResult.getResult();
        if (CollectionUtils.isEmpty(result)) {
            log.error("语音识别失败,请检查错误码 voiceDetailApi::lmId = {}, voiceDetailResult = {}", lmId, voiceDetailResult);
        }
        return result;
    }

    public static List<String> voiceDetailApi(String token, String path) {
        String name = FileNameUtil.getSuffix(path);
        AudioFormat audioFormat = AudioFormat.fromName(name);
        Assert.notNull(audioFormat, "格式不支持");
        return voiceDetailApi(token, audioFormat.getName(), null, path);
    }

    public static List<String> voiceDetailApi(String token, String format, String path) {
        return voiceDetailApi(token, format, null, path);
    }

    /**
     * 语音识别标准版
     * 
     * @param token token
     * @param lmId 语言模型id
     * @param path 语音文件路径
     * @return
     */
    public static List<String> voiceDetailApi(String token, String format, Integer lmId, String path) {
        byte[] read = FileTools.read(path);

        return voiceDetailApi(token, format, lmId, read);
    }

    /**
     * 极速版限定dev_pid为 80001
     * 语音识别
     *
     * @param token token
     * @param read 语音文件
     * @return
     */
    public static List<String> voiceProApi(String token, byte[] read) {
        VoiceCheckReq voiceCheckReq =
            new VoiceCheckReq(token, SystemInfoUtil.getRandomMac(), null, Base64Util.encodeBase64(read), read.length);
        return voiceProApi(voiceCheckReq).getResult();
    }

    public static List<String> voiceProApi(String token, Integer lmId, String path) {
        byte[] read = FileTools.read(path);
        VoiceCheckReq voiceCheckReq =
            new VoiceCheckReq(token, SystemInfoUtil.getRandomMac(), lmId, Base64Util.encodeBase64(read), read.length);
        return voiceProApi(voiceCheckReq).getResult();
    }

    /**
     * 极速版限定dev_pid为 80001
     * 语音识别
     * 
     * @param voiceCheckReq
     * @return
     */
    public static VoiceDetailResult voiceProApi(VoiceCheckReq voiceCheckReq) {
        voiceCheckReq.setDevPid(FAST_DEV_PID);
        HttpResponse httpResponse = HttpUtils.doPost(BaiduApiConstant.VOICE_HOST, BaiduApiConstant.VOICE_SPEECH_FAST,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, JSON.toJSONString(voiceCheckReq));
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        return JSON.parseObject(s, VoiceDetailResult.class);
    }

    /**
     * 语音合成
     * 
     * @param tex 合成文字
     * @param accessToken token
     * @param savePath 保存目录
     */
    public static void voiceSynthesis(String tex, String accessToken, String savePath) {
        VoiceSynthesisReq voiceSynthesisReq = new VoiceSynthesisReq(SystemInfoUtil.getRandomMac(), tex, accessToken);
        FileTools.write(voiceSynthesis(voiceSynthesisReq), savePath);
    }

    /**
     * 语音合成
     *
     * @param voiceSynthesisReq 合成配置
     * @param savePath 保存目录
     */
    public static void voiceSynthesis(VoiceSynthesisReq voiceSynthesisReq, String savePath) {
        FileTools.write(voiceSynthesis(voiceSynthesisReq), savePath);
    }

    /**
     * 语音合成
     * 
     * @param voiceSynthesisReq
     * @return
     * @throws IOException
     */
    public static byte[] voiceSynthesis(VoiceSynthesisReq voiceSynthesisReq) {
        log.info("voiceSynthesis start voiceSynthesisReq={}", JSON.toJSONString(voiceSynthesisReq));
        Map<String, String> map = Maps.newHashMap();
        map.put("tex", HttpUtils.urlEncode(voiceSynthesisReq.getTex(), CharsetUtil.UTF_8));
        map.put("per", voiceSynthesisReq.getPer());
        map.put("spd", voiceSynthesisReq.getSpd());
        map.put("pit", voiceSynthesisReq.getPit());
        map.put("vol", voiceSynthesisReq.getVol());
        map.put("cuid", voiceSynthesisReq.getCuid());
        map.put("tok", voiceSynthesisReq.getAccessToken());
        map.put("aue", voiceSynthesisReq.getAue());
        map.put("lan", voiceSynthesisReq.getLan());
        map.put("ctp", voiceSynthesisReq.getCtp());
        return HttpConnectionPoolUtil.doPost(BaiduApiConstant.VOICE_SYNTHESIS, BaiduApiConstant.VOICE_SYNTHESIS_PATH,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), null, HttpUtils.urlEncode(map), new ByteResponseHandler());
    }

    /**
     * 语音合成
     * 
     * @param text 合成文字
     */
    public static VoiceSynthesisResponse voiceSynthesis(List<String> text, String token) {
        return voiceSynthesis(text, VideoFormat.MP3_16K.getName(), PersonVoice.DU_XIAO_MEI.getCode(), token);
    }

    public static VoiceSynthesisResponse voiceSynthesis(List<String> text, VideoFormat videoFormat, PersonVoice personVoice, String token) {
        return voiceSynthesis(text, videoFormat.getName(), personVoice.getCode(), token);
    }

    public static VoiceSynthesisResponse voiceSynthesis(List<String> text, String format, int voice, String token) {
        return voiceSynthesis(text, format, voice, Locale.SIMPLIFIED_CHINESE.getLanguage(), 5, 5, 5, token);
    }

    public static VoiceSynthesisResponse voiceSynthesis(List<String> text, String format, int voice, String lang, int speed, int pitch, int volume,
        String token) {
        return voiceSynthesis(text, format, voice, lang, speed, pitch, volume, EnableSubtitle.NO_SUBTITLE.getCode(), token);
    }

    /**
     * 长文本语音合成
     * @param text 待合成的文本，需要为UTF-8 编码；输入多段文本时，文本间会插入1s长度的空白间隔。总字数不超过10万个字符，1个中文字、英文字母、数字或符号均算作1个字符
     * @param format 音频格式。"mp3-16k"，"mp3-48k"，"wav"，"pcm-8k"，"pcm-16k"，默认为mp3-16k
     * @param voice
     * 音库。基础音库：度小宇=1，度小美=0，度逍遥（基础）=3，度丫丫=4；精品音库：度逍遥（精品）=5003，度小鹿=5118，度博文=106，度小童=110，度小萌=111，度米朵=103，度小娇=5。默认为度小美
     * @param lang 语言。固定值zh。语言选择,目前只有中英文混合模式，填写固定值zh
     * @param speed 语速。取值0-15，默认为5中语速
     * @param pitch 音调。取值0-15，默认为5中语调
     * @param volume 音量。取值0-15，默认为5中音量（取值为0时为音量最小值，并非为无声）
     * @param enableSubtitle 是否开启字幕。取值范围0, 1, 2，默认为0。0表示不开启字幕，1表示开启句级别字幕，2表示开启词级别字幕。
     * @param token
     */
    public static VoiceSynthesisResponse voiceSynthesis(List<String> text, String format, int voice, String lang, int speed, int pitch, int volume,
        int enableSubtitle, String token) {
        VoiceSynthesisV2Req voiceSynthesisV2Req = new VoiceSynthesisV2Req(text, format, voice, lang, speed, pitch, volume, enableSubtitle);
        String s = HttpConnectionPoolUtil.doPost(BaiduApiConstant.HOST, BaiduApiConstant.VOICE_SYNTHESIS_PATH_V2,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), ImmutableMap.of("access_token", token), JSON.toJSONString(voiceSynthesisV2Req),
            new StringResponseHandler());
        return JSON.parseObject(s, VoiceSynthesisResponse.class);
    }


    /**
     * 语音合成查询
     * @param taskIds 推荐一次查询多个任务id，单次最多可查询200个
     * @param token
     * @return
     */
    public static VoiceSynthesisDetailResponse voiceSynthesisQuery(List<String> taskIds, String token){
        String s = HttpConnectionPoolUtil.doPost(BaiduApiConstant.HOST, BaiduApiConstant.VOICE_SYNTHESIS_V2_QUERY,
            ImmutableMap.of("Content-Type", HttpUtilsConstant.JSON), ImmutableMap.of("access_token", token), JSON.toJSONString(ImmutableMap.of("task_ids", taskIds)),
            new StringResponseHandler());
        return JSON.parseObject(s, VoiceSynthesisDetailResponse.class);
    }

    /**
     * 创建音频转写任务
     *
     * @param token
     * @param speechUrl 可使用百度云对象存储进行音频存储，生成云端可外网访问的url链接，音频大小不超过500MB
     * @param format ["mp3", "wav", "pcm","m4a","amr"]单声道，编码 16bits 位深
     * pid [ 1737（英文模型） ]
     * rare [16000] 固定值
     */
    public static String voiceCreateEn(String token, String speechUrl, String format) {
        return voiceCreate(token, speechUrl, format, 1737);
    }

    /**
     * 创建音频转写任务
     *
     * @param token
     * @param speechUrl 可使用百度云对象存储进行音频存储，生成云端可外网访问的url链接，音频大小不超过500MB
     * @param format ["mp3", "wav", "pcm","m4a","amr"]单声道，编码 16bits 位深
     * pid [80001（中文普通话输入法模型）]
     * rare [16000] 固定值
     */
    public static String voiceCreateZh(String token, String speechUrl, String format) {
        return voiceCreate(token, speechUrl, format, 80001);
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
