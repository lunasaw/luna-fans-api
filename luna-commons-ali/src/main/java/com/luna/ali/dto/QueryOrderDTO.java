package com.luna.ali.dto;

import java.util.List;

/**
 * @Package: com.luna.ali.entity
 * @ClassName: QueryOrderDTO
 * @Author: luna
 * @CreateTime: 2020/8/18 16:42
 * @Description:
 */
public class QueryOrderDTO {

    /** 订单支付时传入的商户订单号 */
    private String       outTradeNo;

    /** 支付宝交易号 */
    private String       tradeNo;

    /** 查询选项，商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式 */
    private List<String> queryOptions;

    public List<String> getQueryOptions() {
        return queryOptions;
    }

    public void setQueryOptions(List<String> queryOptions) {
        this.queryOptions = queryOptions;
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
}
