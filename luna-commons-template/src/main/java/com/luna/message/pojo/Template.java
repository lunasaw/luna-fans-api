package com.luna.message.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;

/****
 * @Author:shenkunlin
 * @Description:Template构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Template", value = "Template")
@Table(name = "tb_template")
public class Template implements Serializable {

    @ApiModelProperty(value = "主键", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long   id;          // 主键

    @ApiModelProperty(value = "创建时间", required = false)
    @Column(name = "create_time")
    private Date   createTime;  // 创建时间

    @ApiModelProperty(value = "修改时间", required = false)
    @Column(name = "modified_time")
    private Date   modifiedTime;// 修改时间

    @ApiModelProperty(value = "标题", required = false)
    @Column(name = "subject")
    private String subject;     // 标题

    @ApiModelProperty(value = "内容", required = false)
    @Column(name = "content")
    private String content;     // 内容

    // get方法
    public Long getId() {
        return id;
    }

    // set方法
    public void setId(Long id) {
        this.id = id;
    }

    // get方法
    public Date getCreateTime() {
        return createTime;
    }

    // set方法
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    // get方法
    public Date getModifiedTime() {
        return modifiedTime;
    }

    // set方法
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    // get方法
    public String getSubject() {
        return subject;
    }

    // set方法
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // get方法
    public String getContent() {
        return content;
    }

    // set方法
    public void setContent(String content) {
        this.content = content;
    }

}
