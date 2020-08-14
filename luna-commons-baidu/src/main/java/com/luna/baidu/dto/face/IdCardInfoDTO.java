package com.luna.baidu.dto.face;

import com.luna.common.entity.Location;

/**
 * @Package: com.luna.baidu.dto.face
 * @ClassName: IdCardInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 13:08
 * @Description:
 */
public class IdCardInfoDTO {

    private String   words;

    private Location location;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
