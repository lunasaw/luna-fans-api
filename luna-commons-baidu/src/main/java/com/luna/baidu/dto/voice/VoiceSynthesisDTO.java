package com.luna.baidu.dto.voice;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: VoiceSynthesisDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 21:54
 * @Description:
 */
public class VoiceSynthesisDTO {

    /**
     * 发音人选择, 基础音库：0为度小美，1为度小宇，3为度逍遥，4为度丫丫，
     * 精品音库：5为度小娇，103为度米朵，106为度博文，110为度小童，111为度小萌，默认为度小美
     */
    private String per;

    /** 语速，取值0-15，默认为5中语速 */
    private String spd;

    /** 音调，取值0-15，默认为5中语调 */
    private String pit;

    /** 音量，取值0-9，默认为5中音量 */
    private String vol;

    /** 机器标识 */
    private String cuid;

    /** 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav */
    private String aue;

    /** 语言 zh */
    private String lan = "Zh";

    /** 1 */
    private String ctp = "1";

    /** 合成文字 urlencode,utf-8 编码 */
    private String tex;

    /** 文件保存文件夹 */
    private String savePath;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    public String getPit() {
        return pit;
    }

    public void setPit(String pit) {
        this.pit = pit;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getAue() {
        return aue;
    }

    public void setAue(String aue) {
        this.aue = aue;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getCtp() {
        return ctp;
    }

    public void setCtp(String ctp) {
        this.ctp = ctp;
    }

    public String getTex() {
        return tex;
    }

    public void setTex(String tex) {
        this.tex = tex;
    }
}
