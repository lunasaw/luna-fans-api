//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.luna.tencent.dto.voice;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * @author luna
 */
public class SpeechRecognitionResponse {
    @JsonIgnore
    private String                                streamId;
    private int                                   code;
    private String                                message;
    @JSONField(name = "voice_id")

    private String                                voiceId;
    @JSONField(name = "final")
    private Integer                               finalSpeech;
    @JSONField(name = "result_list")
    private List<SpeechRecognitionResponseResult> result;
    private String                                messageId;

    public SpeechRecognitionResponse() {}

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JSONField(name = "voice_id")
    public void setVoiceId(String voiceId) {
        this.voiceId = voiceId;
    }

    @JSONField(name = "final")
    public void setFinalSpeech(Integer finalSpeech) {
        this.finalSpeech = finalSpeech;
    }

    @JSONField(name = "result_list")
    public void setResult(List<SpeechRecognitionResponseResult> result) {
        this.result = result;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getStreamId() {
        return this.streamId;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getVoiceId() {
        return this.voiceId;
    }

    public Integer getFinalSpeech() {
        return this.finalSpeech;
    }

    public List<SpeechRecognitionResponseResult> getResult() {
        return this.result;
    }

    public String getMessageId() {
        return this.messageId;
    }
}
