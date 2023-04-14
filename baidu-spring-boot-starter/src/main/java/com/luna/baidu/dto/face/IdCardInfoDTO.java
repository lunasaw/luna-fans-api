package com.luna.baidu.dto.face;

import com.luna.baidu.dto.location.LocationDO;

/**
 * @Package: com.luna.baidu.dto.face
 * @ClassName: IdCardInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 13:08
 * @Description:
 */
public class IdCardInfoDTO {

    private String     words;

    private LocationDO locationDO;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public LocationDO getLocation() {
        return locationDO;
    }

    public void setLocation(LocationDO locationDO) {
        this.locationDO = locationDO;
    }
}
