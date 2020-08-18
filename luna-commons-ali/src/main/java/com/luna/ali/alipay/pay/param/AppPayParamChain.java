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

package com.luna.ali.alipay.pay.param;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.ExtUserInfo;
import com.alipay.api.domain.ExtendParams;
import com.luna.ali.alipay.pay.AppPayChain;

/**
 * ClassName: AppPayParamChain
 * Description: Document Address https://docs.open.alipay.com/204/105465/
 * 文档地址 https://docs.open.alipay.com/204/105465/
 * date: 2019/12/24 17:46
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class AppPayParamChain {

    public AppPayParamChain() {}

    private AlipayClient           alipayClient;
    private AlipayTradeAppPayModel alipayTradeAppPayModel;

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public AlipayTradeAppPayModel getAlipayTradeAppPayModel() {
        return alipayTradeAppPayModel;
    }

    public void setAlipayTradeAppPayModel(AlipayTradeAppPayModel alipayTradeAppPayModel) {
        this.alipayTradeAppPayModel = alipayTradeAppPayModel;
    }

    public AppPayParamChain(AlipayClient alipayClient, AlipayTradeAppPayModel alipayTradeAppPayModel) {
        this.alipayClient = alipayClient;
        this.alipayTradeAppPayModel = alipayTradeAppPayModel;
    }

    /**
     * Builder AppPayChain
     * <p>
     * 构建 AppPayChain
     *
     * @return AppPayChain
     */
    public AppPayChain builder() {
        return new AppPayChain(alipayClient, alipayTradeAppPayModel);
    }

    /**
     * A Specific Description Of A Transaction.
     * If There Are Multiple Products,
     * Please Add Up The Product Description String And Pass It To The Body.
     * <p>
     * 对一笔交易的具体描述信息.如果是多种商品,请将商品描述字符串累加传给body.
     *
     * @param body body
     * @return AppPayParamChain
     */
    public AppPayParamChain builderBody(String body) {
        alipayTradeAppPayModel.setBody(body);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * Product Title / Transaction Title / Order Title / Order Keyword, Etc
     * <p>
     * !!这是不可缺参数
     * 商品的标题/交易标题/订单标题/订单关键字等
     *
     * @param subject subject
     * @return AppPayParamChain
     */
    public AppPayParamChain builderSubject(String subject) {
        alipayTradeAppPayModel.setSubject(subject);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * Unique Order Number Of Merchant Website
     * <p>
     * !!这是不可缺参数
     * 商户网站唯一订单号
     *
     * @param outTradeNo outTradeNo
     * @return AppPayParamChain
     */
    public AppPayParamChain builderOutTradeNo(String outTradeNo) {
        alipayTradeAppPayModel.setOutTradeNo(outTradeNo);
        return this;
    }

    /**
     * The Latest Payment Time Allowed For This Order.
     * If Overdue, The Transaction Will Be Closed.
     * Value Range: 1m-15d. M-minute, H-Hour, D-Day, 1c day
     * (In The Case Of 1C Day, No Matter When The Transaction Is Created, It Is Closed At 0:00).
     * This Parameter Value Does Not Accept Decimal Point, Such As 1.5h, Which Can Be Converted To 90m.
     * Note: If It Is Blank, It Defaults To 15d.
     * <p>
     * 该笔订单允许的最晚付款时间,逾期将关闭交易.
     * 取值范围：1m～15d.m-分钟,h-小时,d-天,1c-当天
     * （1c-当天的情况下,无论交易何时创建,都在0点关闭）.
     * 该参数数值不接受小数点, 如 1.5h,可转换为 90m.
     * 注：若为空,则默认为15d.
     *
     * @param timeoutExpress timeoutExpress
     * @return AppPayParamChain
     */
    public AppPayParamChain builderTimeoutExpress(String timeoutExpress) {
        alipayTradeAppPayModel.setTimeExpire(timeoutExpress);
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
     * @return AppPayParamChain
     */
    public AppPayParamChain builderTotalAmount(String totalAmount) {
        alipayTradeAppPayModel.setTotalAmount(totalAmount);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * Product Code, The Product Code Signed By Merchants And AliPay Is Fixed Value QUICK_MSECURITY_PAY.
     * <p>
     * !!这是不可缺参数
     * 销售产品码,商家和支付宝签约的产品码,为固定值 QUICK_MSECURITY_PAY
     *
     * @param productCode productCode
     * @return AppPayParamChain
     */
    public AppPayParamChain builderProductCode(String productCode) {
        alipayTradeAppPayModel.setProductCode(productCode);
        return this;
    }

    /**
     * Commodity Main Type: 0 - Virtual Commodity; 1 - Physical Commodity
     * Note: The Use Of Huabei Channel Is Not Supported For Virtual Goods
     * <p>
     * 商品主类型：0—虚拟类商品:1—实物类商品
     * 注：虚拟类商品不支持使用花呗渠道
     *
     * @param goodsType goodsType
     * @return AppPayParamChain
     */
    public AppPayParamChain builderGoodsType(String goodsType) {
        alipayTradeAppPayModel.setGoodsType(goodsType);
        return this;
    }

    /**
     * Public Return Parameter. If The Parameter Is Passed At The Time Of Request,
     * It Will Be Returned To The Merchant.
     * AliPay Will Return The Original Parameter In Asynchronous Notification.
     * This Parameter Must Be UrlEncode Before It Can Be Sent To AliPay.
     * <p>
     * 公用回传参数,如果请求时传递了该参数,则返回给商户时会回传该参数.
     * 支付宝会在异步通知时将该参数原样返回.本参数必须进行 UrlEncode 之后才可以发送给支付宝
     *
     * @param passBackParams passBackParams
     * @return AppPayParamChain
     */
    public AppPayParamChain builderPassBackParams(String passBackParams) {
        alipayTradeAppPayModel.setPassbackParams(passBackParams);
        return this;
    }

    /**
     * Preferential Parameters
     * Note: Only After Consultation With AliPay.
     * <p>
     * 优惠参数
     * 注：仅与支付宝协商后可用
     *
     * @param promoParams promoParams
     * @return AppPayParamChain
     */
    public AppPayParamChain builderPromoParams(String promoParams) {
        alipayTradeAppPayModel.setPromoParams(promoParams);
        return this;
    }

    /**
     * Business Extension Parameters ,Document Address https://docs.open.alipay.com/204/105465/
     * <p>
     * 业务扩展参数,文档地址 https://docs.open.alipay.com/204/105465/
     *
     * @param extendParams extendParams
     * @return AppPayParamChain
     */
    public AppPayParamChain builderExtendParams(ExtendParams extendParams) {
        alipayTradeAppPayModel.setExtendParams(extendParams);
        return this;
    }

    /**
     * Available Channels, Users Can Only Pay Within The Specified Channels
     * Use "," To Separate When There Are Multiple Channels
     * Note: mutually exclusive with DisablePayChannels,
     * Document Address https://docs.open.alipay.com/204/105465/
     * <p>
     * 可用渠道,用户只能在指定渠道范围内支付
     * 当有多个渠道时用“,”分隔
     * 注：与 DisablePayChannels 互斥,
     * 文档地址 https://docs.open.alipay.com/204/105465/
     *
     * @param enablePayChannels enablePayChannels
     * @return AppPayParamChain
     */
    public AppPayParamChain builderEnablePayChannels(String enablePayChannels) {
        alipayTradeAppPayModel.setEnablePayChannels(enablePayChannels);
        return this;
    }

    /**
     * Disable Channel, User Can't Pay Through Specified Channel
     * Use "," to Separate When There Are Multiple Channels
     * Note: Mutually Exclusive With EnablePayChannels,
     * Document Address https://docs.open.alipay.com/204/105465/
     * <p>
     * 禁用渠道,用户不可用指定渠道支付
     * 当有多个渠道时用“,”分隔
     * 注：与 EnablePayChannels 互斥,
     * 文档地址 https://docs.open.alipay.com/204/105465/
     *
     * @param disablePayChannels disablePayChannels
     * @return AppPayParamChain
     */
    public AppPayParamChain builderDisablePayChannels(String disablePayChannels) {
        alipayTradeAppPayModel.setDisablePayChannels(disablePayChannels);
        return this;
    }

    /**
     * Merchant Store Number. This Parameter Is Used In The Request Parameter To Distinguish Stores,
     * Not Required
     * 商户门店编号.该参数用于请求参数中以区分各门店,非必传项
     *
     * @param storeId storeId
     * @return AppPayParamChain
     */
    public AppPayParamChain builderStoreId(String storeId) {
        alipayTradeAppPayModel.setStoreId(storeId);
        return this;
    }

    /**
     * External Designated Buyer, Document Address https://docs.open.alipay.com/204/105465/
     * 外部指定买家,文档地址 https://docs.open.alipay.com/204/105465/
     *
     * @param extUserInfo extUserInfo
     * @return AppPayParamChain
     */
    public AppPayParamChain builderExtUserInfo(ExtUserInfo extUserInfo) {
        alipayTradeAppPayModel.setExtUserInfo(extUserInfo);
        return this;
    }

}
