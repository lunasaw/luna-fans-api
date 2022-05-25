package com.luna.baidu.dto.word;
import com.luna.baidu.dto.location.LocationDO;

/**
 * author: luna
 * 2020/8/10 17:05
 */
public class FaceDTO {

    private String     faceToken;

    private LocationDO locationDO;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public LocationDO getLocation() {
        return locationDO;
    }

    public void setLocation(LocationDO locationDO) {
        this.locationDO = locationDO;
    }
}
