//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.luna.tencent.response.voice;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

/**
 * @author luna
 */
public class SpeechRecognitionResponseResult {
    @JSONField(name = "slice_type")
    private Integer                                    sliceType;
    private Integer                                    index;
    @JSONField(name = "start_time")
    private Long                                       startTime;
    @JSONField(name = "end_time")
    private Long                                       endTime;
    @JSONField(name = "voice_text_str")
    private String                                     voiceTextStr;
    @JSONField(name = "word_size")
    private Integer                                    wordSize;
    @JSONField(name = "word_list")
    private List<SpeechRecognitionResponseResult.Word> wordList;

    @JSONField(name = "slice_type")
    public void setSliceType(Integer sliceType) {
        this.sliceType = sliceType;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @JSONField(name = "start_time")
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @JSONField(name = "end_time")
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @JSONField(name = "voice_text_str")
    public void setVoiceTextStr(String voiceTextStr) {
        this.voiceTextStr = voiceTextStr;
    }

    @JSONField(name = "word_size")
    public void setWordSize(Integer wordSize) {
        this.wordSize = wordSize;
    }

    @JSONField(name = "word_list")
    public void setWordList(List<SpeechRecognitionResponseResult.Word> wordList) {
        this.wordList = wordList;
    }

    public Integer getSliceType() {
        return this.sliceType;
    }

    public Integer getIndex() {
        return this.index;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public String getVoiceTextStr() {
        return this.voiceTextStr;
    }

    public Integer getWordSize() {
        return this.wordSize;
    }

    public List<SpeechRecognitionResponseResult.Word> getWordList() {
        return this.wordList;
    }

    public SpeechRecognitionResponseResult() {}

    public static class Word {
        private String  word;
        @JSONField(name = "start_time")
        private Long    startTime;
        @JSONField(name = "end_time")
        private Long    endTime;
        @JSONField(name = "stable_flag")
        private Integer stableFlag;

        public void setWord(String word) {
            this.word = word;
        }

        @JSONField(name = "start_time")
        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        @JSONField(name = "end_time")
        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }

        @JSONField(name = "stable_flag")
        public void setStableFlag(Integer stableFlag) {
            this.stableFlag = stableFlag;
        }

        public String getWord() {
            return this.word;
        }

        public Long getStartTime() {
            return this.startTime;
        }

        public Long getEndTime() {
            return this.endTime;
        }

        public Integer getStableFlag() {
            return this.stableFlag;
        }

        public Word() {}
    }
}
