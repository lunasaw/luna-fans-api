package com.luna.baidu.dto.face;

import com.luna.baidu.dto.word.FaceDTO;

import java.util.List;

/**
 * @Package: com.luna.baidu.dto.face
 * @ClassName: FaceMatchResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 11:08
 * @Description:
 */
public class FaceMatchResultDTO {

    /**
     * 匹配分数
     */
    private Double        score;

    /**
     * faceToken
     */
    private List<FaceDTO> faceList;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<FaceDTO> getFaceList() {
        return faceList;
    }

    public void setFaceList(List<FaceDTO> faceList) {
        this.faceList = faceList;
    }
}
