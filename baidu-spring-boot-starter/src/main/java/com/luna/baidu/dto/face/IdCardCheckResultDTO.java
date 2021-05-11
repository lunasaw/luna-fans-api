package com.luna.baidu.dto.face;

import com.luna.baidu.dto.location.LocationDO;

/**
 * @Package: com.luna.baidu.dto.face
 * @ClassName: IdCardCheckResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 13:02
 * @Description:
 */
public class IdCardCheckResultDTO {

    /**
     * 身份证信息
     */
    private IdCardAllInfoDTO wordsResult;

    /**
     * 头像位置base64编码
     */
    private String           photo;

    /**
     * 头像位置
     */
    private LocationDO       photoLocationDO;

    /**
     * 身份证类型
     *
     * 输入参数 detect_risk = true 时，则返回该字段识别身份证类型:
     * normal-正常身份证；copy-复印件；temporary-临时身份证；screen-翻拍；unknown-其他未知情况
     */
    private String           riskType;

    /**
     * 如果参数 detect_risk = true 时，则返回此字段。如果检测身份证被编辑过，该字段指定编辑软件名称，
     * 如:Adobe Photoshop CC 2014 (Macintosh),如果没有被编辑过则返回值无此参数
     */
    private String           editTool;

    /**
     *
     * 用于校验身份证号码、性别、出生是否一致，输出结果及其对应关系如下：
     * -1: 身份证正面所有字段全为空
     * 0: 身份证证号识别错误
     * 1: 身份证证号和性别、出生信息一致
     * 2: 身份证证号和性别、出生信息都不一致
     * 3: 身份证证号和出生信息不一致
     * 4: 身份证证号和性别信息不一致
     *
     */
    private String           idcardNumberType;

    public IdCardAllInfoDTO getWordsResult() {
        return wordsResult;
    }

    public void setWordsResult(IdCardAllInfoDTO wordsResult) {
        this.wordsResult = wordsResult;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocationDO getPhotoLocation() {
        return photoLocationDO;
    }

    public void setPhotoLocation(LocationDO photoLocationDO) {
        this.photoLocationDO = photoLocationDO;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getEditTool() {
        return editTool;
    }

    public void setEditTool(String editTool) {
        this.editTool = editTool;
    }

    public String getIdcardNumberType() {
        return idcardNumberType;
    }

    public void setIdcardNumberType(String idcardNumberType) {
        this.idcardNumberType = idcardNumberType;
    }
}
