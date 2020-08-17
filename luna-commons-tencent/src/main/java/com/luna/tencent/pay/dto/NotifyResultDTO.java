package com.luna.tencent.pay.dto;

/**
 * @Package;com.luna.tencent.pay.dto
 * @ClassName;NotifyResultDTO
 * @Author;luna
 * @CreateTime;2020/8/16 16:57
 * @Description:
 */
public class NotifyResultDTO {

    /** 微信支付订单号 */
    private String transactionId;
    /** 付款银行 */
    private String bankType;
    /** 用户标识 */
    private String openid;
    /** 签名 */
    private String sign;
    /** 支付币种 */
    private String feeType;
    /** 现金支付金额 单位分 */
    private String cashFee;
    /** 订单号 */
    private String outTradeNo;
    /** 订单金额 */
    private String totalFee;
    /** 交易类型 JSAPI、NATIVE、APP */
    private String tradeType;
    /** 支付结果 */
    private String resultCode;
    /** 支付完成时间 */
    private String timeEnd;
    /** 是否关注公众号 */
    private String isSubscribe;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getCashFee() {
        return cashFee;
    }

    public void setCashFee(String cashFee) {
        this.cashFee = cashFee;
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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }
}
