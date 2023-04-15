package com.luna.baidu.dto.voice;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * This class represents the structure of the data described in the table.
 * 
 * @author weidian
 */
@Data
public class VoiceSynthesisDetailResponse {
    @JSONField(name = "log_id")
    private Long           logId;
    @JSONField(name = "tasks_info")
    private List<TaskInfo> tasksInfo;
    @JSONField(name = "error_code")
    private Long           errorCode;
    @JSONField(name = "error_msg")
    private String         errorMsg;
    @JSONField(name = "error_info")
    private List<String>   errorInfo;

    @Data
    public static class TaskInfo {
        @JSONField(name = "task_id")
        private String     taskId;
        @JSONField(name = "task_status")
        private String     taskStatus;
        @JSONField(name = "task_result")
        private TaskResult taskResult;
    }

    @Data
    public static class TaskResult {
        @JSONField(name = "speech_url")
        private String          speechUrl;
        @JSONField(name = "speech_timestamp")
        private SpeechTimestamp speechTimestamp;
        @JSONField(name = "err_no")
        private Long            errNo;
        @JSONField(name = "err_msg")
        private String          errMsg;
        @JSONField(name = "sn")
        private String          sn;
    }

    @Data
    public static class SpeechTimestamp {
        @JSONField(name = "sentences")
        private List<Sentence> sentences;
    }

    @Data
    public static class Sentence {
        @JSONField(name = "sentence_texts")
        private String          sentenceTexts;
        @JSONField(name = "begin_time")
        private Long            beginTime;
        @JSONField(name = "end_time")
        private Long            endTime;
        @JSONField(name = "characters")
        private List<Character> characters;
    }

    @Data
    public static class Character {
        @JSONField(name = "character_text")
        private String characterText;
        @JSONField(name = "begin_time")
        private Long   beginTime;
        @JSONField(name = "end_time")
        private Long   endTime;
    }
}