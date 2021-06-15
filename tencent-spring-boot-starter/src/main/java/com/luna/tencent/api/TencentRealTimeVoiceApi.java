package com.luna.tencent.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.text.Base64Util;
import com.luna.common.text.ByteUtils;
import com.luna.common.text.MapUtils;
import com.luna.common.text.RandomStrUtil;
import com.luna.tencent.response.voice.SpeechRecognitionResponse;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luna
 * 2021/6/14
 */
public class TencentRealTimeVoiceApi {

    private static final Logger log = LoggerFactory.getLogger(TencentRealTimeVoiceApi.class);

    /**
     * 实时语音识别
     * 
     * @param appId
     * @param secretid
     * @param key
     * @param projectId 腾讯云项目 ID，语音识别目前不区分项目，所以填0即可。
     * @param subServiceType 子服务类型。1：实时流式识别。
     * @param engineModelType 引擎模型类型。
     * 电话场景：
     * • 8k_en：电话 8k 英语；
     * • 8k_zh：电话 8k 中文普通话通用；
     * • 8k_zh_finance：电话 8k 金融领域模型；
     * 非电话场景：
     * • 16k_zh：16k 中文普通话通用；
     * • 16k_en：16k 英语；
     * • 16k_ca：16k 粤语；
     * • 16k_ko：16k 韩语；
     * • 16k_zh-TW：16k 中文普通话繁体；
     * • 16k_ja：16k 日语；
     * • 16k_wuu-SH：16k 上海话方言；
     * • 16k_zh_medical 医疗；
     * • 16k_en_game 英文游戏；
     * • 16k_zh_court 法庭；
     * • 16k_en_edu 英文教育；
     * • 16k_zh_edu 中文教育；
     * • 16k_th 泰语。
     * @param hotwordId 热词 id。用于调用对应的热词表，如果在调用语音识别服务时，不进行单独的热词 id 设置，自动生效默认热词；如果进行了单独的热词 id 设置，那么将生效单独设置的热词 id。
     * @param customizationId 自学习模型 id。用于调用对应的自学习模型，如果在调用语音识别服务时，不进行单独的自学习模型 id 设置，自动生效默认自学习模型；如果进行了单独的自学习模型 id
     * 设置，那么将生效单独设置的自学习模型 id。
     * @param resultTextFormat 识别结果文本编码方式。0：UTF-8。
     * @param resType 结果返回方式，默认值0。0：同步返回；1：尾包返回。
     * @param voiceFormat 语音编码方式，可选，默认值为4。1：pcm；4：speex(sp)；6：silk；8：mp3；10：opus（opus 格式音频流封装说明
     * https://cloud.tencent.com/document/product/1093/35799#jump）；12：wav；14：m4a（每个分片须是一个完整的 m4a 音频）；16：aac。
     * @param needvad 0：关闭 vad，1：开启 vad。
     * 如果音频流总时长超过60秒，用户需开启 vad，默认值0。
     * @param vadSilenceTime 语音断句检测阈值，静音时长超过该阈值会被认为断句（多用在智能客服场景，需配合 needvad = 1 使用），取值范围：240-2000，单位
     * ms，此参数建议不要随意调整，可能会影响识别效果，目前仅支持 8k_zh、8k_zh_finance、16k_zh 引擎模型。
     * @param seq 语音分片的序号，序号从0开始，每次请求递增1， 两个 seq 之间间隔不能超过6秒。
     * @param end 是否为最后一片，最后一片语音片为1，其余为0。
     * @param source 默认值为0。
     * @param voiceId 16位 String 串作为每个音频的唯一标识，用户自己生成。
     * @param timestamp 当前 UNIX 时间戳，可记录发起 API 请求的时间。如果与当前时间相差过大，会引起签名过期错误。可以取值为当前请求的系统时间戳即可。
     * @param expired 签名的有效期，是一个符合 UNIX Epoch 时间戳规范的数值，单位为秒；Expired 必须大于 Timestamp 且 Expired - Timestamp 小于90天。
     * @param nonce 随机正整数。用户需自行生成，最长10位。
     * @param filterDirty 是否过滤脏词（目前支持中文普通话引擎）。默认为0。0：不过滤脏词；1：过滤脏词；2：将脏词替换为 * 。
     * @param filterModal 是否过滤语气词（目前支持中文普通话引擎）。默认为0。0：不过滤语气词；1：部分过滤；2：严格过滤 。
     * @param filterPunc 是否过滤标点符号（目前支持中文普通话引擎）。0：不过滤，1：过滤句末标点，2：过滤所有标点。默认为0。
     * @param convertNumMode 是否进行阿拉伯数字智能转换（目前支持中文普通话引擎）。0：不转换，直接输出中文数字，1：根据场景智能转换为阿拉伯数字，3: 打开数学相关数字转换。默认值为1。
     * @param wordInfo 是否显示词级别时间戳。0：不显示；1：显示，不包含标点时间戳，2：显示，包含标点时间戳。支持引擎
     * 8k_en，8k_zh，8k_zh_finance，16k_zh，16k_en，16k_ca，16k_zh-TW，16k_ja，16k_wuu-SH，默认为0。
     * @param bytes 发送的数据文件
     * @return
     */
    public static List<SpeechRecognitionResponse> realTimeSpeechRecognition(String appId, String secretid, String key,
        Integer projectId, Integer subServiceType,
        String engineModelType, String hotwordId, String customizationId,
        Integer resultTextFormat, Integer resType, Integer voiceFormat, Integer needvad, Integer vadSilenceTime,
        Integer seq,
        Integer end, Integer source, String voiceId, Long timestamp,
        Long expired, Long nonce, Integer filterDirty, Integer filterModal, Integer filterPunc,
        Integer convertNumMode,
        Integer wordInfo, byte[] bytes) {
        TreeMap<String, Object> map = Maps.newTreeMap();

        MapUtils.putIfNull(map, "projectid", projectId);
        MapUtils.putIfNull(map, "secretid", secretid);
        MapUtils.putIfNull(map, "sub_service_type", subServiceType);
        MapUtils.putIfNull(map, "engine_model_type", engineModelType);
        MapUtils.putIfNull(map, "hotword_id", hotwordId);
        MapUtils.putIfNull(map, "customization_id", customizationId);
        MapUtils.putIfNull(map, "result_text_format", resultTextFormat);
        MapUtils.putIfNull(map, "res_type", resType);
        MapUtils.putIfNull(map, "voice_format", voiceFormat);
        MapUtils.putIfNull(map, "needvad", needvad);
        MapUtils.putIfNull(map, "vad_silence_time", vadSilenceTime);

        MapUtils.putIfNull(map, "source", source);
        MapUtils.putIfNull(map, "voice_id", voiceId);
        MapUtils.putIfNull(map, "timestamp", timestamp);
        MapUtils.putIfNull(map, "expired", expired);
        MapUtils.putIfNull(map, "nonce", nonce);
        MapUtils.putIfNull(map, "filter_dirty", filterDirty);
        MapUtils.putIfNull(map, "filter_modal", filterModal);
        MapUtils.putIfNull(map, "filter_punc", filterPunc);
        MapUtils.putIfNull(map, "convert_num_mode", convertNumMode);
        MapUtils.putIfNull(map, "word_info", wordInfo);

        try {
            // http 建议每次传输200ms数据 websocket建议每次传输40ms数据
            List<byte[]> speechData = ByteUtils.subToSmallBytes(bytes, 0, 6400);
            AtomicInteger size = new AtomicInteger(speechData.size());
            ArrayList<SpeechRecognitionResponse> list = Lists.newArrayList();
            speechData.stream().forEach(bytesTemp -> {
                int tempSeq = seq;
                int tempEnd = end;
                if (size.get() == 0) {
                    tempEnd = 1;
                }
                MapUtils.putIfNull(map, "seq", tempSeq++);
                MapUtils.putIfNull(map, "end", tempEnd);
                String url =
                    HttpUtils.buildUrlObject("http://" + TencentConstant.VOICE_FAST_IDENTIFY, "/asr/v1/" + appId, map);
                String post = url.replace("http://", "POST");
                String authorization = Base64Util.encodeBase64(TencentCloudAPITC3.hmac1(post, key));
                log.info("voiceIdentifyOneMinutes start secretid={}, key={}, url={}", secretid,
                    key, url);
                HttpResponse httpResponse =
                    HttpUtils.doPost(url, "",
                        ImmutableMap.of("Host", TencentConstant.VOICE_FAST_IDENTIFY, "Content-Type",
                            HttpUtilsConstant.MEDIA,
                            "Authorization", authorization),
                        null, bytesTemp);
                String response = HttpUtils.checkResponseAndGetResult(httpResponse, true);
                SpeechRecognitionResponse speechRecognitionResponse =
                    JSON.parseObject(response, SpeechRecognitionResponse.class);
                log.info("voiceIdentifyOneMinutes start secretid={}, key={}, speechRecognitionResponse={}", secretid,
                    key, JSON.toJSONString(speechRecognitionResponse));
                size.getAndDecrement();
            });
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<SpeechRecognitionResponse> realTimeSpeechRecognitionWithFile(String appId, String secretid,
        String key,
        Integer projectId, Integer subServiceType,
        String engineModelType, String hotwordId, String customizationId,
        Integer resultTextFormat, Integer resType, Integer voiceFormat, Integer needvad, Integer vadSilenceTime,
        Integer seq,
        Integer end, Integer source, String voiceId, Long timestamp,
        Long expired, Long nonce, Integer filterDirty, Integer filterModal, Integer filterPunc,
        Integer convertNumMode,
        Integer wordInfo, String fileName) {
        // 案例使用文件模拟实时获取语音流，用户使用可直接调用write传入字节数据
        return realTimeSpeechRecognition(appId, secretid, key,
            projectId, subServiceType,
            engineModelType, hotwordId, customizationId,
            resultTextFormat, resType, voiceFormat, needvad, vadSilenceTime,
            seq,
            end, source, voiceId, timestamp,
            expired, nonce, filterDirty, filterModal, filterPunc,
            convertNumMode,
            wordInfo, FileTools.read(fileName));
    }

    public static List<SpeechRecognitionResponse> realTimeSpeechRecognitionWithFile(String appId, String secretid,
        String key,
        String engineModelType, String hotwordId, String customizationId,
        Integer voiceFormat, Integer needvad, Integer vadSilenceTime,
        Integer seq,
        Integer end, String voiceId, Long timestamp,
        Long expired, Long nonce, Integer filterDirty, Integer filterModal, Integer filterPunc,
        Integer convertNumMode,
        Integer wordInfo, String fileName) {
        return realTimeSpeechRecognitionWithFile(appId, secretid, key,
            0, 1,
            engineModelType, hotwordId, customizationId,
            0, 0, voiceFormat, needvad, vadSilenceTime,
            seq, end, 0, voiceId, timestamp,
            expired, nonce, filterDirty, filterModal, filterPunc,
            convertNumMode,
            wordInfo, fileName);
    }

    public static List<SpeechRecognitionResponse> realTimeSpeechRecognition(String appId, String secretid, String key,
        String engineModelType, String hotwordId, String customizationId,
        Integer voiceFormat, Integer needvad, Integer vadSilenceTime,
        Integer seq,
        Integer end, String voiceId, Long timestamp,
        Long expired, Long nonce, Integer filterDirty, Integer filterModal, Integer filterPunc,
        Integer convertNumMode,
        Integer wordInfo, byte[] bytes) {
        return realTimeSpeechRecognition(appId, secretid, key,
            0, 1,
            engineModelType, hotwordId, customizationId,
            0, 0, voiceFormat, needvad, vadSilenceTime,
            seq, end, 0, voiceId, timestamp,
            expired, nonce, filterDirty, filterModal, filterPunc,
            convertNumMode,
            wordInfo, bytes);
    }

    public static List<SpeechRecognitionResponse> realTimeSpeechRecognitionWithFile16kZh(String appId, String secretid,
        String key,
        Integer voiceFormat, String fileName) {
        return realTimeSpeechRecognitionWithFile(appId, secretid, key,
            "16k_zh", null, null, voiceFormat, null, null,
            0, 0, RandomStrUtil.generateNonceStr(), System.currentTimeMillis() / 1000L,
            System.currentTimeMillis() / 1000L + 86400L, System.currentTimeMillis() / 1000L, null, null, null,
            null,
            null, fileName);
    }

    public static List<SpeechRecognitionResponse> realTimeSpeechRecognition16kZh(String appId, String secretid,
        String key,
        Integer voiceFormat, byte[] bytes) {
        return realTimeSpeechRecognition(appId, secretid, key,
            "16k_zh", null, null, voiceFormat, null, null,
            0, 0, RandomStrUtil.generateNonceStr(), System.currentTimeMillis() / 1000L,
            System.currentTimeMillis() / 1000L + 86400L, System.currentTimeMillis() / 1000L, null, null, null,
            null,
            null, bytes);
    }
}
