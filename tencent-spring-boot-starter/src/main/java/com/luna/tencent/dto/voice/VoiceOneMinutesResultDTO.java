package com.luna.tencent.dto.voice;

import com.alibaba.fastjson.annotation.JSONField;
import com.tencentcloudapi.asr.v20190614.models.SentenceWord;

import java.util.List;

/**
 * @author luna
 * 2021/6/14
 */
public class VoiceOneMinutesResultDTO {

    @JSONField(name = "WordList")
    private List<SentenceWord> wordList;
    @JSONField(name = "Result")
    private String             result;
    @JSONField(name = "WordSize")
    private String             wordSize;
    @JSONField(name = "AudioDuration")
    private String             audioDuration;

    public List<SentenceWord> getWordList() {
        return wordList;
    }

    public void setWordList(List<SentenceWord> wordList) {
        this.wordList = wordList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWordSize() {
        return wordSize;
    }

    public void setWordSize(String wordSize) {
        this.wordSize = wordSize;
    }

    public String getAudioDuration() {
        return audioDuration;
    }

    public void setAudioDuration(String audioDuration) {
        this.audioDuration = audioDuration;
    }
}
