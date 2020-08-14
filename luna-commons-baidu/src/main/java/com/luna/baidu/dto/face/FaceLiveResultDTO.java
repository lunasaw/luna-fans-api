package com.luna.baidu.dto.face;

import com.luna.common.dto.FaceDTO;

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
    private List<FaceDTO> faceList;

    public Float getFaceLiveness() {
        return faceLiveness;
    }

    public void setFaceLiveness(Float faceLiveness) {
        this.faceLiveness = faceLiveness;
    }

    public List<FaceDTO> getFaceList() {
        return faceList;
    }

    public void setFaceList(List<FaceDTO> faceList) {
        this.faceList = faceList;
    }

}
