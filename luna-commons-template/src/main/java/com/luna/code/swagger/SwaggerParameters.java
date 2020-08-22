package com.luna.code.swagger;

/*****
 * @Author: shenkunlin
 * @Date: 2019/7/23 14:54
 * @Description: com.luna.code.util
 * Swagger参数
 ****/
public class SwaggerParameters {

    // 输入类型 body
    private String  in;

    // 输入名字介绍 Album对象
    private String  name;

    // 输入描述
    private String  description;

    // 是否是必须输入
    private Boolean required;

    // 引用对象
    private String  schema;

    // 类型
    private String  type;

    // format
    private String  format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
