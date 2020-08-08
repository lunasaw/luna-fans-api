package com.luna.message.entity;

/**
 * @Package: com.luna.message.entity
 * @ClassName: EmailSmallDTO
 * @Author: luna
 * @CreateTime: 2020/8/8 17:55
 * @Description:
 */
public class EmailSmallDTO {

    /** 发送人昵称 */
    private String   userName;

    /** 接收人 */
    private String   to;

    /** 主题 */
    private String   subject;

    /** 内容 */
    private String   content;

    /** 加密抄送 */
    private String[] bcc;

    /** 抄送人 */
    private String[] cc;

    public EmailSmallDTO(String userName, String to, String subject, String content) {
        this.userName = userName;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public EmailSmallDTO() {}

    public EmailSmallDTO(String userName, String to, String subject, String content, String[] bcc, String[] cc) {
        this.userName = userName;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.bcc = bcc;
        this.cc = cc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }
}
