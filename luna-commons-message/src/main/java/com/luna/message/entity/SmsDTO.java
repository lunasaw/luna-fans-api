package com.luna.message.entity;

import java.util.List;

/**
 * @Package: com.luna.message.entity
 * @ClassName: SmsDTO
 * @Author: luna
 * @CreateTime: 2020/8/9 13:19
 * @Description:
 */
public class SmsDTO {

    /** 手机列表 */
    private List<String> phones;

    /** 发送应用id */
    private String       appid;

    /** 签名 */
    private String       sign;

    /** 短信模板id */
    private String       templateId;

    /** 占位消息 */
    private List<String> message;

    public SmsDTO() {}

    public SmsDTO(List<String> phones, String appid, String sign, String templateId, List<String> message) {
        this.phones = phones;
        this.appid = appid;
        this.sign = sign;
        this.templateId = templateId;
        this.message = message;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
