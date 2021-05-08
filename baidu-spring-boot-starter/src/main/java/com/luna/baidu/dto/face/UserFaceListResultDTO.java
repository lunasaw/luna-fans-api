package com.luna.baidu.dto.face;

import java.util.List;

/**
 * @author luna@mac
 * 2021年05月07日 21:00
 */
public class UserFaceListResultDTO {

    private List<UserFaceResultDTO> faceList;

    public List<UserFaceResultDTO> getFaceList() {
        return faceList;
    }

    public void setFaceList(List<UserFaceResultDTO> faceList) {
        this.faceList = faceList;
    }
}
