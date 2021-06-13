//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.luna.tencent.dto.voice;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FlashRecognitionResponse {
    @JsonProperty("request_id")
    private String                                                requestId;
    @JsonProperty("code")
    private Integer                                               code;
    @JsonProperty("message")
    private String                                                message;
    @JsonProperty("audio_duration")
    private Long                                                  audioDuration;
    @JsonProperty("flash_result")
    private List<FlashRecognitionResponse.FlashRecognitionResult> flashResult;

    @JsonProperty("request_id")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("audio_duration")
    public void setAudioDuration(Long audioDuration) {
        this.audioDuration = audioDuration;
    }

    @JsonProperty("flash_result")
    public void setFlashResult(List<FlashRecognitionResponse.FlashRecognitionResult> flashResult) {
        this.flashResult = flashResult;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getAudioDuration() {
        return this.audioDuration;
    }

    public List<FlashRecognitionResponse.FlashRecognitionResult> getFlashResult() {
        return this.flashResult;
    }

    public FlashRecognitionResponse() {}

    public static class FlashWordData {
        @JsonProperty("word")
        private String  word;
        @JsonProperty("start_time")
        private Long    startTime;
        @JsonProperty("end_time")
        private Long    endTime;
        @JsonProperty("stable_flag")
        private Integer stableFlag;

        @JsonProperty("word")
        public void setWord(String word) {
            this.word = word;
        }

        @JsonProperty("start_time")
        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        @JsonProperty("end_time")
        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }

        @JsonProperty("stable_flag")
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

        public FlashWordData() {}
    }

    public static class FlashRecognitionSentence {
        @JsonProperty("text")
        private String                                       text;
        @JsonProperty("start_time")
        private Long                                         startTime;
        @JsonProperty("end_time")
        private Long                                         endTime;
        @JsonProperty("speaker_id")
        private Integer                                      speakerId;
        @JsonProperty("word_list")
        private List<FlashRecognitionResponse.FlashWordData> wordList;

        @JsonProperty("text")
        public void setText(String text) {
            this.text = text;
        }

        @JsonProperty("start_time")
        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        @JsonProperty("end_time")
        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }

        @JsonProperty("speaker_id")
        public void setSpeakerId(Integer speakerId) {
            this.speakerId = speakerId;
        }

        @JsonProperty("word_list")
        public void setWordList(List<FlashRecognitionResponse.FlashWordData> wordList) {
            this.wordList = wordList;
        }

        public String getText() {
            return this.text;
        }

        public Long getStartTime() {
            return this.startTime;
        }

        public Long getEndTime() {
            return this.endTime;
        }

        public Integer getSpeakerId() {
            return this.speakerId;
        }

        public List<FlashRecognitionResponse.FlashWordData> getWordList() {
            return this.wordList;
        }

        public FlashRecognitionSentence() {}
    }

    public static class FlashRecognitionResult {
        @JsonProperty("text")
        private String                                                  text;
        @JsonProperty("channel_id")
        private Integer                                                 channelId;
        @JsonProperty("sentence_list")
        private List<FlashRecognitionResponse.FlashRecognitionSentence> sentenceList;

        @JsonProperty("text")
        public void setText(String text) {
            this.text = text;
        }

        @JsonProperty("channel_id")
        public void setChannelId(Integer channelId) {
            this.channelId = channelId;
        }

        @JsonProperty("sentence_list")
        public void setSentenceList(List<FlashRecognitionResponse.FlashRecognitionSentence> sentenceList) {
            this.sentenceList = sentenceList;
        }

        public String getText() {
            return this.text;
        }

        public Integer getChannelId() {
            return this.channelId;
        }

        public List<FlashRecognitionResponse.FlashRecognitionSentence> getSentenceList() {
            return this.sentenceList;
        }

        public FlashRecognitionResult() {}
    }
}
