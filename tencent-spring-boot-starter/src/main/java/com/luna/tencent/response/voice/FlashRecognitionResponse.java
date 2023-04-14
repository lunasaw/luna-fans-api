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
public class FlashRecognitionResponse {
    @JSONField(name = "request_id")
    private String                                                requestId;
    @JSONField(name = "code")
    private Integer                                               code;
    @JSONField(name = "message")
    private String                                                message;
    @JSONField(name = "audio_duration")
    private Long                                                  audioDuration;
    @JSONField(name = "flash_result")
    private List<FlashRecognitionResponse.FlashRecognitionResult> flashResult;

    @JSONField(name = "request_id")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JSONField(name = "code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JSONField(name = "message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JSONField(name = "audio_duration")
    public void setAudioDuration(Long audioDuration) {
        this.audioDuration = audioDuration;
    }

    @JSONField(name = "flash_result")
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
        @JSONField(name = "word")
        private String  word;
        @JSONField(name = "start_time")
        private Long    startTime;
        @JSONField(name = "end_time")
        private Long    endTime;
        @JSONField(name = "stable_flag")
        private Integer stableFlag;

        @JSONField(name = "word")
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

        public FlashWordData() {}
    }

    public static class FlashRecognitionSentence {
        @JSONField(name = "text")
        private String                                       text;
        @JSONField(name = "start_time")
        private Long                                         startTime;
        @JSONField(name = "end_time")
        private Long                                         endTime;
        @JSONField(name = "speaker_id")
        private Integer                                      speakerId;
        @JSONField(name = "word_list")
        private List<FlashRecognitionResponse.FlashWordData> wordList;

        @JSONField(name = "text")
        public void setText(String text) {
            this.text = text;
        }

        @JSONField(name = "start_time")
        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        @JSONField(name = "end_time")
        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }

        @JSONField(name = "speaker_id")
        public void setSpeakerId(Integer speakerId) {
            this.speakerId = speakerId;
        }

        @JSONField(name = "word_list")
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
        @JSONField(name = "text")
        private String                                                  text;
        @JSONField(name = "channel_id")
        private Integer                                                 channelId;
        @JSONField(name = "sentence_list")
        private List<FlashRecognitionResponse.FlashRecognitionSentence> sentenceList;

        @JSONField(name = "text")
        public void setText(String text) {
            this.text = text;
        }

        @JSONField(name = "channel_id")
        public void setChannelId(Integer channelId) {
            this.channelId = channelId;
        }

        @JSONField(name = "sentence_list")
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
