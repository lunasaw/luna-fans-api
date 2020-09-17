package com.luna.code.swagger;

import java.util.List;

/*****
 * @Author: shenkunlin
 * @Date: 2019/7/22 15:29
 * @Description: com.luna.code.util
 * javabean信息
 ****/
public class SwaggerModel {

    // 名字
    private String                       name;

    // 类型
    private String                       type;

    // 字段集合
    private List<SwaggerModelProperties> properties;

    // 描述
    private String                       description;

    public SwaggerModel() {}

    public SwaggerModel(String name, String type, List<SwaggerModelProperties> properties, String description) {
        this.name = name;
        this.type = type;
        this.properties = properties;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SwaggerModelProperties> getProperties() {
        return properties;
    }

    public void setProperties(List<SwaggerModelProperties> properties) {
        this.properties = properties;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
