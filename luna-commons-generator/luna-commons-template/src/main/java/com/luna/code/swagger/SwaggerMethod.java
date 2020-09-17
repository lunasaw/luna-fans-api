package com.luna.code.swagger;

import java.util.List;

/*****
 * @Author: shenkunlin
 * @Date: 2019/7/23 14:25
 * @Description: com.luna.code.util
 ****/
public class SwaggerMethod {

    // 提交路径
    private String                  url;

    // 提交方式 GET、POST、DELETE。。。
    private String                  method;

    // 所属Tags
    private String                  tags;

    // 简介
    private String                  summary;

    // 详细描述
    private String                  description;

    // 操作ID
    private String                  operationId;

    // 接收数据类型
    private String                  consumes;

    // 响应类型
    private String                  produces;

    // 响应数据
    private List<SwaggerResponse>   responses;

    // 参数设置
    private List<SwaggerParameters> swaggerParameters;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getConsumes() {
        return consumes;
    }

    public void setConsumes(String consumes) {
        this.consumes = consumes;
    }

    public String getProduces() {
        return produces;
    }

    public void setProduces(String produces) {
        this.produces = produces;
    }

    public List<SwaggerResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<SwaggerResponse> responses) {
        this.responses = responses;
    }

    public List<SwaggerParameters> getSwaggerParameters() {
        return swaggerParameters;
    }

    public void setSwaggerParameters(List<SwaggerParameters> swaggerParameters) {
        this.swaggerParameters = swaggerParameters;
    }
}
