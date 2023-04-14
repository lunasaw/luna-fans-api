package com.luna.api.email.dto;

import com.luna.api.email.constant.MessageTypeConstant;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

public class MessageDTO {
    /** 目标列表，邮箱或手机 */
    private List<String>        targetList;

    /** 消息类型，见{@link MessageTypeConstant} */
    @NotBlank
    private String              messageType;

    /** 消息id */
    @Min(1)
    private Long                templateId;
    /** 占位内容 */
    private Map<String, String> placeholderContent;

    public List<String> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<String> targetList) {
        this.targetList = targetList;
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
}
