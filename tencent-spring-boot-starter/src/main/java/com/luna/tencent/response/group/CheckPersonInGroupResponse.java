package com.luna.tencent.response.group;

import java.util.List;

/**
 * @author luna
 * 2021/6/14
 */
public class CheckPersonInGroupResponse {

    private String                         faceModelVersion;

    private Integer                        faceNum;

    private List<ResultsReturnsByGroupResponse> resultsReturnsByGroup;

    public String getFaceModelVersion() {
        return faceModelVersion;
    }

    public void setFaceModelVersion(String faceModelVersion) {
        this.faceModelVersion = faceModelVersion;
    }

    public Integer getFaceNum() {
        return faceNum;
    }

    public void setFaceNum(Integer faceNum) {
        this.faceNum = faceNum;
    }

    public List<ResultsReturnsByGroupResponse> getResultsReturnsByGroup() {
        return resultsReturnsByGroup;
    }

    public void setResultsReturnsByGroup(List<ResultsReturnsByGroupResponse> resultsReturnsByGroup) {
        this.resultsReturnsByGroup = resultsReturnsByGroup;
    }
}
