package com.luna.message.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;

/****
 * @Author:shenkunlin
 * @Description:Scheduled构建
 * @Date 2019/6/14 19:13
 *****/
@ApiModel(description = "Scheduled", value = "Scheduled")
@Table(name = "tb_scheduled")
public class Scheduled implements Serializable {

    @ApiModelProperty(value = "主键id", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cron_id")
    private Integer cronId;        // 主键id

    @ApiModelProperty(value = "定时任务完整类名", required = false)
    @Column(name = "cron_key")
    private String  cronKey;       // 定时任务完整类名

    @ApiModelProperty(value = "cron表达式", required = false)
    @Column(name = "cron_expression")
    private String  cronExpression;// cron表达式

    @ApiModelProperty(value = "任务描述", required = false)
    @Column(name = "task_explain")
    private String  taskExplain;   // 任务描述

    @ApiModelProperty(value = "状态,1:正常;2:停用", required = false)
    @Column(name = "status")
    private Integer status;        // 状态,1:正常;2:停用

    // get方法
    public Integer getCronId() {
        return cronId;
    }

    // set方法
    public void setCronId(Integer cronId) {
        this.cronId = cronId;
    }

    // get方法
    public String getCronKey() {
        return cronKey;
    }

    // set方法
    public void setCronKey(String cronKey) {
        this.cronKey = cronKey;
    }

    // get方法
    public String getCronExpression() {
        return cronExpression;
    }

    // set方法
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    // get方法
    public String getTaskExplain() {
        return taskExplain;
    }

    // set方法
    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain;
    }

    // get方法
    public Integer getStatus() {
        return status;
    }

    // set方法
    public void setStatus(Integer status) {
        this.status = status;
    }

}
