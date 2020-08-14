package com.luna.baidu.dto.face;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Package: com.luna.baidu.dto.face
 * @ClassName: IdCardAllinfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 13:15
 * @Description:
 */
public class IdCardAllinfoDTO {

    /**
     * 姓名
     */
    @JSONField(name = "姓名")
    private IdCardInfoDTO name;

    /**
     * 身份证号
     */
    @JSONField(name = "公民身份号码")
    private IdCardInfoDTO idNumber;

    /**
     * 地址
     */
    @JSONField(name = "住址")
    private IdCardInfoDTO address;

    /**
     * 民族
     */
    @JSONField(name = "民族")
    private IdCardInfoDTO nation;

    /**
     * 出生年月
     */
    @JSONField(name = "出生")
    private IdCardInfoDTO birth;

    /**
     * 性别
     */
    @JSONField(name = "性别")
    private IdCardInfoDTO gender;

    public IdCardInfoDTO getName() {
        return name;
    }

    public void setName(IdCardInfoDTO name) {
        this.name = name;
    }

    public IdCardInfoDTO getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(IdCardInfoDTO idNumber) {
        this.idNumber = idNumber;
    }

    public IdCardInfoDTO getAddress() {
        return address;
    }

    public void setAddress(IdCardInfoDTO address) {
        this.address = address;
    }

    public IdCardInfoDTO getNation() {
        return nation;
    }

    public void setNation(IdCardInfoDTO nation) {
        this.nation = nation;
    }

    public IdCardInfoDTO getBirth() {
        return birth;
    }

    public void setBirth(IdCardInfoDTO birth) {
        this.birth = birth;
    }

    public IdCardInfoDTO getGender() {
        return gender;
    }

    public void setGender(IdCardInfoDTO gender) {
        this.gender = gender;
    }
}
