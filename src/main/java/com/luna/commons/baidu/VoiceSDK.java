package com.luna.commons.baidu;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

import com.luna.commons.utils.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Luna@win10
 * @date 2020/5/3 14:32
 */
public class VoiceSDK {
    /**
     * 语音合成
     * 
     * @param client API配置
     * @param text 文字内容
     * @param man 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
     * @param speed 语速，取值0-9，默认为5中语速
     * @param pitch 音调，取值0-9，默认为5中语调
     * @return
     */
    public static JSONObject synthesis(AipSpeech client, String text, String man, String speed, String pitch,
        String ouput) {
        HashMap<String, Object> options = new HashMap<>();
        if (StringUtils.isNotEmpty(speed)) {
            options.put("spd", speed);
        }
        if (StringUtils.isNotEmpty(pitch)) {
            options.put("pit", pitch);
        }
        if (StringUtils.isNotEmpty(man)) {
            options.put("per", man);
        }
        TtsResponse res = client.synthesis(text, "zh", 1, options);
        JSONObject result = res.getResult();
        // 服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        if (result != null) {
            return result;
        }
        byte[] data = res.getData();
        // 生成的音频数据
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, ouput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 对本地语音文件进行识别
     * 
     * @param client
     * @param path
     */
    public static JSONObject asr(AipSpeech client, String path, String dev_pid, String format) throws JSONException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (dev_pid != null) {
            map.put("dev_pid", dev_pid);
        }
        if (format == null) {
            format = "pcm";
        }
        JSONObject asrRes = client.asr(path, format, 16000, map);
        return asrRes;
    }

    /**
     * 对语音二进制数据进行识别
     * 
     * @param client key配置
     * @param path 文件路径
     * @param dev_pid 语言
     * 1537 普通话 输入法模型 有标点 支持自定义词库
     * 1737 英语 无标点 不支持自定义词库
     * 1637 粤语 有标点 不支持自定义词库
     * 1837 四川话 有标点 不支持自定义词库
     * 1936 普通话远场 远场模型 有标点 不支持
     * @param format 文件格式 pcm 或者 wav 或者 amr。不区分大小写。推荐pcm文件
     * @throws IOException
     */
    public static JSONObject byte2Asr(AipSpeech client, String path, String dev_pid, String format) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (dev_pid != null) {
            map.put("dev_pid", dev_pid);
        }
        if (format == null) {
            format = "pcm";
        }
        byte[] data = Util.readFileByBytes(path);
        // readFileByBytes仅为获取二进制数据示例
        JSONObject asrRes2 = client.asr(data, format, 16000, map);
        return asrRes2;
    }

}
