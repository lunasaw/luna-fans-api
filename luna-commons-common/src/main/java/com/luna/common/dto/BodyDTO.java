package com.luna.common.dto;

import com.luna.common.entity.Location;

/**
 * @Package: com.luna.common.dto
 * @ClassName: BodyDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 17:26
 * @Description:
 */
public class BodyDTO {

    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
