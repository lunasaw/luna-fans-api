package com.luna.common.dto;

import com.luna.common.entity.Location;

/**
 * @Package: com.luna.common.dto
 * @ClassName: WordDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 17:25
 * @Description:
 */
public class WordDTO {

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
