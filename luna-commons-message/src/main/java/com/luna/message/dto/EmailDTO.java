package com.luna.message.dto;

import java.util.Map;

/**
 * @Package: com.luna.message
 * @ClassName: dto
 * @Author: luna
 * @CreateTime: 2020/8/8 17:20
 * @Description:
 */
public class EmailDTO {

    /** 模板名称 */
    private String              modelName;

    private EmailSmallDTO       emailSmallDTO;

    /** 介绍 */
    private Map<String, String> contents;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public EmailSmallDTO getEmailSmallDTO() {
        return emailSmallDTO;
    }

    public void setEmailSmallDTO(EmailSmallDTO emailSmallDTO) {
        this.emailSmallDTO = emailSmallDTO;
    }

    public Map<String, String> getContents() {
        return contents;
    }

    public void setContents(Map<String, String> contents) {
        this.contents = contents;
    }
}
