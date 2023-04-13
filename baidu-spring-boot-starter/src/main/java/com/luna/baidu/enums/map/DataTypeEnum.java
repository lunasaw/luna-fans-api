package com.luna.baidu.enums.map;

public enum DataTypeEnum {
    NOW("now", 1),
    FC("fc", 2),
    INDEX("index", 3),
    ALERT("alert", 4),
    FC_HOUR("fc_hour", 5),
    ALL("all", 6);

    private String  data;
    private Integer type;

    DataTypeEnum(String data, Integer type) {
        this.data = data;
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public Integer getType() {
        return type;
    }
}
