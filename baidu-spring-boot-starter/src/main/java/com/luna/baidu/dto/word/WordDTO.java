package com.luna.baidu.dto.word;

import com.luna.baidu.dto.location.LocationDO;

/**
 * @Package: com.luna.common.dto
 * @ClassName: WordDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 17:25
 * @Description:
 */
public class WordDTO {

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
