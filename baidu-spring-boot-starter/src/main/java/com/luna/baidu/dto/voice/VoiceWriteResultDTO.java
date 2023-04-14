package com.luna.baidu.dto.voice;

import java.util.List;

/**
 * @author luna@mac
 * @link https://cloud.baidu.com/doc/SPEECH/s/6k5dilahb
 * 2021年05月11日 15:53
 */
public class VoiceWriteResultDTO {

    private String     taskStatus;

    private TaskResult taskResult;

    public String getTaskStatus() {
        return taskStatus;
    }

    public VoiceWriteResultDTO setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }

    public TaskResult getTaskResult() {
        return taskResult;
    }

    public VoiceWriteResultDTO setTaskResult(TaskResult taskResult) {
        this.taskResult = taskResult;
        return this;
    }
}

class TaskResult {
    private List<String>         result;

    private Integer              audioDuration;

    private List<DetailedResult> detailedResult;

    public List<String> getResult() {
        return result;
    }

    public TaskResult setResult(List<String> result) {
        this.result = result;
        return this;
    }

    public Integer getAudioDuration() {
        return audioDuration;
    }

    public TaskResult setAudioDuration(Integer audioDuration) {
        this.audioDuration = audioDuration;
        return this;
    }

    public List<DetailedResult> getDetailedResult() {
        return detailedResult;
    }

    public TaskResult setDetailedResult(List<DetailedResult> detailedResult) {
        this.detailedResult = detailedResult;
        return this;
    }
}

class DetailedResult {
    private List<String> res;

    private Integer      endTime;

    private Integer      beginTime;

    private String       corpusNo;

    private String       sn;

    private List<String> wordsInfo;

    public List<String> getRes() {
        return res;
    }

    public DetailedResult setRes(List<String> res) {
        this.res = res;
        return this;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public DetailedResult setEndTime(Integer endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public DetailedResult setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getCorpusNo() {
        return corpusNo;
    }

    public DetailedResult setCorpusNo(String corpusNo) {
        this.corpusNo = corpusNo;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public DetailedResult setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public List<String> getWordsInfo() {
        return wordsInfo;
    }

    public DetailedResult setWordsInfo(List<String> wordsInfo) {
        this.wordsInfo = wordsInfo;
        return this;
    }
}