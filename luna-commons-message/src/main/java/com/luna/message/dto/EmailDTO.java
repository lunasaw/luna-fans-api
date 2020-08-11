package com.luna.message.dto;

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

    private ModelContentDTO     modelContentDTO;

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

    public ModelContentDTO getModelContentDTO() {
        return modelContentDTO;
    }

    public void setModelContentDTO(ModelContentDTO modelContentDTO) {
        this.modelContentDTO = modelContentDTO;
    }
}
