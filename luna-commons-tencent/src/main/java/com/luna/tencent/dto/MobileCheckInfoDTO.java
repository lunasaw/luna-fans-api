package com.luna.tencent.dto;

/**
 * @Package: com.luna.common.entity
 * @ClassName: MobileCheckInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/6 14:02
 * @Description:
 */
public class MobileCheckInfoDTO {

    /**
     * 收费结果码：
     * 0: 成功
     * -2: 手机号不存在
     * -3: 手机号存在，但无法查询到在网时长
     * 不收费结果码：
     * -1: 手机号格式不正确
     * -4: 验证中心服务繁忙
     *
     */
    private String Result;

    /** 业务结果描述。 */
    private String Description;

    private String Range;

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

    public String getRange() {
        return Range;
    }

    public void setRange(String range) {
        Range = range;
    }
}
