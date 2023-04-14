package com.luna.baidu.req.voice;

import lombok.Data;

/**
 * VoiceSynthesisResponse
 * @author weidian
 */
@Data
public class VoiceSynthesisResponse {
    /**
     * log id
     */
    private Integer logId;
    /**
     * 任务id。注意保存该id，用于后续请求结果
     */
    private String taskId;
    /**
     * 任务状态
     */
    private String taskStatus;
    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;
}