package com.luna.tencent.response.group;

import com.luna.tencent.dto.error.ErrorDTO;
import com.luna.tencent.dto.face.FaceInfosDTO;

/**
 * @author luna
 * 2021/6/14
 */
public class AddFaceResultResponse {

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
