package com.luna.baidu.dto;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: VoiceCheckDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 20:16
 * @Description:
 */
public class VoiceCheckDTO {

    /** 语音文件的格式，pcm/wav/amr/m4a。不区分大小写。推荐pcm文件 */
    private String  format;

    /** 采样率，16000、8000，固定值 */
    private Integer rate;

    /** 声道数，仅支持单声道，请填写固定值 1 */
    private Integer channel;

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
    private Integer dev_pid;

    /** 自训练平台模型id，填dev_pid = 8001 或 8002生效 */
    private Integer lm_id;

    /** 本地语音文件的的二进制语音数据 ，需要进行base64 编码。与len参数连一起使用。 */
    private String  speech;

    /** 本地语音文件的的字节数，单位字节 */
    private Integer len;

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

    public Integer getDev_pid() {
        return dev_pid;
    }

    public void setDev_pid(Integer dev_pid) {
        this.dev_pid = dev_pid;
    }

    public Integer getLm_id() {
        return lm_id;
    }

    public void setLm_id(Integer lm_id) {
        this.lm_id = lm_id;
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
}
