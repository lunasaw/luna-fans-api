package com.luna.baidu.req;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: VoiceSynthesisReq
 * @Author: luna
 * @CreateTime: 2020/8/10 21:54
 * @Description:
 */
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

    private String tok;

    public VoiceSynthesisReq(String cuid, String tex, String tok) {
        this.cuid = cuid;
        this.tex = tex;
        this.tok = tok;
    }

    public String getPer() {
        return per;
    }

    public VoiceSynthesisReq setPer(String per) {
        this.per = per;
        return this;
    }

    public String getSpd() {
        return spd;
    }

    public VoiceSynthesisReq setSpd(String spd) {
        this.spd = spd;
        return this;
    }

    public String getPit() {
        return pit;
    }

    public VoiceSynthesisReq setPit(String pit) {
        this.pit = pit;
        return this;
    }

    public String getVol() {
        return vol;
    }

    public VoiceSynthesisReq setVol(String vol) {
        this.vol = vol;
        return this;
    }

    public String getCuid() {
        return cuid;
    }

    public VoiceSynthesisReq setCuid(String cuid) {
        this.cuid = cuid;
        return this;
    }

    public String getAue() {
        return aue;
    }

    public VoiceSynthesisReq setAue(String aue) {
        this.aue = aue;
        return this;
    }

    public String getLan() {
        return lan;
    }

    public VoiceSynthesisReq setLan(String lan) {
        this.lan = lan;
        return this;
    }

    public String getCtp() {
        return ctp;
    }

    public VoiceSynthesisReq setCtp(String ctp) {
        this.ctp = ctp;
        return this;
    }

    public String getTex() {
        return tex;
    }

    public VoiceSynthesisReq setTex(String tex) {
        this.tex = tex;
        return this;
    }

    public String getTok() {
        return tok;
    }

    public VoiceSynthesisReq setTok(String tok) {
        this.tok = tok;
        return this;
    }
}
