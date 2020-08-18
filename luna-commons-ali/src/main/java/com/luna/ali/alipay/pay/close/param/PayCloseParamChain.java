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

package com.luna.ali.alipay.pay.close.param;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.luna.ali.alipay.pay.close.PayCloseChain;

/**
 * ClassName: PayCloseParamChain
 * Description: Document Address https://docs.open.alipay.com/api_1/alipay.trade.close
 * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.close
 * date: 2019/12/25 16:05
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class PayCloseParamChain {
    private AlipayClient          alipayClient;
    private AlipayTradeCloseModel alipayTradeCloseModel;

    public PayCloseParamChain() {}

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public AlipayTradeCloseModel getAlipayTradeCloseModel() {
        return alipayTradeCloseModel;
    }

    public void setAlipayTradeCloseModel(AlipayTradeCloseModel alipayTradeCloseModel) {
        this.alipayTradeCloseModel = alipayTradeCloseModel;
    }

    public PayCloseParamChain(AlipayClient alipayClient, AlipayTradeCloseModel alipayTradeCloseModel) {
        this.alipayClient = alipayClient;
        this.alipayTradeCloseModel = alipayTradeCloseModel;
    }

    /**
     * Builder PayCloseChain
     * <p>
     * 构建 PayCloseChain
     *
     * @return PayCloseChain
     */
    public PayCloseChain builder() {
        return new PayCloseChain(alipayClient, alipayTradeCloseModel);
    }

    /**
     * !!This Is An Indispensable Parameter
     * The Transaction In AliPay System Transaction Code.
     * The Shortest 16 Bits, The Longest 64 Bits.
     * And OutTradeNo Cannot Be Empty At The Same Time.
     * If OutTradeNo And TradeNo Are Passed At The Same Time,
     * TradeNo Shall Prevail.
     * <p>
     * !!这是不可缺参数
     * 该交易在支付宝系统中的交易流水号.最短 16 位,最长 64 位.
     * 和OutTradeNo不能同时为空,
     * 如果同时传了 OutTradeNo和 TradeNo,
     * 则以 TradeNo为准.
     *
     * @param tradeNo tradeNo
     * @return PayCloseParamChain
     */
    public PayCloseParamChain builderTradeNo(String tradeNo) {
        alipayTradeCloseModel.setTradeNo(tradeNo);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * When The Order Is Paid, The Incoming Merchant Order Number And The AliPay Transaction Number Can Not Be Empty At
     * The Same Time.
     * TradeNo, OutTradeNo If Both Exist, Take TradeNo First
     * <p>
     * !!这是不可缺参数
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空.
     * TradeNo,OutTradeNo如果同时存在优先取TradeNo
     *
     * @param outTradeNo outTradeNo
     * @return PayCloseParamChain
     */
    public PayCloseParamChain builderOutTradeNo(String outTradeNo) {
        alipayTradeCloseModel.setOutTradeNo(outTradeNo);
        return this;
    }

    /**
     * Operator ID Customized By The Seller
     * <p>
     * 卖家端自定义的的操作员 ID
     *
     * @param operatorId operatorId
     * @return PayCloseParamChain
     */
    public PayCloseParamChain builderOperatorId(String operatorId) {
        alipayTradeCloseModel.setOperatorId(operatorId);
        return this;
    }

}
