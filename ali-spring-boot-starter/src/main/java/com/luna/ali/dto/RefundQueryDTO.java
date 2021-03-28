package com.luna.ali.dto;

/**
 * @Package: com.luna.ali.dto
 * @ClassName: RefundQueryDTO
 * @Author: luna
 * @CreateTime: 2020/8/18 22:11
 * @Description:
 */
public class RefundQueryDTO {

    /** 订单支付时传入的商户订单号 */
    private String outTradeNo;

    /** 支付宝交易号 */
    private String tradeNo;

    /** 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。 */
    private String outRequestNo;

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

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }
}
