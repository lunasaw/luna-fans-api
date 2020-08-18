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

package com.luna.ali.alipay.pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.*;
import com.luna.ali.alipay.container.PayParamConstant;
import com.luna.ali.alipay.pay.close.param.PayCloseParamChain;
import com.luna.ali.alipay.pay.download.param.PayDownloadQueryParamChain;
import com.luna.ali.alipay.pay.param.AppPayParamChain;
import com.luna.ali.alipay.pay.param.PagePayParamChain;
import com.luna.ali.alipay.pay.param.WapPayParamChain;
import com.luna.ali.alipay.pay.query.param.PayQueryParamChain;
import com.luna.ali.alipay.pay.refund.param.PayRefundParamChain;
import com.luna.ali.alipay.pay.refund.query.param.PayRefundQueryParamChain;

/**
 * ClassName: DefaultPayChain
 * Description:
 * date: 2019/12/24 16:50
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */

public class DefaultPayChain {
    private AlipayClient alipayClient;

    public DefaultPayChain() {}

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public DefaultPayChain(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    /**
     * Pay With App , Document Address https://docs.open.alipay.com/204
     * <p>
     * 使用app支付,文档地址 https://docs.open.alipay.com/204
     *
     * @param subject Product Title / Transaction Title / Order Title / Order Keyword, Etc
     * 商品的标题/交易标题/订单标题/订单关键字等
     * @param outTradeNo Unique Order Number Of Merchant Website
     * 商户网站唯一订单号
     * @param totalAmount Total Order Amount, Unit: Yuan, Accurate To Two Decimal Places, Value Range [0.01100000000]
     * 订单总金额,单位为元,精确到小数点后两位,取值范围[0.01,100000000]
     * @return AppPayParamChain
     */
    public AppPayParamChain appPay(String subject, String outTradeNo, String totalAmount) {
        AlipayTradeAppPayModel appPayModel = new AlipayTradeAppPayModel();
        appPayModel.setSubject(subject);
        appPayModel.setOutTradeNo(outTradeNo);
        appPayModel.setTotalAmount(totalAmount);
        appPayModel.setProductCode(PayParamConstant.APP_PAY_PARAM_PRODUCT_CODE.getValue());
        return new AppPayParamChain(alipayClient, appPayModel);
    }

    /**
     * Custom Payment Parameters, Recommended
     * {@linkplain AlipayTradeAppPayModel AlipayTradeAppPayModel}
     * <p>
     * 自定义支付参数,建议使用
     * {@linkplain AlipayTradeAppPayModel AlipayTradeAppPayModel}
     *
     * @param appPayModel appPayModel
     * @return AppPayParamChain
     */
    public AppPayParamChain appPay(AlipayTradeAppPayModel appPayModel) {
        return new AppPayParamChain(alipayClient, appPayModel);
    }

    /**
     * Pay With Mobile Website, Document Address https://docs.open.alipay.com/203
     * <p>
     * 使用手机网站支付,文档地址 https://docs.open.alipay.com/203
     *
     * @param subject Product Title / Transaction Title / Order Title / Order Keyword, Etc
     * 商品的标题/交易标题/订单标题/订单关键字等
     * @param outTradeNo Unique Order Number Of Merchant Website
     * 商户网站唯一订单号
     * @param totalAmount Total Order Amount, Unit: Yuan, Accurate To Two Decimal Places, Value Range [0.01100000000]
     * 订单总金额,单位为元,精确到小数点后两位,取值范围[0.01,100000000]
     * @return WapPayParamChain
     */
    public WapPayParamChain wapPay(String subject, String outTradeNo, String totalAmount) {
        AlipayTradeWapPayModel payModel = new AlipayTradeWapPayModel();
        payModel.setSubject(subject);
        payModel.setOutTradeNo(outTradeNo);
        payModel.setTotalAmount(totalAmount);
        payModel.setProductCode(PayParamConstant.WAP_PAY_PARAM_PRODUCT_CODE.getValue());
        return new WapPayParamChain(alipayClient, payModel);
    }

    /**
     * Custom Payment Parameters,
     * Recommended {@linkplain AlipayTradeWapPayModel AlipayTradeWapPayModel}
     * <p>
     * 自定义支付参数,建议使用
     * {@linkplain AlipayTradeWapPayModel AlipayTradeWapPayModel}
     *
     * @param payModel payModel
     * @return WapPayParamChain
     */
    public WapPayParamChain wapPay(AlipayTradeWapPayModel payModel) {
        return new WapPayParamChain(alipayClient, payModel);
    }

    /**
     * Pay By Computer Website, Document Address https://docs.open.alipay.com/270
     * <p>
     * 使用电脑网站支付,文档地址 https://docs.open.alipay.com/270
     *
     * @param subject Product Title / Transaction Title / Order Title / Order Keyword, Etc
     * 商品的标题/交易标题/订单标题/订单关键字等
     * @param outTradeNo Unique Order Number Of Merchant Website
     * 商户网站唯一订单号
     * @param totalAmount Total Order Amount, Unit: Yuan, Accurate To Two Decimal Places, Value Range [0.01100000000]
     * 订单总金额,单位为元,精确到小数点后两位,取值范围[0.01,100000000]
     * @return PagePayParamChain
     */
    public PagePayParamChain pagePay(String subject, String outTradeNo, String totalAmount) {
        AlipayTradePagePayModel payModel = new AlipayTradePagePayModel();
        payModel.setSubject(subject);
        payModel.setOutTradeNo(outTradeNo);
        payModel.setTotalAmount(totalAmount);
        payModel.setProductCode(PayParamConstant.PAGE_PAT_PARAM_PRODUCT_CODE.getValue());
        return new PagePayParamChain(alipayClient, payModel);
    }

    /**
     * Custom Payment Parameters,
     * Recommended {@linkplain AlipayTradePagePayModel AlipayTradePagePayModel}
     * <p>
     * 自定义支付参数,建议使用
     * {@linkplain AlipayTradePagePayModel AlipayTradePagePayModel}
     *
     * @param payModel payModel
     * @return PagePayParamChain
     */
    public PagePayParamChain pagePay(AlipayTradePagePayModel payModel) {
        return new PagePayParamChain(alipayClient, payModel);
    }

    /**
     * Closing Transaction, Document Address https://docs.open.alipay.com/api_1/alipay.trade.close
     * <p>
     * 关闭交易,文档地址 https://docs.open.alipay.com/api_1/alipay.trade.close
     *
     * @param outTradeNo When The Order Is Paid, The Incoming Merchant Order Number And The AliPay Transaction Number
     * Can Not Be Empty At The Same Time.
     * TradeNo, OutTradeNo If Both Exist, Take TradeNo First
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空.
     * TradeNo,OutTradeNo如果同时存在优先取TradeNo
     * @param tradeNo The Transaction In AliPay System Transaction Code.
     * The Shortest 16 Bits, The Longest 64 Bits.
     * And OutTradeNo Cannot Be Empty At The Same Time.
     * If OutTradeNo And TradeNo Are Passed At The Same Time,
     * TradeNo Shall Prevail.
     * 该交易在支付宝系统中的交易流水号.最短 16 位,最长 64 位.
     * 和OutTradeNo不能同时为空,
     * 如果同时传了 OutTradeNo和 TradeNo,
     * 则以 TradeNo为准.
     * @return PayCloseParamChain
     */
    public PayCloseParamChain closePay(String outTradeNo, String tradeNo) {
        AlipayTradeCloseModel closeModel = new AlipayTradeCloseModel();
        closeModel.setOutTradeNo(outTradeNo);
        closeModel.setTradeNo(tradeNo);
        return new PayCloseParamChain(alipayClient, closeModel);
    }

    /**
     * Custom Close Transaction,
     * Recommended {@linkplain AlipayTradeCloseModel AlipayTradeCloseModel}
     * <p>
     * 自定义关闭交易,建议使用
     * {@linkplain AlipayTradeCloseModel AlipayTradeCloseModel}
     *
     * @param closeModel closeModel
     * @return PayCloseParamChain
     */
    public PayCloseParamChain closePay(AlipayTradeCloseModel closeModel) {
        return new PayCloseParamChain(alipayClient, closeModel);
    }

    /**
     * Query Payment,Document Address https://docs.open.alipay.com/api_1/alipay.trade.query
     * <p>
     * 查询付款,文档地址 https://docs.open.alipay.com/api_1/alipay.trade.query
     *
     * @param outTradeNo When The Order Is Paid, The Incoming Merchant Order Number And The AliPay Transaction Number
     * Can Not Be Empty At The Same Time.
     * TradeNo, OutTradeNo If There Is TradeNo Priority At The Same Time
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空.
     * TradeNo,OutTradeNo如果同时存在优先取TradeNo
     * @param tradeNo AliPay Transaction Number And Merchant Order Number Can Not Be Empty At The Same Time.
     * 支付宝交易号,和商户订单号不能同时为空
     * @return PayQueryParamChain
     */
    public PayQueryParamChain queryPay(String outTradeNo, String tradeNo) {
        AlipayTradeQueryModel queryModel = new AlipayTradeQueryModel();
        queryModel.setOutTradeNo(outTradeNo);
        queryModel.setTradeNo(tradeNo);
        return new PayQueryParamChain(alipayClient, queryModel);
    }

    /**
     * Custom query payment,
     * Recommended {@linkplain AlipayTradeQueryModel AlipayTradeQueryModel}
     * <p>
     * 自定义查询付款,建议使用
     * {@linkplain AlipayTradeQueryModel AlipayTradeQueryModel}
     *
     * @param queryModel queryModel
     * @return PayQueryParamChain
     */
    public PayQueryParamChain queryPay(AlipayTradeQueryModel queryModel) {
        return new PayQueryParamChain(alipayClient, queryModel);
    }

    /**
     * Transaction Refund,Document Address https://docs.open.alipay.com/api_1/alipay.trade.refund
     * <p>
     * 交易退款,文档地址 https://docs.open.alipay.com/api_1/alipay.trade.refund
     *
     * @param outTradeNo The Merchant Order Number Passed In During Order Payment Cannot Be Empty At The Same Time As
     * TradeNo
     * 订单支付时传入的商户订单号,不能和 tradeNo同时为空.
     * @param tradeNo AliPay Transaction Number And Merchant Order Number Can Not Be Empty At The Same Time.
     * 支付宝交易号,和商户订单号不能同时为空
     * @param refundAmount The Amount To Be Refunded Cannot Be Greater Than The Order Amount.
     * The Unit Is Yuan And Two Decimal Places Are Supported
     * 需要退款的金额,该金额不能大于订单金额,单位为元,支持两位小数
     * @return PayRefundParamChain
     */
    public PayRefundParamChain refundPay(String outTradeNo, String tradeNo, String refundAmount, String refundReason,
        String outRequestNo) {
        AlipayTradeRefundModel refundModel = new AlipayTradeRefundModel();
        refundModel.setOutTradeNo(outTradeNo);
        refundModel.setTradeNo(tradeNo);
        refundModel.setRefundAmount(refundAmount);
        refundModel.setRefundReason(refundReason);
        refundModel.setOutRequestNo(outRequestNo);
        return new PayRefundParamChain(alipayClient, refundModel);
    }

    /**
     * Custom Refund
     * Recommended {@linkplain AlipayTradeRefundModel AlipayTradeRefundModel}
     * <p>
     * 自定义退款,建议使用
     * {@linkplain AlipayTradeRefundModel AlipayTradeRefundModel}
     *
     * @param refundModel refundModel
     * @return PayRefundParamChain
     */
    public PayRefundParamChain refundPay(AlipayTradeRefundModel refundModel) {
        return new PayRefundParamChain(alipayClient, refundModel);
    }

    /**
     * Refund Inquiry,Document Address https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query
     * 退款查询,文档地址 https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query
     *
     * @param outTradeNo When The Order Is Paid, The Incoming Merchant Order Number And The AliPay Transaction Number
     * Can Not Be Empty At The Same Time.
     * OutTradeNo, OutRequestNo If There Is A Priority OutTradeNo At The Same Time
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空.
     * OutTradeNo,OutRequestNo如果同时存在优先取OutTradeNo
     * @param tradeNo AliPay Transaction Number And Merchant Order Number Can Not Be Empty At The Same Time.
     * 支付宝交易号,和商户订单号不能同时为空
     * @param outRequestNo When Requesting The Refund Interface, The Incoming Refund Request Number.
     * If It Is Not Passed In At The Time Of Refund Request,
     * The Value Is The External Transaction Number At The Time Of Transaction Creation
     * 请求退款接口时,传入的退款请求号,
     * 如果在退款请求时未传入,则该值为创建交易时的外部交易号
     * @return PayRefundQueryParamChain
     */
    public PayRefundQueryParamChain refundQueryPay(String outTradeNo, String tradeNo, String outRequestNo) {
        AlipayTradeFastpayRefundQueryModel queryModel = new AlipayTradeFastpayRefundQueryModel();
        queryModel.setOutTradeNo(outTradeNo);
        queryModel.setTradeNo(tradeNo);
        queryModel.setOutRequestNo(outRequestNo);
        return new PayRefundQueryParamChain(alipayClient, queryModel);
    }

    /**
     * Custom Refund Query
     * Recommended {@linkplain AlipayTradeFastpayRefundQueryModel AlipayTradeFastpayRefundQueryModel}
     * <p>
     * 自定义退款查询,建议使用
     * {@linkplain AlipayTradeFastpayRefundQueryModel AlipayTradeFastpayRefundQueryModel}
     *
     * @param queryModel queryModel
     * @return PayRefundQueryParamChain
     */
    public PayRefundQueryParamChain refundQueryPay(AlipayTradeFastpayRefundQueryModel queryModel) {
        return new PayRefundQueryParamChain(alipayClient, queryModel);
    }

    /**
     * Query Bill Download,Document Address
     * https://docs.open.alipay.com/api_15/alipay.data.dataservice.bill.downloadurl.query
     * <p>
     * 查询账单下载,文档地址 https://docs.open.alipay.com/api_15/alipay.data.dataservice.bill.downloadurl.query
     *
     * @param billType The Type Of Bill, The Merchant Through The Interface Or Merchant Authorized By The Open Platform,
     * Its Service Providers Through The Interface Can Get The Following Types Of Bills:
     * trade, signcustomer;
     * trade Refers To The Business Account Receipts Based On The AliPay Transaction;
     * signcustomer Refers To The Account Receipts Based On The Changes In The AliPay Balance Income And Expenditure.
     * 账单类型,
     * 商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型:
     * trade、signcustomer；
     * trade指商户基于支付宝交易收单的业务账单；
     * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单.
     * @param billDate Bill Time: The Format Of Daily Bill Is yyyy-MM-dd,
     * And The Earliest Daily Bill From January 1, 2016 Can Be Downloaded;
     * The Format Of Monthly Bill Is yyyy-MM, And The Earliest Monthly Bill From January 2016 Can Be Downloaded.
     * 账单时间:日账单格式为yyyy-MM-dd,最早可下载2016年1月1日开始的日账单；
     * 月账单格式为yyyy-MM,最早可下载2016年1月开始的月账单.
     * @return PayDownloadQueryParamChain
     */
    public PayDownloadQueryParamChain downloadQueryPay(String billType, String billDate) {
        AlipayDataDataserviceBillDownloadurlQueryModel queryModel =
            new AlipayDataDataserviceBillDownloadurlQueryModel();
        queryModel.setBillType(billType);
        queryModel.setBillDate(billDate);
        return new PayDownloadQueryParamChain(alipayClient, queryModel);
    }

    /**
     * Custom Query Bill Download
     * Recommended {@linkplain AlipayDataDataserviceBillDownloadurlQueryModel
     * AlipayDataDataserviceBillDownloadurlQueryModel}
     * <p>
     * 自定义查询账单下载,建议使用
     * {@linkplain AlipayDataDataserviceBillDownloadurlQueryModel AlipayDataDataserviceBillDownloadurlQueryModel}
     *
     * @param queryModel queryModel
     * @return PayDownloadQueryParamChain
     */
    public PayDownloadQueryParamChain downloadQueryPay(AlipayDataDataserviceBillDownloadurlQueryModel queryModel) {
        return new PayDownloadQueryParamChain(alipayClient, queryModel);
    }

}
