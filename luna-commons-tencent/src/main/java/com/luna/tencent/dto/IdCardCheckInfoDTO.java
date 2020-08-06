package com.luna.tencent.dto;

/**
 * @Package: com.luna.common.entity
 * @ClassName: MobileCheckInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/6 14:02
 * @Description:
 */
public class IdCardCheckInfoDTO {

    /**
     * 收费结果码：
     * 0: 姓名和身份证号一致
     * -1: 姓名和身份证号不一致
     * 不收费结果码：
     * -2: 非法身份证号（长度、校验位等不正确）
     * -3: 非法姓名（长度、格式等不正确）
     * -4: 证件库服务异常
     * -5: 证件库中无此身份证记录
     */
    private String Result;

    /** 业务结果描述。 */
    private String Description;

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
