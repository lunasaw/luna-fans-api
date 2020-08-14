package com.luna.tencent.dto.personGroup;

import java.util.List;

/**
 * @Package: com.luna.tencent.dto.personGroup
 * @ClassName: CheckPersonInGroupResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 23:17
 * @Description:
 */
public class CheckPersonInGroupResultDTO {

    private String                         faceModelVersion;

    private Integer                        faceNum;

    private List<ResultsReturnsByGroupDTO> resultsReturnsByGroup;

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

    public List<ResultsReturnsByGroupDTO> getResultsReturnsByGroup() {
        return resultsReturnsByGroup;
    }

    public void setResultsReturnsByGroup(List<ResultsReturnsByGroupDTO> resultsReturnsByGroup) {
        this.resultsReturnsByGroup = resultsReturnsByGroup;
    }
}
