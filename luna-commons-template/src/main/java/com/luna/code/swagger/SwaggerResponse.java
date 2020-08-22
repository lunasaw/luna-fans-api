package com.luna.code.swagger;

/*****
 * @Author: shenkunlin
 * @Date: 2019/7/23 14:51
 * @Description: com.luna.code.util
 * 响应数据配置
 ****/
public class SwaggerResponse {
    // 响应状态码 200,404.。。
    private int    code;

    // 描述
    private String description;

    // 响应引用
    private String schema;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
