package com.luna.tencent.pay.dto;

/**
 * @Package; com.luna.tencent.pay.dto
 * @ClassName; QueryResultDTO
 * @Author; luna
 * @CreateTime; 2020/8/16 15;15
 * @Description;
 */
public class QueryResultDTO {

    /** 设备号 */
    private String deviceInfo;
    /** 订单号 */
    private String outTradeNo;
    /**
     * SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 REVOKED—已撤销（付款码支付） USERPAYING--用户支付中（付款码支付）
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private String tradeState;
    /** 对当前查询订单状态的描述和下一步操作的指引 */
    private String tradeStateDesc;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }
}
