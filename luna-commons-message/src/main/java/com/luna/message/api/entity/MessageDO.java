package com.luna.message.api.entity;

import com.luna.message.api.constant.MessageTypeConstant;
import com.luna.message.api.constant.TargetKeyConstant;
import com.luna.message.api.constant.TargetTypeConstant;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author Tony
 */
public class MessageDO {
    /** 占位内容 */
    Map<String, String>         placeholderContent;
    /** 目标，见{@link TargetKeyConstant} */
    private Map<String, String> targetMap;
    /** 目标类型，见{@link TargetTypeConstant */
    @NotBlank
    private String              targetType;
    /** 消息类型，见{@link MessageTypeConstant} */
    @NotBlank
    private String              messageType;
    /** 消息id */
    @Min(1)
    private long                templateId;

    /** 发送目标 */
    public Map<String, String> getTargetMap() {
        return targetMap;
    }

    public void setTargetMap(Map<String, String> targetMap) {
        this.targetMap = targetMap;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(long templateId) {
        this.templateId = templateId;
    }

    public Map<String, String> getPlaceholderContent() {
        return placeholderContent;
    }

    public void setPlaceholderContent(Map<String, String> placeholderContent) {
        this.placeholderContent = placeholderContent;
    }

    @Override
    public String toString() {
        return "MessageDO{" +
            "targetMap=" + targetMap +
            ", targetType='" + targetType + '\'' +
            ", messageType='" + messageType + '\'' +
            ", templateId=" + templateId +
            ", placeholderContent=" + placeholderContent +
            '}';
    }
}
