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

package io.github.lunasaw.alipay.pay.refund.param;

import java.util.List;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.domain.OpenApiRoyaltyDetailInfoPojo;
import io.github.lunasaw.alipay.pay.refund.PayRefundChain;

/**
 * ClassName: PayRefundParamChain
 * Description: Document Address https://docs.open.alipay.com/api_1/alipay.trade.refund
 * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.refund
 * date: 2019/12/25 17:33
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */

public class PayRefundParamChain {
    private AlipayClient           alipayClient;
    private AlipayTradeRefundModel alipayTradeRefundModel;

    public PayRefundParamChain() {}

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public AlipayTradeRefundModel getAlipayTradeRefundModel() {
        return alipayTradeRefundModel;
    }

    public void setAlipayTradeRefundModel(AlipayTradeRefundModel alipayTradeRefundModel) {
        this.alipayTradeRefundModel = alipayTradeRefundModel;
    }

    public PayRefundParamChain(AlipayClient alipayClient, AlipayTradeRefundModel alipayTradeRefundModel) {
        this.alipayClient = alipayClient;
        this.alipayTradeRefundModel = alipayTradeRefundModel;
    }

    /**
     * Builder PayRefundChain
     * <p>
     * 构建 PayRefundChain
     *
     * @return PayRefundChain
     */
    public PayRefundChain builder() {
        return new PayRefundChain(alipayClient, alipayTradeRefundModel);
    }

    /**
     * !!This Is An Indispensable Parameter
     * The Merchant Order Number Passed In During Order Payment Cannot Be Empty At The Same Time As TradeNo
     * <p>
     * !!这是不可缺参数
     * 订单支付时传入的商户订单号,不能和 tradeNo同时为空.
     *
     * @param outTradeNo outTradeNo
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderOutTradeNo(String outTradeNo) {
        alipayTradeRefundModel.setOutTradeNo(outTradeNo);
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
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderTradeNo(String tradeNo) {
        alipayTradeRefundModel.setTradeNo(tradeNo);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * The Amount To Be Refunded Cannot Be Greater Than The Order Amount.
     * The Unit Is Yuan And Two Decimal Places Are Supported
     * <p>
     * !!这是不可缺参数
     * 需要退款的金额,该金额不能大于订单金额,单位为元,支持两位小数
     *
     * @param refundAmount refundAmount
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderRefundAmount(String refundAmount) {
        alipayTradeRefundModel.setRefundAmount(refundAmount);
        return this;
    }

    /**
     * Order refund currency information
     * <p>
     * 订单退款币种信息
     *
     * @param refundCurrency refundCurrency
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderRefundCurrency(String refundCurrency) {
        alipayTradeRefundModel.setRefundCurrency(refundCurrency);
        return this;
    }

    /**
     * Reason for refund
     * <p>
     * 退款的原因说明
     *
     * @param refundReason refundReason
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderRefundReason(String refundReason) {
        alipayTradeRefundModel.setRefundReason(refundReason);
        return this;
    }

    /**
     * Identify A Refund Request.
     * Multiple Refunds Of The Same Transaction Need To Be Unique.
     * If Partial Refund Is Required, This Parameter Must Be Passed.
     * <p>
     * 标识一次退款请求,同一笔交易多次退款需要保证唯一,如需部分退款,则此参数必传.
     *
     * @param outRequestNo outRequestNo
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderOutRequestNo(String outRequestNo) {
        alipayTradeRefundModel.setOutRequestNo(outRequestNo);
        return this;
    }

    /**
     * Merchant's Operator Number
     * <p>
     * 商户的操作员编号
     *
     * @param operatorId operatorId
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderOperatorId(String operatorId) {
        alipayTradeRefundModel.setOperatorId(operatorId);
        return this;
    }

    /**
     * Merchant's Store Number
     * <p>
     * 商户的门店编号
     *
     * @param storeId storeId
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderStoreId(String storeId) {
        alipayTradeRefundModel.setStoreId(storeId);
        return this;
    }

    /**
     * Merchant's Terminal Number
     * <p>
     * 商户的终端编号
     *
     * @param terminalId terminalId
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderTerminalId(String terminalId) {
        alipayTradeRefundModel.setTerminalId(terminalId);
        return this;
    }

    /**
     * Item List Information Included In Refund, JSON Format.
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.refund
     * <p>
     * 退款包含的商品列表信息,JSON格式.
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.refund
     *
     * @param goodsDetail goodsDetail
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderGoodsDetail(List<GoodsDetail> goodsDetail) {
        alipayTradeRefundModel.setGoodsDetail(goodsDetail);
        return this;
    }

    /**
     * Return Sub Ledger Details
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.refund
     * <p>
     * 退分账明细信息
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.refund
     *
     * @param refundRoyaltyParameters refundRoyaltyParameters
     * @return PayRefundParamChain
     */
    public PayRefundParamChain
        builderRefundRoyaltyParameters(List<OpenApiRoyaltyDetailInfoPojo> refundRoyaltyParameters) {
        alipayTradeRefundModel.setRefundRoyaltyParameters(refundRoyaltyParameters);
        return this;
    }

    /**
     * It Is Useful In The Bank To Bank Mode. Please Do Not Use It In Other Scenarios;
     * Through This Parameter, The Double Link Specifies The PID Of The Acquirer Of The Exchange To Be Refunded;
     * <p>
     * 银行间联模式下有用,其它场景请不要使用:
     * 双联通过该参数指定需要退款的交易所属收单机构的PID;
     *
     * @param orgPid orgPid
     * @return PayRefundParamChain
     */
    public PayRefundParamChain builderOrgPid(String orgPid) {
        alipayTradeRefundModel.setOrgPid(orgPid);
        return this;
    }
}
