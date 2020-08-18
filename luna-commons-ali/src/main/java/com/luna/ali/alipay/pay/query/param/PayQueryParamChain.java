/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.luna.ali.alipay.pay.query.param;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.luna.ali.alipay.pay.query.PayQueryChain;

import java.util.List;

/**
 * ClassName: PayQueryParamChain
 * Description: Document Address https://docs.open.alipay.com/api_1/alipay.trade.query
 * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.query
 * date: 2019/12/25 16:39
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */

public class PayQueryParamChain {
    private AlipayClient          alipayClient;
    private AlipayTradeQueryModel alipayTradeQueryModel;

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public AlipayTradeQueryModel getAlipayTradeQueryModel() {
        return alipayTradeQueryModel;
    }

    public void setAlipayTradeQueryModel(AlipayTradeQueryModel alipayTradeQueryModel) {
        this.alipayTradeQueryModel = alipayTradeQueryModel;
    }

    public PayQueryParamChain(AlipayClient alipayClient, AlipayTradeQueryModel alipayTradeQueryModel) {
        this.alipayClient = alipayClient;
        this.alipayTradeQueryModel = alipayTradeQueryModel;
    }

    /**
     * Builder PayQueryChain
     * <p>
     * 构建 PayQueryChain
     *
     * @return PayQueryChain
     */
    public PayQueryChain builder() {
        return new PayQueryChain(alipayClient, alipayTradeQueryModel);
    }

    /**
     * !!This Is An Indispensable Parameter
     * When The Order Is Paid, The Incoming Merchant Order Number And The AliPay Transaction Number Can Not Be Empty At
     * The Same Time.
     * TradeNo, OutTradeNo If There Is TradeNo Priority At The Same Time
     * <p>
     * !!这是不可缺参数
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空.
     * TradeNo,OutTradeNo如果同时存在优先取TradeNo
     *
     * @param outTradeNo outTradeNo
     * @return PayQueryParamChain
     */
    public PayQueryParamChain builderOutTradeNo(String outTradeNo) {
        alipayTradeQueryModel.setOutTradeNo(outTradeNo);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * AliPay Transaction Number And Merchant Order Number Can Not Be Empty At The Same Time.
     * <p>
     * !!这是不可缺参数
     * 支付宝交易号,和商户订单号不能同时为空
     *
     * @param tradeNo tradeNo
     * @return PayQueryParamChain
     */
    public PayQueryParamChain builderTradeNo(String tradeNo) {
        alipayTradeQueryModel.setTradeNo(tradeNo);
        return this;
    }

    /**
     * It Is Useful In The Bank To Bank Mode. Please Do Not Use It In Other Scenarios;
     * Through This Parameter, The Double Link Specifies The PID Of The Acquirer Of The Exchange To Be Queried;
     * <p>
     * 银行间联模式下有用,其它场景请不要使用:
     * 双联通过该参数指定需要查询的交易所属收单机构的PID;
     *
     * @param orgPid orgPid
     * @return PayQueryParamChain
     */
    public PayQueryParamChain builderOrgPid(String orgPid) {
        alipayTradeQueryModel.setOrgPid(orgPid);
        return this;
    }

    /**
     * Query Option, The Merchant Customizes The Query Return Information By Submitting This Field
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.query
     * <p>
     * 查询选项,商户通过上送该字段来定制查询返回信息
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.query
     *
     * @param queryOptions queryOptions
     * @return PayQueryParamChain
     */
    public PayQueryParamChain builderQueryOptions(List<String> queryOptions) {
        alipayTradeQueryModel.setQueryOptions(queryOptions);
        return this;
    }

}
