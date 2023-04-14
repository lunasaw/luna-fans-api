package com.luna.baidu.enums.map;

/**
 * @author luna
 */

public enum CoordinateType {
    WGS84("wgs84", 1),
    BD09LL("bd09ll", 2),
    BD09MC("bd09mc", 3),
    GCJ02("gcj02", 4);

    private final String  data;
    private final Integer type;

    CoordinateType(String data, Integer type) {
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
