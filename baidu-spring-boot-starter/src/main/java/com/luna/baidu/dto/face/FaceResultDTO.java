package com.luna.baidu.dto.face;

import com.luna.baidu.dto.location.LocationDO;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: FaceDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 17:05
 * @Description:
 */
public class FaceResultDTO {

    private String     faceToken;

    private LocationDO locationDO;

    private LiveNess   liveness;

    private String     spoofing;

    private Double     age;

    private Integer    beauty;

    private String     faceProbability;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public LocationDO getLocationDO() {
        return locationDO;
    }

    public void setLocationDO(LocationDO locationDO) {
        this.locationDO = locationDO;
    }

    public LiveNess getLiveness() {
        return liveness;
    }

    public void setLiveness(LiveNess liveness) {
        this.liveness = liveness;
    }

    public String getSpoofing() {
        return spoofing;
    }

    public void setSpoofing(String spoofing) {
        this.spoofing = spoofing;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Integer getBeauty() {
        return beauty;
    }

    public void setBeauty(Integer beauty) {
        this.beauty = beauty;
    }

    public String getFaceProbability() {
        return faceProbability;
    }

    public void setFaceProbability(String faceProbability) {
        this.faceProbability = faceProbability;
    }
}

class LiveNess {
    private String livemapscore;

    public String getLivemapscore() {
        return livemapscore;
    }

    public void setLivemapscore(String livemapscore) {
        this.livemapscore = livemapscore;
    }
}