package com.luna.tencent.dto.voice;

/**
 * @author luna
 * 2021/6/15
 */
public class VoiceOneMinutesDTO {

    /**
     * engSerViceType 引擎模型类型。
     * 电话场景：
     * • 8k_en：电话 8k 英语；
     * • 8k_zh：电话 8k 中文普通话通用；
     * 非电话场景：
     * • 16k_zh：16k 中文普通话通用；
     * • 16k_en：16k 英语；
     * • 16k_ca：16k 粤语；
     * • 16k_ja：16k 日语；
     * •16k_wuu-SH：16k 上海话方言；
     * •16k_zh_medical：16k 医疗。
     * sourceType 语音数据来源。0：语音 URL；1：语音数据（post body）
     * voiceFormat 识别音频的音频格式。mp3、wav。
     * usrAudioKey 用户端对此任务的唯一标识，用户自助生成，用于用户查找识别结果。
     * url 语音 URL，公网可下载。当 SourceType 值为 0（语音 URL上传） 时须填写该字段，为 1 时不填；URL 的长度大于 0，小于
     * 2048，需进行urlencode编码。音频时间长度要小于60s。
     * data 语音数据，当SourceType 值为1（本地语音数据上传）时必须填写，当SourceType 值为0（语音
     * URL上传）可不写。要使用base64编码(采用python语言时注意读取文件应该为string而不是byte，以byte格式读取后要decode()。编码后的数据不可带有回车换行符)。数据长度要小于3MB（Base64后）。
     * dataLen 数据长度，单位为字节。当 SourceType 值为1（本地语音数据上传）时必须填写，当 SourceType 值为0（语音
     * URL上传）可不写（此数据长度为数据未进行base64编码时的数据长度）。
     * hotwordId 热词id。用于调用对应的热词表，如果在调用语音识别服务时，不进行单独的热词id设置，自动生效默认热词；如果进行了单独的热词id设置，那么将生效单独设置的热词id。
     * filterDirty 是否过滤脏词（目前支持中文普通话引擎）。0：不过滤脏词；1：过滤脏词；2：将脏词替换为 * 。默认值为 0。
     * filterModal 是否过语气词（目前支持中文普通话引擎）。0：不过滤语气词；1：部分过滤；2：严格过滤 。默认值为 0。
     * filterPunc 是否过滤标点符号（目前支持中文普通话引擎）。 0：不过滤，1：过滤句末标点，2：过滤所有标点。默认值为 0。
     * convertNumMode 是否进行阿拉伯数字智能转换。0：不转换，直接输出中文数字，1：根据场景智能转换为阿拉伯数字。默认值为1。
     * wordInfo 是否显示词级别时间戳。0：不显示；1：显示，不包含标点时间戳，2：显示，包含标点时间戳。支持引擎8k_zh，16k_zh，16k_en，16k_ca，16k_ja，16k_wuu-SH。默认值为
     * 0。
     */
    private String  engSerViceType = "16k_zh";
    private Integer sourceType;
    private String  voiceFormat;
    private String  usrAudioKey;
    private String  url;
    private String  data;
    private Integer dataLen;
    private Integer hotwordId;
    private Integer filterDirty;
    private Integer filterModal;
    private Integer filterPunc;
    private Integer convertNumMode;
    private Integer wordInfo;

    public VoiceOneMinutesDTO() {}

    public VoiceOneMinutesDTO(String engSerViceType, Integer sourceType, String voiceFormat, String usrAudioKey,
        String url, String data, Integer dataLen, Integer hotwordId, Integer filterDirty, Integer filterModal,
        Integer filterPunc, Integer convertNumMode, Integer wordInfo) {
        this.engSerViceType = engSerViceType;
        this.sourceType = sourceType;
        this.voiceFormat = voiceFormat;
        this.usrAudioKey = usrAudioKey;
        this.url = url;
        this.data = data;
        this.dataLen = dataLen;
        this.hotwordId = hotwordId;
        this.filterDirty = filterDirty;
        this.filterModal = filterModal;
        this.filterPunc = filterPunc;
        this.convertNumMode = convertNumMode;
        this.wordInfo = wordInfo;
    }

    public VoiceOneMinutesDTO(String engSerViceType, Integer sourceType, String voiceFormat, String usrAudioKey,
        String url, String data, Integer dataLen) {
        this.engSerViceType = engSerViceType;
        this.sourceType = sourceType;
        this.voiceFormat = voiceFormat;
        this.usrAudioKey = usrAudioKey;
        this.url = url;
        this.data = data;
        this.dataLen = dataLen;
    }

    public String getEngSerViceType() {
        return engSerViceType;
    }

    public void setEngSerViceType(String engSerViceType) {
        this.engSerViceType = engSerViceType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getVoiceFormat() {
        return voiceFormat;
    }

    public void setVoiceFormat(String voiceFormat) {
        this.voiceFormat = voiceFormat;
    }

    public String getUsrAudioKey() {
        return usrAudioKey;
    }

    public void setUsrAudioKey(String usrAudioKey) {
        this.usrAudioKey = usrAudioKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getDataLen() {
        return dataLen;
    }

    public void setDataLen(Integer dataLen) {
        this.dataLen = dataLen;
    }

    public Integer getHotwordId() {
        return hotwordId;
    }

    public void setHotwordId(Integer hotwordId) {
        this.hotwordId = hotwordId;
    }

    public Integer getFilterDirty() {
        return filterDirty;
    }

    public void setFilterDirty(Integer filterDirty) {
        this.filterDirty = filterDirty;
    }

    public Integer getFilterModal() {
        return filterModal;
    }

    public void setFilterModal(Integer filterModal) {
        this.filterModal = filterModal;
    }

    public Integer getFilterPunc() {
        return filterPunc;
    }

    public void setFilterPunc(Integer filterPunc) {
        this.filterPunc = filterPunc;
    }

    public Integer getConvertNumMode() {
        return convertNumMode;
    }

    public void setConvertNumMode(Integer convertNumMode) {
        this.convertNumMode = convertNumMode;
    }

    public Integer getWordInfo() {
        return wordInfo;
    }

    public void setWordInfo(Integer wordInfo) {
        this.wordInfo = wordInfo;
    }
}
