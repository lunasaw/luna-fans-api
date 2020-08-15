package com.luna.tencent.dto.card;

/**
 * @Package: com.luna.common.dto
 * @ClassName: MobileCheckInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/6 14:02
 * @Description:
 */
public class IdCardAndBankCardCheckInfoDTO {

    /**
     * 认证结果码
     * 收费结果码：
     * '0': '认证通过'
     * '-1': '认证未通过'
     * '-5': '持卡人信息有误'
     * '-6': '未开通无卡支付'
     * '-7': '此卡被没收'
     * '-8': '无效卡号'
     * '-9': '此卡无对应发卡行'
     * '-10': '该卡未初始化或睡眠卡'
     * '-11': '作弊卡、吞卡'
     * '-12': '此卡已挂失'
     * '-13': '该卡已过期'
     * '-14': '受限制的卡'
     * '-15': '密码错误次数超限'
     * '-16': '发卡行不支持此交易'
     * 不收费结果码：
     * '-2': '姓名校验不通过'
     * '-3': '身份证号码有误'
     * '-4': '银行卡号码有误'
     * '-17': '验证中心服务繁忙'
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
