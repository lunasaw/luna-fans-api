package com.luna.tencent.response.message;

/**
 * @author luna
 * 2021/6/14
 */
public class SendStatusDTO {

    /** 发送流水号。 */
    private String  serialNo;

    /** 手机号码,e.164标准，+[国家或地区码][手机号] ，示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号。 */
    private String  phoneNumber;

    /** 计费条数。 */
    private Integer Fee;

    /** 用户Session内容。 */
    private String  sessionContext;

    /** 短信请求错误码。 */
    private String  code;

    /** 短信请求错误码描述。 */
    private String  message;

    /** 国家码或地区码，例如CN,US等，对于未识别出国家码或者地区码，默认返回DEF,具体支持列表请参考国际/港澳台计费总览。 */
    private String  isoCode;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getFee() {
        return Fee;
    }

    public void setFee(Integer fee) {
        Fee = fee;
    }

    public String getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(String sessionContext) {
        this.sessionContext = sessionContext;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
}
