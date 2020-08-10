package com.luna.baidu.dto;

import com.luna.common.dto.FaceDTO;

import java.util.List;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: FaceCheckDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 17:01
 * @Description:
 */
public class FaceCheckDTO {

    private String        faceNum;

    private List<FaceDTO> faceList;

    public String getFaceNum() {
        return faceNum;
    }

    public void setFaceNum(String faceNum) {
        this.faceNum = faceNum;
    }

    public List<FaceDTO> getFaceList() {
        return faceList;
    }

    public void setFaceList(List<FaceDTO> faceList) {
        this.faceList = faceList;
    }
}
