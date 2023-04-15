package com.luna.baidu.req.voice;

import lombok.Data;

/**
 * @author luna
 */
@Data
public class VoiceSynthesisReq {

    /**
     * 发音人选择, 基础音库：0为度小美，1为度小宇，3为度逍遥，4为度丫丫，
     * 精品音库：5为度小娇，103为度米朵，106为度博文，110为度小童，111为度小萌，默认为度小美
     */
    private String per = "0";

    /** 语速，取值0-15，默认为5中语速 */
    private String spd = "5";

    /** 音调，取值0-15，默认为5中语调 */
    private String pit = "5";

    /** 音量，取值0-9，默认为5中音量 */
    private String vol = "5";

    /** 机器标识 */
    private String cuid;

    /** 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav */
    private String aue = "mp3";

    /** 语言 zh */
    private String lan = "Zh";

    /** 1 */
    private String ctp = "1";

    /** 合成文字 urlEncode,utf-8 编码 */
    private String tex;

    private String accessToken;


    public VoiceSynthesisReq(String mac, String tex, String accessToken) {
        this.cuid = mac;
        this.tex = tex;
        this.accessToken = accessToken;
    }

    public VoiceSynthesisReq() {
    }
}
