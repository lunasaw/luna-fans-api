package com.luna.common.dto;

import com.luna.common.entity.Location;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: FaceDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 17:05
 * @Description:
 */
public class FaceDTO {

    private String   faceToken;

    private Location location;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
