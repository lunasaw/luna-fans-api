package io.github.lunasaw.alipay.dto;

/**
 * @Package: com.luna.ali.entity
 * @ClassName: QueryOrderResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/18 20:22
 * @Description:
 */
public class QueryOrderResultDTO {

    /** 支付宝交易号 */
    private String tradeNo;

    /** 商家订单号 */
    private String outTradeNo;

    /** 买家支付宝账号 */
    private String buyerLogonId;

    /**
     * 交易状态
     * WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
     */
    private String tradeStatus;

    /** 交易的订单金额，单位为元，两位小数 该参数的值为支付时传入的total_amount */
    private String totalAmount;

    /** 买家用户类型。CORPORATE:企业用户；PRIVATE:个人用户。 */
    private String buyerUserType;

    /** 买家实付金额，单位为元，两位小数。该金额代表该笔交易买家实际支付的金额，不包含商户折扣等金额 */
    private String buyerPayAmount;

    /** 交易中用户支付的可开具发票的金额，单位为元，两位小数。该金额代表该笔交易中可以给用户开具发票的金额 */
    private String invoiceAmount;

    /** 实收金额，单位为元，两位小数。该金额为本笔交易，商户账户能够实际收到的金额 */
    private String receiptAmount;

    /** 积分支付的金额，单位为元，两位小数。该金额代表该笔交易中用户使用积分支付的金额，比如集分宝或者支付宝实时优惠等 */
    private String pointAmount;

    public String getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(String buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public String getBuyerUserType() {
        return buyerUserType;
    }

    public void setBuyerUserType(String buyerUserType) {
        this.buyerUserType = buyerUserType;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(String pointAmount) {
        this.pointAmount = pointAmount;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
