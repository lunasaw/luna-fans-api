package com.luna.baidu.req.voice;

import com.alibaba.fastjson2.annotation.JSONField;

/**
 * @author luna
 */
public class VoiceCheckReq {

    /** 语音文件的格式，pcm/wav/amr/m4a。不区分大小写。推荐pcm文件 */
    private String  format  = "pcm";

    /** 采样率，16000、8000，固定值 */
    private Integer rate    = 16000;

    /** 声道数，仅支持单声道，请填写固定值 1 */
    private Integer channel = 1;

    /** 用户唯一标识，用来区分用户，计算UV值。建议填写能区分用户的机器 MAC 地址或 IMEI 码，长度为60字符以内。 */
    private String  cuid;

    /**
     * 不填写lan参数生效，都不填写，默认1537（普通话 输入法模型），dev_pid参数见本节开头的表格
     * 1537 普通话 输入法模型 有标点 支持自定义词库
     * 1737 英语 无标点 不支持自定义词库
     * 1637 粤语 有标点 不支持自定义词库
     * 1837 四川话 有标点 不支持自定义词库
     * 1936 普通话远场 远场模型 有标点 不支持
     *
     */
    @JSONField(name = "dev_pid")
    private Integer devPid  = 1537;

    /** 自训练平台模型id，填dev_pid = 8001 或 8002生效 */
    @JSONField(name = "lm_id")
    private Integer lmId;

    /** 本地语音文件的的二进制语音数据 ，需要进行base64 编码。与len参数连一起使用。 */
    private String  speech;

    /** 本地语音文件的的字节数，单位字节 */
    private Integer len;

    private String  token;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public Integer getDevPid() {
        return devPid;
    }

    public void setDevPid(Integer devPid) {
        this.devPid = devPid;
    }

    public Integer getLmId() {
        return lmId;
    }

    public void setLmId(Integer lmId) {
        this.lmId = lmId;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public VoiceCheckReq(String token, String cuid, Integer lmId, String speech, Integer len) {
        this.cuid = cuid;
        this.lmId = lmId;
        this.speech = speech;
        this.len = len;
        this.token = token;
    }

    public VoiceCheckReq(String cuid, Integer lmId, String speech, Integer len) {
        this.cuid = cuid;
        this.lmId = lmId;
        this.speech = speech;
        this.len = len;
    }

    public VoiceCheckReq() {}
}
