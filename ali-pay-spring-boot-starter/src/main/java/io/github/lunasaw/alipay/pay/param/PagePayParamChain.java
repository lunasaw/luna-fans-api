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

package io.github.lunasaw.alipay.pay.param;

import java.util.List;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.*;
import io.github.lunasaw.alipay.pay.PagePayChain;

/**
 * ClassName: PagePayParamChain
 * Description: Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay
 * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay
 * date: 2019/12/24 20:25
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class PagePayParamChain {
    private AlipayClient            alipayClient;
    private AlipayTradePagePayModel alipayTradePagePayModel;

    public PagePayParamChain() {}

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public AlipayTradePagePayModel getAlipayTradePagePayModel() {
        return alipayTradePagePayModel;
    }

    public void setAlipayTradePagePayModel(AlipayTradePagePayModel alipayTradePagePayModel) {
        this.alipayTradePagePayModel = alipayTradePagePayModel;
    }

    public PagePayParamChain(AlipayClient alipayClient, AlipayTradePagePayModel alipayTradePagePayModel) {
        this.alipayClient = alipayClient;
        this.alipayTradePagePayModel = alipayTradePagePayModel;
    }

    /**
     * Builder PagePayChain
     * <p>
     * 构建 PagePayChain
     *
     * @return PagePayChain
     */
    public PagePayChain builder() {
        return new PagePayChain(alipayClient, alipayTradePagePayModel);
    }

    /**
     * !!This Is An Indispensable Parameter
     * The Order Number Of The Merchant, Within 64 Characters,
     * Can Contain Letters, Numbers And Underscores;
     * It Shall Not Be Repeated At The Merchant Side
     * <p>
     * !!这是不可缺参数
     * 商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
     *
     * @param outTradeNo outTradeNo
     * @return PagePayParam
     */
    public PagePayParamChain builderOutTradeNo(String outTradeNo) {
        alipayTradePagePayModel.setOutTradeNo(outTradeNo);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * Sell Product Code, Name Of Product Code Signed With AliPay.
     * Note: Currently Only Supported:FAST_INSTANT_TRADE_PAY
     * <p>
     * !!这是不可缺参数
     * 销售产品码,与支付宝签约的产品码名称.
     * 注:目前仅支持FAST_INSTANT_TRADE_PAY
     *
     * @param productCode productCode
     * @return PagePayParamChain
     */
    public PagePayParamChain builderProductCode(String productCode) {
        alipayTradePagePayModel.setProductCode(productCode);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * Total Order Amount, Unit: Yuan, Accurate To Two Decimal Places, Value Range [0.01100000000]
     * <p>
     * !!这是不可缺参数
     * 订单总金额,单位为元,精确到小数点后两位,取值范围[0.01,100000000]
     *
     * @param totalAmount totalAmount
     * @return PagePayParamChain
     */
    public PagePayParamChain builderTotalAmount(String totalAmount) {
        alipayTradePagePayModel.setTotalAmount(totalAmount);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * Order Title
     * <p>
     * !!这是不可缺参数
     * 订单标题
     *
     * @param subject subject
     * @return PagePayParamChain
     */
    public PagePayParamChain builderSubject(String subject) {
        alipayTradePagePayModel.setSubject(subject);
        return this;
    }

    /**
     * Order Description
     * <p>
     * 订单描述
     *
     * @param body body
     * @return PagePayParamChain
     */
    public PagePayParamChain builderBody(String body) {
        alipayTradePagePayModel.setBody(body);
        return this;
    }

    /**
     * Absolute Timeout,Format Is yyyy-MM-dd HH:mm:ss
     * <p>
     * 绝对超时时间,格式为yyyy-MM-dd HH:mm:ss
     *
     * @param timeExpire timeExpire
     * @return PagePayParamChain
     */
    public PagePayParamChain builderTimeExpire(String timeExpire) {
        alipayTradePagePayModel.setTimeExpire(timeExpire);
        return this;
    }

    /**
     * Product list information contained in the order, JSON format,
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     * <p>
     * 订单包含的商品列表信息,JSON格式,
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     *
     * @param goodsDetails goodsDetails
     * @return PagePayParamChain
     */
    public PagePayParamChain builderGoodsDetail(List<GoodsDetail> goodsDetails) {
        alipayTradePagePayModel.setGoodsDetail(goodsDetails);
        return this;
    }

    /**
     * Public Return Parameter. If The Parameter Is Passed At The Time Of Request,
     * It Will Be Returned To The Merchant.
     * AliPay Will Return The Original Parameter Only When It Returns Synchronously,
     * Including The Jump To Business Website And Asynchronous Notification.
     * This Parameter Must Be UrlEncode Before It Can Be Sent to AliPay.
     * <p>
     * 公用回传参数,如果请求时传递了该参数,则返回给商户时会回传该参数.
     * 支付宝只会在同步返回（包括跳转回商户网站）和异步通知时将该参数原样返回.
     * 本参数必须进行UrlEncode之后才可以发送给支付宝
     *
     * @param passBackParams passBackParams
     * @return PagePayParamChain
     */
    public PagePayParamChain builderPassBackParams(String passBackParams) {
        alipayTradePagePayModel.setPassbackParams(passBackParams);
        return this;
    }

    /**
     * Business Extension Parameters
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     * <p>
     * 业务扩展参数
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     *
     * @param extendParams extendParams
     * @return PagePayParamChain
     */
    public PagePayParamChain builderExtendParams(ExtendParams extendParams) {
        alipayTradePagePayModel.setExtendParams(extendParams);
        return this;
    }

    /**
     * Commodity Main Type: 0 - Virtual Commodity; 1 - Physical Commodity
     * Note: The Use Of Huabei Channel Is Not Supported For Virtual Goods
     * <p>
     * 商品主类型:0—虚拟类商品；1—实物类商品
     * 注:虚拟类商品不支持使用花呗渠道
     *
     * @param goodsType goodsType
     * @return PagePayParamChain
     */
    public PagePayParamChain builderGoodsType(String goodsType) {
        alipayTradePagePayModel.setGoodsType(goodsType);
        return this;
    }

    /**
     * The Latest Payment Time Allowed For This Order.
     * If Overdue, The Transaction Will Be Closed.
     * Value Range: 1m-15d. M-minute, H-Hour, D-Day, 1c day
     * (In The Case Of 1C Day, No Matter When The Transaction Is Created, It Is Closed At 0:00).
     * This Parameter Value Does Not Accept Decimal Point, Such As 1.5h, Which Can Be Converted To 90m.
     * <p>
     * 该笔订单允许的最晚付款时间,逾期将关闭交易.
     * 取值范围:1m～15d.m-分钟,h-小时,d-天,1c-当天
     * （1c-当天的情况下,无论交易何时创建,都在0点关闭）.
     * 该参数数值不接受小数点, 如 1.5h,可转换为 90m.
     *
     * @param timeoutExpress timeoutExpress
     * @return AppPayParamChain
     */
    public PagePayParamChain builderTimeoutExpress(String timeoutExpress) {
        alipayTradePagePayModel.setTimeExpire(timeoutExpress);
        return this;
    }

    /**
     * Preferential Parameters
     * Note: Only After Consultation With AliPay.
     * <p>
     * 优惠参数
     * 注:仅与支付宝协商后可用
     *
     * @param promoParams promoParams
     * @return AppPayParamChain
     */
    public PagePayParamChain builderPromoParams(String promoParams) {
        alipayTradePagePayModel.setPromoParams(promoParams);
        return this;
    }

    /**
     * Describe The Sub Ledger Information
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     * <p>
     * 描述分账信息
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     *
     * @param royaltyInfo royaltyInfo
     * @return PagePayParamChain
     */
    public PagePayParamChain builderRoyaltyInfo(RoyaltyInfo royaltyInfo) {
        alipayTradePagePayModel.setRoyaltyInfo(royaltyInfo);
        return this;
    }

    /**
     * This Field Is Only Used In Specific Scenarios Of Special Banking Institutions
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     * <p>
     * 间连受理商户信息体,当前只对特殊银行机构特定场景下使用此字段
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     *
     * @param subMerchant subMerchant
     * @return PagePayParamChain
     */
    public PagePayParamChain builderSubMerchant(SubMerchant subMerchant) {
        alipayTradePagePayModel.setSubMerchant(subMerchant);
        return this;
    }

    /**
     * Merchant Original Order Number, Maximum Length Limit 32 Bits
     * <p>
     * 商户原始订单号,最大长度限制32位
     *
     * @param merchantOrderNo merchantOrderNo
     * @return PagePayParamChain
     */
    public PagePayParamChain builderMerchantOrderNo(String merchantOrderNo) {
        alipayTradePagePayModel.setMerchantOrderNo(merchantOrderNo);
        return this;
    }

    /**
     * Available Channels, Users Can Only Pay Within The Specified Channel Range,
     * Multiple Channels Are Separated By Commas
     * Note: It Is Mutually Exclusive With DisablePayChannels
     * Channel List: https://docs.open.alipay.com/common/wifww7
     * <p>
     * 可用渠道,用户只能在指定渠道范围内支付,多个渠道以逗号分割
     * 注,与DisablePayChannels互斥
     * 渠道列表:https://docs.open.alipay.com/common/wifww7
     *
     * @param enablePayChannels enablePayChannels
     * @return PagePayParamChain
     */
    public PagePayParamChain builderEnablePayChannels(String enablePayChannels) {
        alipayTradePagePayModel.setEnablePayChannels(enablePayChannels);
        return this;
    }

    /**
     * Merchant store number
     * <p>
     * 商户门店编号
     *
     * @param storedId storedId
     * @return PagePayParamChain
     */
    public PagePayParamChain builderStoredId(String storedId) {
        alipayTradePagePayModel.setStoreId(storedId);
        return this;
    }

    /**
     * Disable channel, users can't use the specified channel to pay,
     * multiple channels are separated by commas
     * Note, mutually exclusive with EnablePayChannels
     * Channel list: https://docs.open.alipay.com/common/wifww7
     * <p>
     * 禁用渠道,用户不可用指定渠道支付,多个渠道以逗号分割
     * 注,与EnablePayChannels互斥
     * 渠道列表:https://docs.open.alipay.com/common/wifww7
     *
     * @param disablePayChannels disablePayChannels
     * @return PagePayParamChain
     */
    public PagePayParamChain builderDisablePayChannels(String disablePayChannels) {
        alipayTradePagePayModel.setDisablePayChannels(disablePayChannels);
        return this;
    }

    /**
     * PC scan payment mode, support the front mode and
     * <p>
     * Jump mode.
     * The front-end mode is to forward the QR code to the merchant
     * Mode of the order confirmation page for. The merchant is required to
     * Request in iframe mode in own page
     * Alipay page. It can be divided into the following categories:
     * 0: order code - reduced front mode, corresponding iframe width cannot be less than 600px and height cannot be
     * less than 300px;
     * 1: Order code pre mode, corresponding iframe width cannot be less than 300px, and height cannot be less than
     * 600px;
     * 3: Order code - Mini front mode, corresponding iframe width cannot be less than 75px, height cannot be less than
     * 75px;
     * 4: Order code - an embedded QR code with a definable width. The merchant can set the size of the QR code as
     * required.
     * In the jump mode, the user's scanning interface is generated by Alipay, not under the domain name of the
     * merchant.
     * 2: Order code - jump mode
     * <p>
     * PC扫码支付的方式,支持前置模式和
     * <p>
     * 跳转模式.
     * 前置模式是将二维码前置到商户
     * 的订单确认页的模式.需要商户在
     * 自己的页面中以 iframe 方式请求
     * 支付宝页面.具体分为以下几种:
     * 0:订单码-简约前置模式,对应 iframe 宽度不能小于600px,高度不能小于300px；
     * 1:订单码-前置模式,对应iframe 宽度不能小于 300px,高度不能小于600px；
     * 3:订单码-迷你前置模式,对应 iframe 宽度不能小于 75px,高度不能小于75px；
     * 4:订单码-可定义宽度的嵌入式二维码,商户可根据需要设定二维码的大小.
     * <p>
     * 跳转模式下,用户的扫码界面是由支付宝生成的,不在商户的域名下.
     * 2:订单码-跳转模式
     *
     * @param qrPayMode qrPayMode
     * @return PagePayParamChain
     */
    public PagePayParamChain builderQrPayMode(String qrPayMode) {
        alipayTradePagePayModel.setQrPayMode(qrPayMode);
        return this;
    }

    /**
     * Merchant Custom QR Code Width
     * Note: This Parameter Takes Effect When QrPayMode = 4
     * <p>
     * 商户自定义二维码宽度
     * 注:QrPayMode=4时该参数生效
     *
     * @param qrCodeWidth qrCodeWidth
     * @return PagePayParamChain
     */
    public PagePayParamChain builderQrCodeWidth(Long qrCodeWidth) {
        alipayTradePagePayModel.setQrcodeWidth(qrCodeWidth);
        return this;
    }

    /**
     * Description Of Settlement Information, JSON Format
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     * <p>
     * 描述结算信息,JSON格式
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     *
     * @param settleInfo settleInfo
     * @return PagePayParamChain
     */
    public PagePayParamChain builderSettleInfo(SettleInfo settleInfo) {
        alipayTradePagePayModel.setSettleInfo(settleInfo);
        return this;
    }

    /**
     * Invoice Information
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     * <p>
     * 开票信息
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     *
     * @param invoiceInfo invoiceInfo
     * @return PagePayParamChain
     */
    public PagePayParamChain builderInvoiceInfo(InvoiceInfo invoiceInfo) {
        alipayTradePagePayModel.setInvoiceInfo(invoiceInfo);
        return this;
    }

    /**
     * Signing Parameters, Use Of Signing Scenarios After Payment
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     * <p>
     * 签约参数,支付后签约场景使用
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     *
     * @param agreementSignParams agreementSignParams
     * @return PagePayParamChain
     */
    public PagePayParamChain builderAgreementSignParams(AgreementSignParams agreementSignParams) {
        alipayTradePagePayModel.setAgreementSignParams(agreementSignParams);
        return this;
    }

    /**
     * Integration Mode Of The Page After Request.
     * Value Range:
     * 1. ALIAPP: Alipay Wallet
     * 2. PCWEB: PC Access
     * The Default Is PCWEB.
     * <p>
     * 请求后页面的集成方式.
     * 取值范围:
     * 1. ALIAPP:支付宝钱包内
     * 2. PCWEB:PC端访问
     * 默认值为PCWEB.
     *
     * @param integrationType integrationType
     * @return PagePayParamChain
     */
    public PagePayParamChain builderIntegrationType(String integrationType) {
        alipayTradePagePayModel.setIntegrationType(integrationType);
        return this;
    }

    /**
     * Request Source Address. If The Integration Method Of AliApp Is Used,
     * The Address Will Be Returned If The User Cancels The Payment Midway.
     * <p>
     * 请求来源地址.如果使用ALIAPP的集成方式,用户中途取消支付会返回该地址.
     *
     * @param requestFromUrl requestFromUrl
     * @return PagePayParamChain
     */
    public PagePayParamChain builderRequestFromUrl(String requestFromUrl) {
        alipayTradePagePayModel.setRequestFromUrl(requestFromUrl);
        return this;
    }

    /**
     * Business Information, Specific Value And AliPay Agreed To Apply To Security,
     * Marketing And Other Parameters Directly Transmitted Scenario, Format JSON Format.
     * <p>
     * 商户传入业务信息,具体值要和支付宝约定,应用于安全,
     * 营销等参数直传场景,格式为JSON格式
     *
     * @param businessParams businessParams
     * @return PagePayParamChain
     */
    public PagePayParamChain builderBusinessParams(String businessParams) {
        alipayTradePagePayModel.setBusinessParams(businessParams);
        return this;
    }

    /**
     * External Designated Buyer
     * Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     * <p>
     * 外部指定买家
     * 文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     *
     * @param extUserInfo extUserInfo
     * @return PagePayParamChain
     */
    public PagePayParamChain builderExtUserInfo(ExtUserInfo extUserInfo) {
        alipayTradePagePayModel.setExtUserInfo(extUserInfo);
        return this;
    }
}
