package com.luna.code.swagger;

import java.util.List;

/*****
 * @Author: shenkunlin
 * @Date: 2019/7/23 15:11
 * @Description: com.luna.code.util
 * Swagger请求路径配置
 ****/
public class SwaggerPath {

    // 请求路径
    private String              path;

    // 方法配置
    private List<SwaggerMethod> methods;

    // 对象
    private String              model;

    // 对象-首字母小写
    private String              table;

    public SwaggerPath(String Table, String table) {
        this.model = Table;
        this.table = table;
    }

    public SwaggerPath() {}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SwaggerMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<SwaggerMethod> methods) {
        this.methods = methods;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
