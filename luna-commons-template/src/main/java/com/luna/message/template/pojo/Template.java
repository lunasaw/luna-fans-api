package com.luna.message.template.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
/****
 * @Author:luna
 * @Description:Template构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Template",value = "Template")
@Table(name="tb_template")
public class Template implements Serializable{

	@ApiModelProperty(value = "主键",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	/** 主键 */
    @Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "创建时间",required = false)
	/** 创建时间 */
    @Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "修改时间",required = false)
	/** 修改时间 */
    @Column(name = "modified_time")
	private Date modifiedTime;

	@ApiModelProperty(value = "标题",required = false)
	/** 标题 */
    @Column(name = "subject")
	private String subject;

	@ApiModelProperty(value = "内容",required = false)
	/** 内容 */
    @Column(name = "content")
	private String content;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
