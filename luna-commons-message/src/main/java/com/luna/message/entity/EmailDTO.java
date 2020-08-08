package com.luna.message.entity;

import java.util.Map;

/**
 * @Package: com.luna.message
 * @ClassName: entity
 * @Author: luna
 * @CreateTime: 2020/8/8 17:20
 * @Description:
 */
public class EmailDTO {

    /** 模板名称 */
    private String              modelName;

    /** 附件地址<名称,路径> */
    private Map<String, String> pathMap;

    private EmailSmallDTO       emailSmallDTO;

    private ModelContentDTO     modelContentDTO;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Map<String, String> getPathMap() {
        return pathMap;
    }

    public void setPathMap(Map<String, String> pathMap) {
        this.pathMap = pathMap;
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
