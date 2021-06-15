package com.luna.tencent.dto.voice;

/**
 * @author luna
 * 2021/6/15
 */
public class VoiceFastIdentifyDTO {

    /**
     * engineType 引擎模型类型。
     * 8k_zh：8k 中文普通话通用；
     * 16k_zh：16k 中文普通话通用；
     * 16k_zh_video：16k 音视频领域。
     * voiceFormat 音频格式。支持 wav、pcm、ogg-opus、speex、silk、mp3、m4a、aac。
     * timestamp 当前 UNIX 时间戳，如果与当前时间相差超过3分钟，会报签名失败错误。
     * speakerDiarization 是否开启说话人分离（目前支持中文普通话引擎），默认为0，0：不开启，1：开启。
     * filterDirty 是否过滤脏词（目前支持中文普通话引擎），默认为0。0：不过滤脏词；1：过滤脏词；2：将脏词替换为 *。
     * filterModal 是否过滤语气词（目前支持中文普通话引擎），默认为0。0：不过滤语气词；1：部分过滤；2：严格过滤。
     * filterPunc 是否过滤标点符号（目前支持中文普通话引擎），默认为0。0：不过滤，1：过滤句末标点，2：过滤所有标点。
     * convertNumMode 是否进行阿拉伯数字智能转换，默认为1。0：全部转为中文数字；1：根据场景智能转换为阿拉伯数字。
     * wordInfo 是否显示词级别时间戳，默认为0。0：不显示；1：显示，不包含标点时间戳，2：显示，包含标点时间戳。
     * firstChannelOnly 是否只识别首个声道，默认为1。0：识别所有声道；1：识别首个声道。
     */
    private String  engineType;
    private String  voiceFormat;
    private Long    timestamp;
    private Integer speakerDiarization;
    private Integer filterDirty;
    private Integer filterModal;
    private Integer filterPunc;
    private Integer convertNumMode;
    private Integer wordInfo;
    private Integer firstChannelOnly;
    private String  fileName;

    public VoiceFastIdentifyDTO() {}

    public VoiceFastIdentifyDTO(String engineType, String voiceFormat, Long timestamp, Integer speakerDiarization,
        Integer filterDirty, Integer filterModal, Integer filterPunc, Integer convertNumMode, Integer wordInfo,
        Integer firstChannelOnly, String fileName) {
        this.engineType = engineType;
        this.voiceFormat = voiceFormat;
        this.timestamp = timestamp;
        this.speakerDiarization = speakerDiarization;
        this.filterDirty = filterDirty;
        this.filterModal = filterModal;
        this.filterPunc = filterPunc;
        this.convertNumMode = convertNumMode;
        this.wordInfo = wordInfo;
        this.firstChannelOnly = firstChannelOnly;
        this.fileName = fileName;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getVoiceFormat() {
        return voiceFormat;
    }

    public void setVoiceFormat(String voiceFormat) {
        this.voiceFormat = voiceFormat;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getSpeakerDiarization() {
        return speakerDiarization;
    }

    public void setSpeakerDiarization(Integer speakerDiarization) {
        this.speakerDiarization = speakerDiarization;
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

    public Integer getFirstChannelOnly() {
        return firstChannelOnly;
    }

    public void setFirstChannelOnly(Integer firstChannelOnly) {
        this.firstChannelOnly = firstChannelOnly;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
