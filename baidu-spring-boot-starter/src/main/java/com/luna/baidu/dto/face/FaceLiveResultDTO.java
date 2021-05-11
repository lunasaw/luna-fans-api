package com.luna.baidu.dto.face;

import com.luna.baidu.dto.word.FaceDTO;

import java.util.List;

/**
 * @Package: com.luna.baidu.dto.face
 * @ClassName: FaceLiveResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 11:26
 * @Description:
 */
public class FaceLiveResultDTO {

    /**
     * 活体分数
     */
    private Float         faceLiveness;

    /**
     * 脸部信息
     */
    private List<FaceResultDTO> faceList;

    public Float getFaceLiveness() {
        return faceLiveness;
    }

    public void setFaceLiveness(Float faceLiveness) {
        this.faceLiveness = faceLiveness;
    }

    public List<FaceResultDTO> getFaceList() {
        return faceList;
    }

    public void setFaceList(List<FaceResultDTO> faceList) {
        this.faceList = faceList;
    }
}
