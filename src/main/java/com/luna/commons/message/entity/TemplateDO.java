package com.luna.commons.message.entity;

import java.util.Date;

/**
 * 实体template
 *
 * @author MrZhang-YUBO
 */
public class TemplateDO {
    /** 用户主键 */
    private long   id;
    /** 创建时间 */
    private Date   createTime;
    /** 修改时间 */
    private Date   modifiedTime;
    /** 标题 */
    private String subject;
    /** 内容 */
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
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
}
