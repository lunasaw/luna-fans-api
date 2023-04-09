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

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.domain.ExtUserInfo;
import com.alipay.api.domain.ExtendParams;
import io.github.lunasaw.alipay.pay.WapPayChain;

/**
 * ClassName: WapPayParamChain
 * Description: Document Address https://docs.open.alipay.com/203/107090/
 * 文档地址 https://docs.open.alipay.com/203/107090/
 * date: 2019/12/24 19:05
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class WapPayParamChain {
    private AlipayClient           alipayClient;
    private AlipayTradeWapPayModel alipayTradeWapPayModel;

    public WapPayParamChain() {}

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public AlipayTradeWapPayModel getAlipayTradeWapPayModel() {
        return alipayTradeWapPayModel;
    }

    public void setAlipayTradeWapPayModel(AlipayTradeWapPayModel alipayTradeWapPayModel) {
        this.alipayTradeWapPayModel = alipayTradeWapPayModel;
    }

    public WapPayParamChain(AlipayClient alipayClient, AlipayTradeWapPayModel alipayTradeWapPayModel) {
        this.alipayClient = alipayClient;
        this.alipayTradeWapPayModel = alipayTradeWapPayModel;
    }

    /**
     * Builder WapPayChain
     * <p>
     * 构建 WapPayChain
     *
     * @return WapPayChain
     */
    public WapPayChain builder() {
        return new WapPayChain(alipayClient, alipayTradeWapPayModel);
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
    public WapPayParamChain builderBody(String body) {
        alipayTradeWapPayModel.setBody(body);
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
    public WapPayParamChain builderSubject(String subject) {
        alipayTradeWapPayModel.setSubject(subject);
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
    public WapPayParamChain builderOutTradeNo(String outTradeNo) {
        alipayTradeWapPayModel.setOutTradeNo(outTradeNo);
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
     * 取值范围:1m～15d.m-分钟,h-小时,d-天,1c-当天
     * （1c-当天的情况下,无论交易何时创建,都在0点关闭）.
     * 该参数数值不接受小数点, 如 1.5h,可转换为 90m.
     * 注:若为空,则默认为15d.
     *
     * @param timeoutExpress timeoutExpress
     * @return AppPayParamChain
     */
    public WapPayParamChain builderTimeoutExpress(String timeoutExpress) {
        alipayTradeWapPayModel.setTimeExpire(timeoutExpress);
        return this;
    }

    /**
     * Absolute Timeout In The Format yyyy-MM-dd HH:mm. Note: 1)
     * Take AliPay System Time As The Criterion; 2)
     * If TimeoutExpress Parameters Are Introduced At The Same Time, TimeExpire Is The Criterion.
     * <p>
     * 绝对超时时间,格式为yyyy-MM-dd HH:mm. 注:1）以支付宝系统时间为准；2）
     * 如果和timeoutExpress参数同时传入,以timeExpire为准
     *
     * @param timeExpire timeExpire
     * @return WapPayParamChain
     */
    public WapPayParamChain builderTimeExpire(String timeExpire) {
        alipayTradeWapPayModel.setTimeExpire(timeExpire);
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
    public WapPayParamChain builderTotalAmount(String totalAmount) {
        alipayTradeWapPayModel.setTotalAmount(totalAmount);
        return this;
    }

    /**
     * The User Authorization Interface Is Used To Identify The User's Authorization Relationship Note When Obtaining
     * The Relevant Data Of Users.
     * If It Does Not Belong To The Business Contract Manager Provided by AliPay Business Manager,
     * The Function Will Not Be Provided Temporarily, And The Parameter Is Invalid.
     * <p>
     * 针对用户授权接口,获取用户相关数据时,
     * 用于标识用户授权关系注:若不属于支付宝业务经理提供签约服务的商户,
     * 暂不对外提供该功能,该参数使用无效
     *
     * @param authToken authToken
     * @return WapPayParamChain
     */
    public WapPayParamChain builderAuthToken(String authToken) {
        alipayTradeWapPayModel.setAuthToken(authToken);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * Product Code, The Product Code Signed By AliPay And Merchants.
     * Please Fill In The Fixed Value For This Product:QUICK_WAP_WAY
     * <p>
     * !!这是不可缺参数
     * 销售产品码,商家和支付宝签约的产品码.该产品请填写固定值:QUICK_WAP_WAY
     *
     * @param productCode productCode
     * @return WapPayParamChain
     */
    public WapPayParamChain builderProductCode(String productCode) {
        alipayTradeWapPayModel.setProductCode(productCode);
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
     * @return AppPayParamChain
     */
    public WapPayParamChain builderGoodsType(String goodsType) {
        alipayTradeWapPayModel.setGoodsType(goodsType);
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
    public WapPayParamChain builderPassBackParams(String passBackParams) {
        alipayTradeWapPayModel.setPassbackParams(passBackParams);
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
    public WapPayParamChain builderPromoParams(String promoParams) {
        alipayTradeWapPayModel.setPromoParams(promoParams);
        return this;
    }

    /**
     * Business Extension Parameters ,Document Address https://docs.open.alipay.com/203/107090/
     * <p>
     * 业务扩展参数,文档地址 https://docs.open.alipay.com/203/107090/
     *
     * @param extendParams extendParams
     * @return AppPayParamChain
     */
    public WapPayParamChain builderExtendParams(ExtendParams extendParams) {
        alipayTradeWapPayModel.setExtendParams(extendParams);
        return this;
    }

    /**
     * Available Channels, Users Can Only Pay Within The Specified Channels
     * Use "," To Separate When There Are Multiple Channels
     * Note: mutually exclusive with DisablePayChannels,
     * Document Address https://docs.open.alipay.com/203/107090/
     * <p>
     * 可用渠道,用户只能在指定渠道范围内支付
     * 当有多个渠道时用“,”分隔
     * 注:与 DisablePayChannels 互斥,
     * 文档地址 https://docs.open.alipay.com/203/107090/
     *
     * @param enablePayChannels enablePayChannels
     * @return AppPayParamChain
     */
    public WapPayParamChain builderEnablePayChannels(String enablePayChannels) {
        alipayTradeWapPayModel.setEnablePayChannels(enablePayChannels);
        return this;
    }

    /**
     * Disable Channel, User Can't Pay Through Specified Channel
     * Use "," to Separate When There Are Multiple Channels
     * Note: Mutually Exclusive With EnablePayChannels,
     * Document Address https://docs.open.alipay.com/203/107090/
     * <p>
     * 禁用渠道,用户不可用指定渠道支付
     * 当有多个渠道时用“,”分隔
     * 注:与 EnablePayChannels 互斥,
     * 文档地址 https://docs.open.alipay.com/203/107090/
     *
     * @param disablePayChannels disablePayChannels
     * @return AppPayParamChain
     */
    public WapPayParamChain builderDisablePayChannels(String disablePayChannels) {
        alipayTradeWapPayModel.setDisablePayChannels(disablePayChannels);
        return this;
    }

    /**
     * After Adding This Parameter, A Return Button Will Appear At The H5 Payment And Cash Register,
     * Which Can Be Used For Users To Withdraw From Payment And Return To The Merchant Website Address Specified By This
     * parameter.
     * Note: This Parameter Does Not Take Effect On The Jump Of AliPay Wallet Standard Cash Register.
     * <p>
     * 添加该参数后在h5支付收银台会出现返回按钮,
     * 可用于用户付款中途退出并返回到该参数指定的商户网站地址.
     * 注:该参数对支付宝钱包标准收银台下的跳转不生效
     *
     * @param quitUrl quitUrl
     * @return WapPayParamChain
     */
    public WapPayParamChain builderQuitUrl(String quitUrl) {
        alipayTradeWapPayModel.setQuitUrl(quitUrl);
        return this;
    }

    /**
     * External Designated Buyer, Document Address https://docs.open.alipay.com/203/107090/
     * 外部指定买家,文档地址 https://docs.open.alipay.com/203/107090/
     *
     * @param extUserInfo extUserInfo
     * @return AppPayParamChain
     */
    public WapPayParamChain builderExtUserInfo(ExtUserInfo extUserInfo) {
        alipayTradeWapPayModel.setExtUserInfo(extUserInfo);
        return this;
    }
}
