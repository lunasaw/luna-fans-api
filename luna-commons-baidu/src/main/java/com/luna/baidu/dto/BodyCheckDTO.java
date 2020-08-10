package com.luna.baidu.dto;

import com.luna.common.entity.Location;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: BodyCheckDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 19:33
 * @Description:
 */
public class BodyCheckDTO {

    private BodyAttributesDTO attributes;

    private Location          location;

    public BodyAttributesDTO getAttributes() {
        return attributes;
    }

    public void setAttributes(BodyAttributesDTO attributes) {
        this.attributes = attributes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
