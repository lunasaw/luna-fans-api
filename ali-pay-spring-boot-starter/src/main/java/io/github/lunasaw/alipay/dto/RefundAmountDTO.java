package io.github.lunasaw.alipay.dto;

/**
 * @Package: com.luna.ali.dto
 * @ClassName: RefundAmountDTO
 * @Author: luna
 * @CreateTime: 2020/8/18 21:37
 * @Description:
 */
public class RefundAmountDTO {

    /** 订单支付时传入的商户订单号 */
    private String outTradeNo;

    /** 支付宝交易号 */
    private String tradeNo;

    /** 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数。 */
    private String refundAmount;

    /** 退款原因 */
    private String refundReason;

    /** 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。 */
    private String outRequestNo;

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

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

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }
}
