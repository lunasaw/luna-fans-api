package com.luna.baidu.dto.body;

import com.luna.baidu.dto.location.LocationDO;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: BodyCheckDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 19:33
 * @Description:
 */
public class BodyCheckDTO {

    private BodyAttributesDTO attributes;

    private LocationDO        locationDO;

    public BodyAttributesDTO getAttributes() {
        return attributes;
    }

    public void setAttributes(BodyAttributesDTO attributes) {
        this.attributes = attributes;
    }

    public LocationDO getLocation() {
        return locationDO;
    }

    public void setLocation(LocationDO locationDO) {
        this.locationDO = locationDO;
    }
}
