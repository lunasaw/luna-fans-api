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

package io.github.lunasaw.alipay.pay.refund.query.param;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import io.github.lunasaw.alipay.pay.refund.query.PayRefundQueryChain;

/**
 * ClassName: PayRefundQueryParamChain
 * Description: Document Address https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query
 * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query
 * date: 2019/12/25 18:27
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */

public class PayRefundQueryParamChain {
    private AlipayClient                       alipayClient;
    private AlipayTradeFastpayRefundQueryModel alipayTradeFastpayRefundQueryModel;

    public PayRefundQueryParamChain() {}

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public AlipayTradeFastpayRefundQueryModel getAlipayTradeFastpayRefundQueryModel() {
        return alipayTradeFastpayRefundQueryModel;
    }

    public void
        setAlipayTradeFastpayRefundQueryModel(AlipayTradeFastpayRefundQueryModel alipayTradeFastpayRefundQueryModel) {
        this.alipayTradeFastpayRefundQueryModel = alipayTradeFastpayRefundQueryModel;
    }

    public PayRefundQueryParamChain(AlipayClient alipayClient,
        AlipayTradeFastpayRefundQueryModel alipayTradeFastpayRefundQueryModel) {
        this.alipayClient = alipayClient;
        this.alipayTradeFastpayRefundQueryModel = alipayTradeFastpayRefundQueryModel;
    }

    /**
     * Builder PayRefundChain
     * <p>
     * 构建 PayRefundChain
     *
     * @return PayRefundQueryChain
     */
    public PayRefundQueryChain builder() {
        return new PayRefundQueryChain(alipayClient, alipayTradeFastpayRefundQueryModel);
    }

    /**
     * !!This Is An Indispensable Parameter
     * AliPay Transaction Number And Merchant Order Number Can Not Be Empty At The Same Time.
     * <p>
     * !!这是不可缺参数
     * 支付宝交易号,和商户订单号不能同时为空
     *
     * @param tradeNo tradeNo
     * @return PayRefundQueryParamChain
     */
    public PayRefundQueryParamChain builderTradeNo(String tradeNo) {
        alipayTradeFastpayRefundQueryModel.setTradeNo(tradeNo);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * When The Order Is Paid, The Incoming Merchant Order Number And The AliPay Transaction Number Can Not Be Empty At
     * The Same Time.
     * OutTradeNo, OutRequestNo If There Is A Priority OutTradeNo At The Same Time
     * <p>
     * !!这是不可缺参数
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空.
     * OutTradeNo,OutRequestNo如果同时存在优先取OutTradeNo
     *
     * @param outTradeNo outTradeNo
     * @return PayRefundQueryParamChain
     */
    public PayRefundQueryParamChain builderOutTradeNo(String outTradeNo) {
        alipayTradeFastpayRefundQueryModel.setOutTradeNo(outTradeNo);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * When Requesting The Refund Interface, The Incoming Refund Request Number.
     * If It Is Not Passed In At The Time Of Refund Request,
     * The Value Is The External Transaction Number At The Time Of Transaction Creation
     * <p>
     * !!这是不可缺参数
     * 请求退款接口时,传入的退款请求号,
     * 如果在退款请求时未传入,则该值为创建交易时的外部交易号
     *
     * @param outRequestNo outRequestNo
     * @return PayRefundQueryParamChain
     */
    public PayRefundQueryParamChain builderOutRequestNo(String outRequestNo) {
        alipayTradeFastpayRefundQueryModel.setOutRequestNo(outRequestNo);
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
     * @return PayRefundQueryParamChain
     */
    public PayRefundQueryParamChain builderOrgPid(String orgPid) {
        alipayTradeFastpayRefundQueryModel.setOrgPid(orgPid);
        return this;
    }
}
