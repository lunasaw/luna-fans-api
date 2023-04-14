package com.luna.baidu.dto.word;

import com.luna.baidu.dto.location.LocationDO;

/**
 * @Package: com.luna.common.dto
 * @ClassName: BodyDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 17:26
 * @Description:
 */
public class BodyDTO {

    private LocationDO locationDO;

    public LocationDO getLocation() {
        return locationDO;
    }

    public void setLocation(LocationDO locationDO) {
        this.locationDO = locationDO;
    }
}
