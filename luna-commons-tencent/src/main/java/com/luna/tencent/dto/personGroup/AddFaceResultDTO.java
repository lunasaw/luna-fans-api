package com.luna.tencent.dto.personGroup;

import com.luna.tencent.dto.ErrorDTO;
import com.luna.tencent.dto.face.FaceInfosDTO;

/**
 * @Package: com.luna.tencent.dto.face
 * @ClassName: AddFaceResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 20:37
 * @Description:
 */
public class AddFaceResultDTO {

    private FaceInfosDTO faceRect;

    private String       faceId;

    private String       similarPersonId;

    private ErrorDTO     Error;

    public ErrorDTO getError() {
        return Error;
    }

    public void setError(ErrorDTO error) {
        Error = error;
    }

    public FaceInfosDTO getFaceRect() {
        return faceRect;
    }

    public void setFaceRect(FaceInfosDTO faceRect) {
        this.faceRect = faceRect;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getSimilarPersonId() {
        return similarPersonId;
    }

    public void setSimilarPersonId(String similarPersonId) {
        this.similarPersonId = similarPersonId;
    }
}
