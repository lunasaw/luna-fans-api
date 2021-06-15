package com.luna.tencent.response.voice;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author luna
 * 2021/6/14
 */
public class VoiceIdentifyResponse {

    @JSONField(name = "RequestId")
    private String requestId;

    @JSONField(name = "Data")
    private Task   data;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Task getData() {
        return data;
    }

    public void setData(Task data) {
        this.data = data;
    }

}

class Task {
    @JSONField(name = "TaskId")
    private Integer taskId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
