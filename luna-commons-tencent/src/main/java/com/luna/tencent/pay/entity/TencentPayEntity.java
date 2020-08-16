package com.luna.tencent.pay.entity;

/**
 * @Package: com.luna.tencent.pay.entity
 * @ClassName: PayEntity
 * @Author: luna
 * @CreateTime: 2020/8/16 14:23
 * @Description:
 */
public class TencentPayEntity {
    /** 商品描述 */
    private String body;

    /** 商户订单号 */
    private String outTradeNo;

    /** 交易金额 单位是分 */
    private String totalFee;

    /** 终端的IP */
    private String spbillCreateIp;

    /** 扫码支付类型 */
    private String tradeType = "NATIVE";

    public TencentPayEntity() {}

    public TencentPayEntity(String body, String outTradeNo, String totalFee, String spbillCreateIp,
        String tradeType) {
        this.body = body;
        this.outTradeNo = outTradeNo;
        this.totalFee = totalFee;
        this.spbillCreateIp = spbillCreateIp;
        this.tradeType = tradeType;
    }

    public TencentPayEntity(String body, String outTradeNo, String totalFee, String spbillCreateIp) {
        this.body = body;
        this.outTradeNo = outTradeNo;
        this.totalFee = totalFee;
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
