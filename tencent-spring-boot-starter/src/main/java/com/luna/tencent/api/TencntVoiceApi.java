package com.luna.tencent.api;

import ch.qos.logback.core.util.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.common.file.FileNameUtils;
import com.luna.common.file.FileTools;
import com.luna.common.net.HttpUtils;
import com.luna.common.net.HttpUtilsConstant;
import com.luna.common.net.base.HttpBaseUtils;
import com.luna.common.text.Base64Util;
import com.luna.common.text.MapUtils;
import com.luna.tencent.dto.group.CompareFaceResultDTO;
import com.sun.crypto.provider.HmacSHA1;
import com.tencent.asr.model.FlashRecognitionResponse;
import com.tencentcloudapi.asr.v20190614.models.DescribeAsyncRecognitionTasksResponse;
import com.tencentcloudapi.asr.v20190614.models.TaskStatus;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author luna
 * 2021/6/13
 */
public class TencntVoiceApi {

    private static final Logger log = LoggerFactory.getLogger(TencntVoiceApi.class);

    /**
     * 录音文件识别请求
     * 本接口服务对时长5小时以内的录音文件进行识别，异步返回识别全部结果。
     * • 支持中文普通话、英语、粤语、日语、泰语
     * • 支持通用、音视频领域
     * • 支持wav、mp3、m4a、flv、mp4、wma、3gp、amr、aac、ogg-opus、flac格式
     * • 支持语音 URL 和本地语音文件两种请求方式
     * • 语音 URL 的音频时长不能长于5小时，文件大小不超过512MB
     * • 本地语音文件不能大于5MB
     * • 提交录音文件识别请求后，在5小时内完成识别（半小时内发送超过1000小时录音或者2万条识别任务的除外），识别结果在服务端可保存7天
     * 
     * @param id
     * @param key
     * @param engineModelType 引擎模型类型。
     * 电话场景：
     * • 8k_en：电话 8k 英语；
     * • 8k_zh：电话 8k 中文普通话通用；
     * 非电话场景：
     * • 16k_zh：16k 中文普通话通用；
     * • 16k_zh_video：16k 音视频领域；
     * • 16k_en：16k 英语；
     * • 16k_ca：16k 粤语；
     * • 16k_ja：16k 日语；
     * • 16k_zh_edu 中文教育；
     * • 16k_en_edu 英文教育；
     * • 16k_zh_medical 医疗；
     * • 16k_th 泰语；
     * @param channelNum 识别声道数。1：单声道；2：双声道（仅支持 8k_zh 引擎模）。注意：录音识别会自动将音频转码为填写的识别声道数
     * @param resTextFormat 识别结果返回形式。0： 识别结果文本(含分段时间戳)； 1：词级别粒度的详细识别结果(不含标点，含语速值)；2：词级别粒度的详细识别结果（包含标点、语速值）
     * @param sourceType 语音数据来源。0：语音 URL；1：语音数据（post body）。
     * @param speakerDiarization 是否开启说话人分离，0：不开启，1：开启(仅支持8k_zh，16k_zh，16k_zh_video引擎模型，单声道音频)，默认值为 0。
     * @param speakerNumber 说话人分离人数（需配合开启说话人分离使用），取值范围：0-10，0代表自动分离（目前仅支持≤6个人），1-10代表指定说话人数分离。默认值为 0。
     * 注：话者分离目前是beta版本，请根据您的需要谨慎使用
     * @param callbackUrl 回调 URL，用户自行搭建的用于接收识别结果的服务URL。如果用户使用轮询方式获取识别结果，则无需提交该参数。回调格式&内容详见：录音识别回调说明
     * https://cloud.tencent.com/document/product/1093/52632
     * @param url 语音的URL地址，需要公网可下载。长度小于2048字节，当 SourceType 值为 0 时须填写该字段，为 1
     * 时不需要填写。注意：请确保录音文件时长在5个小时之内，否则可能识别失败。请保证文件的下载速度，否则可能下载失败。
     * @param data 语音数据，当SourceType
     * 值为1时必须填写，为0可不写。要base64编码(采用python语言时注意读取文件应该为string而不是byte，以byte格式读取后要decode()。编码后的数据不可带有回车换行符)。音频数据要小于5MB。
     * @param dataLen 数据长度，非必填（此数据长度为数据未进行base64编码时的数据长度）。
     * @param hotwordId 热词id。用于调用对应的热词表，如果在调用语音识别服务时，不进行单独的热词id设置，自动生效默认热词；如果进行了单独的热词id设置，那么将生效单独设置的热词id。
     * @param filterDirty 是否过滤脏词（目前支持中文普通话引擎）。0：不过滤脏词；1：过滤脏词；2：将脏词替换为 * 。默认值为 0。
     * @param filterModal 是否过滤语气词（目前支持中文普通话引擎）。0：不过滤语气词；1：部分过滤；2：严格过滤 。默认值为 0。
     * @param convertNumMode 是否进行阿拉伯数字智能转换（目前支持中文普通话引擎）。0：不转换，直接输出中文数字，1：根据场景智能转换为阿拉伯数字，3: 打开数学相关数字转换。默认值为 1。
     * @param filterPunc 是否过滤标点符号（目前支持中文普通话引擎）。 0：不过滤，1：过滤句末标点，2：过滤所有标点。默认值为 0。
     */
    public static DescribeAsyncRecognitionTasksResponse voiceIdentify(String id, String key, String engineModelType,
        Integer channelNum,
        Integer resTextFormat, Integer sourceType, Integer speakerDiarization, Integer speakerNumber,
        String callbackUrl,
        String url, String data,
        Integer dataLen, Integer hotwordId, Integer filterDirty, Integer filterModal, Integer convertNumMode,
        Integer filterPunc) {

        HashMap<String, Object> map = Maps.newHashMap();

        MapUtils.putIfNull(map, "EngineModelType", engineModelType);
        MapUtils.putIfNull(map, "EngineModelType", engineModelType);
        MapUtils.putIfNull(map, "ChannelNum", channelNum);
        MapUtils.putIfNull(map, "ResTextFormat", resTextFormat);
        MapUtils.putIfNull(map, "SourceType", sourceType);
        MapUtils.putIfNull(map, "SpeakerDiarization", speakerDiarization);
        MapUtils.putIfNull(map, "SpeakerNumber", speakerNumber);
        MapUtils.putIfNull(map, "CallbackUrl", callbackUrl);
        MapUtils.putIfNull(map, "Url", url);
        MapUtils.putIfNull(map, "Data", data);
        MapUtils.putIfNull(map, "DataLen", dataLen);
        MapUtils.putIfNull(map, "HotwordId", hotwordId);
        MapUtils.putIfNull(map, "FilterDirty", filterDirty);
        MapUtils.putIfNull(map, "FilterModal", filterModal);
        MapUtils.putIfNull(map, "ConvertNumMode", convertNumMode);
        MapUtils.putIfNull(map, "FilterPunc", filterPunc);

        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "asr",
                TencentConstant.VOICE_IDENTIFY, "", "CreateRecTask", "2019-06-14", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(s).getString("Response");
        DescribeAsyncRecognitionTasksResponse describeAsyncRecognitionTasksResponse =
            JSON.parseObject(response, DescribeAsyncRecognitionTasksResponse.class);
        log.info("idNameCheck start id={}, key={}, resultDTO={}", id, key,
            JSON.toJSONString(describeAsyncRecognitionTasksResponse));
        return describeAsyncRecognitionTasksResponse;
    }

    public static void voiceIdentifyWithFile(String id, String key, String engineModelType, Integer channelNum,
        Integer resTextFormat, Integer speakerDiarization, Integer speakerNumber, String callbackUrl,
        String data,
        Integer dataLen, Integer hotwordId, Integer filterDirty, Integer filterModal, Integer convertNumMode,
        Integer filterPunc) {
        voiceIdentify(id, key, engineModelType, channelNum,
            resTextFormat, 1, speakerDiarization, speakerNumber, callbackUrl,
            null, data, dataLen, hotwordId, filterDirty, filterModal, convertNumMode, filterPunc);
    }

    public static void voiceIdentifyWithFile(String id, String key, String engineModelType, String data,
        Integer dataLen) {
        voiceIdentifyWithFile(id, key, engineModelType, 1,
            0, 0, 0, null,
            data, dataLen, null, 0, 0, 1, 0);
    }

    public static void voiceIdentifyWithFile16kZh(String id, String key, String data,
        Integer dataLen) {
        voiceIdentifyWithFile(id, key, "16k_zh", data, dataLen);
    }

    public static void voiceIdentifyWithFile16kZh(String id, String key, String fileName) {
        byte[] read = FileTools.read(fileName);
        voiceIdentifyWithFile(id, key, "16k_zh", Base64Util.encodeBase64(read), read.length);
    }

    public static void voiceIdentifyWithUrl(String id, String key, String engineModelType, Integer channelNum,
        Integer resTextFormat, Integer speakerDiarization, Integer speakerNumber, String callbackUrl,
        String url,
        Integer dataLen, Integer hotwordId, Integer filterDirty, Integer filterModal, Integer convertNumMode,
        Integer filterPunc) {
        voiceIdentify(id, key, engineModelType, channelNum,
            resTextFormat, 0, speakerDiarization, speakerNumber, callbackUrl,
            url, null, dataLen, hotwordId, filterDirty, filterModal, convertNumMode, filterPunc);
    }

    public static void voiceIdentifyWithUrl(String id, String key, String engineModelType, String url) {
        voiceIdentifyWithFile(id, key, engineModelType, 1,
            0, 0, 0, null,
            url, null, null, 0, 0, 1, 0);
    }

    public static void voiceIdentifyWithUrl16kZh(String id, String key, String url) {
        voiceIdentifyWithUrl(id, key, "16k_zh", url);
    }

    /**
     * 录音文件识别结果查询
     * 在调用录音文件识别请求接口后，有回调和轮询两种方式获取识别结果。
     * 
     * @param id
     * @param key
     * @param taskId
     * @return
     */
    public static TaskStatus getVoiceResult(String id, String key, Integer taskId) {
        ImmutableMap<String, Object> map = ImmutableMap.of("TaskId", taskId);
        String body = JSONArray.toJSONString(map);
        Map postHeader =
            TencentCloudAPITC3.getPostHeader(id, key, "asr",
                TencentConstant.VOICE_IDENTIFY, "", "DescribeTaskStatus", "2019-06-14", body);
        HttpResponse httpResponse =
            HttpUtils.doPost("https://" + TencentConstant.FACE_CHECK, "/", postHeader, null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        String response = JSON.parseObject(JSON.parseObject(s).getString("Response")).getString("Data");
        TaskStatus taskStatus =
            JSON.parseObject(response, TaskStatus.class);
        log.info("idNameCheck start id={}, key={}, taskStatus={}", id, key, JSON.toJSONString(taskStatus));
        return taskStatus;
    }

    public static FlashRecognitionResponse voiceFastIdentify(String appId, String secretid, String key,
        String engineType,
        Long timestamp, Integer speakerDiarization, Integer filterDirty, Integer filterModal, Integer filterPunc,
        Integer convertNumMode, Integer wordInfo, Integer firstChannelOnly, String fileName) {
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        return voiceFastIdentify(appId, secretid, key, engineType, type,
            timestamp, speakerDiarization, filterDirty, filterModal, filterPunc,
            convertNumMode, wordInfo, firstChannelOnly, fileName);
    }

    public static FlashRecognitionResponse voiceFastIdentifyWith16kZh(String appId, String secretid, String key,
        String fileName) {
        return voiceFastIdentify(appId, secretid, key, "16k_zh",
            System.currentTimeMillis() / 1000, 0, 0, 0, 0,
            1, 0, 1, fileName);
    }

    public static FlashRecognitionResponse voiceFastIdentify(String appId, String secretid, String key,
        String engineType,
        String voiceFormat,
        Long timestamp, Integer speakerDiarization, Integer filterDirty, Integer filterModal, Integer filterPunc,
        Integer convertNumMode, Integer wordInfo, Integer firstChannelOnly, String fileName) {

        TreeMap<String, Object> map = Maps.newTreeMap();
        MapUtils.putIfNull(map, "secretid", secretid);
        MapUtils.putIfNull(map, "engine_type", engineType);
        MapUtils.putIfNull(map, "voice_format", voiceFormat);
        MapUtils.putIfNull(map, "timestamp", timestamp);
        MapUtils.putIfNull(map, "speaker_diarization", speakerDiarization);
        MapUtils.putIfNull(map, "filter_dirty", filterDirty);
        MapUtils.putIfNull(map, "filter_modal", filterModal);
        MapUtils.putIfNull(map, "filter_punc", filterPunc);
        MapUtils.putIfNull(map, "convert_num_mode", convertNumMode);
        MapUtils.putIfNull(map, "word_info", wordInfo);
        MapUtils.putIfNull(map, "first_channel_only", firstChannelOnly);

        String url = HttpUtils.buildUrlObject("https://asr.cloud.tencent.com", "/asr/flash/v1/" + appId, map);
        String post = url.replace("https://", "POST");
        try {

            // POSTasr.cloud.tencent.com/asr/flash/v1/1300707296?convert_num_mode=1&engine_type=16k_zh&filter_dirty=0&filter_modal=0&filter_punc=0&first_channel_only=1&secretid=AKIDQiohOwHROE1pZAYheOzShrtspiVDpLoD&speaker_diarization=0&timestamp=1623598188&voice_format=mp3&word_info=1
            // POSTasr.cloud.tencent.com/asr/flash/v1/1300707296?convert_num_mode=1&engine_type=16k_zh&filter_dirty=0&filter_modal=0&filter_punc=0&first_channel_only=1&secretid=AKIDQiohOwHROE1pZAYheOzShrtspiVDpLoD&speaker_diarization=0&timestamp=1623598215&voice_format=mp3&word_info=0
            // Base64Encode(HmacSha1("POSTasr.cloud.tencent.com/asr/flash/v1/1259228442?convert_num_mode=1&engine_type=16k_zh&filter_dirty=0&filter_modal=0&filter_punc=0&first_channel_only=1&hotword_id=&secretid=AKIDoQq1zhZMN8dv0psmvud6OUKuGPO7pu0r&speaker_diarization=0&timestamp=1609560089&voice_format=wav&word_info=0",
            // "kFpwoX5RYQ2SkqpeHgqmSzHK7h3A2fni"))
            String authorization = Base64Util.encodeBase64(TencentCloudAPITC3.hmac1(post, key));
            System.out.println(authorization);
            HttpResponse httpResponse =
                HttpUtils.doPost(url, "",
                    ImmutableMap.of("Host", "asr.cloud.tencent.com", "Content-Type", HttpUtilsConstant.MEDIA,
                        "Authorization", authorization),
                    null,
                    FileTools.read(fileName));
            String response = HttpUtils.checkResponseAndGetResult(httpResponse, true);
            FlashRecognitionResponse flashRecognitionResponse =
                JSON.parseObject(response, FlashRecognitionResponse.class);
            log.info("idNameCheck start secretid={}, key={}, taskStatus={}", secretid, key,
                JSON.toJSONString(flashRecognitionResponse));
            return flashRecognitionResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // byte[] read =
        // FileTools.read("/Users/luna/Document/project/post/2021/05/31/36911f9480a344ba8e85900fbcff3897.mp3");
        // voiceIdentifyWithFile16kZh("AKIDV1h7G7trkM5ctwbIZeuue8KhpgEVxFsN", "NVM2RYP6WtzsVt1tUosyrBFeokQKCsOy",
        // Base64Util.encodeBase64(read), read.length);

        // getVoiceResult("AKIDV1h7G7trkM5ctwbIZeuue8KhpgEVxFsN", "NVM2RYP6WtzsVt1tUosyrBFeokQKCsOy", 1279503318);

        voiceFastIdentifyWith16kZh("1300707296", "AKIDQiohOwHROE1pZAYheOzShrtspiVDpLoD",
            "Rn5JUentyzxvnTwDHAqIZgdPzRioiV7Y",
            "/Users/luna/Document/project/post/2021/05/31/36911f9480a344ba8e85900fbcff3897.mp3");

    }
}
