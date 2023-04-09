package io.github.lunasaw.alipay.dto;

/**
 * @Package: com.luna.ali.dto
 * @ClassName: QueryBillDTO
 * @Author: luna
 * @CreateTime: 2020/8/18 22:16
 * @Description:
 */
public class QueryBillDTO {

    /**
     * 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
     * trade指商户基于支付宝交易收单的业务账单；
     * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单。
     */
    private String billType;

    /** 账单时间：日账单格式为yyyy-MM-dd，最早可下载2016年1月1日开始的日账单；月账单格式为yyyy-MM，最早可下载2016年1月开始的月账单。 */
    private String billDate;

    public QueryBillDTO(String billType, String billDate) {
        this.billType = billType;
        this.billDate = billDate;
    }

    public QueryBillDTO() {}

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
