package com.luna.baidu.dto.face;

import com.luna.baidu.dto.location.LocationDO;

/**
 * @Package: com.luna.baidu.dto.face
 * @ClassName: IdCardInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 13:08
 * @Description:
 */
public class UserFaceResultDTO {

    private String     faceToken;

    private String     ctime;

    private LocationDO location;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public LocationDO getLocation() {
        return location;
    }

    public void setLocation(LocationDO location) {
        this.location = location;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "UserFaceResultDTO{" +
            "faceToken='" + faceToken + '\'' +
            ", ctime='" + ctime + '\'' +
            ", location=" + location +
            '}';
    }
}
