package com.luna.api.email.dto;

import com.luna.api.email.constant.MessageTypeConstant;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author Luna
 */
@Data
public class SendKeyMessageDTO {
    /**
     * 发送人列表列表
     */
    private List<SendModelDTO> sendModelDTOS;
    /**
     * site
     */
    @NotBlank
    private String              site;

    /**
     * 消息类型，见{@link MessageTypeConstant}
     */
    @NotBlank
    private String              messageType;

    /**
     * 消息id
     */
    @Min(1)
    private Long                templateId;
    /**
     * 占位内容
     */
    private Map<String, String> placeholderContent;

}
