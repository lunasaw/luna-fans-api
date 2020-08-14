package com.luna.tencent.dto.personGroup;

import com.luna.tencent.dto.ErrorDTO;

import java.util.List;

/**
 * @Package: com.luna.tencent.dto.personGroup
 * @ClassName: PersonBaseInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 22:56
 * @Description:
 */
public class PersonBaseInfoResultDTO {

    /**
     * 人员名称
     */
    private String       personName;

    /**
     * 人员性别，0代表未填写，1代表男性，2代表女性
     */
    private Integer      gender;

    /**
     * 包含的人脸 ID 列表
     */
    private List<String> faceIds;

    /**
     * 错误信息
     */
    private ErrorDTO     error;

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public List<String> getFaceIds() {
        return faceIds;
    }

    public void setFaceIds(List<String> faceIds) {
        this.faceIds = faceIds;
    }
}
