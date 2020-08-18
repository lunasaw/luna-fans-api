package com.luna.ali.dto;

/**
 * @Package: com.luna.ali.dto
 * @ClassName: CloseOrderDTO
 * @Author: luna
 * @CreateTime: 2020/8/18 21:21
 * @Description:
 */
public class CloseOrderDTO {

    /** 订单支付时传入的商户订单号 */
    private String outTradeNo;

    /** 支付宝交易号 */
    private String tradeNo;

    /** 卖家端自定义的的操作员 ID */
    private String operatorId;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}
