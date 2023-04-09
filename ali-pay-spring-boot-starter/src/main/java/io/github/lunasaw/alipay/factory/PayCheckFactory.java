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

package io.github.lunasaw.alipay.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import io.github.lunasaw.alipay.container.PayCheckFactoryContainer;
import io.github.lunasaw.alipay.container.PayClientConstant;

/**
 * ClassName: PayCheckFactory
 * Description:
 * date: 2019/12/25 19:29
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class PayCheckFactory {
    private PayCheckFactory() {}

    /**
     * Call This Method Before Using {@linkplain #check(Map, String)}
     * Response Parameter Document Address https://docs.open.alipay.com/api_1/alipay.trade.page.pay/
     * <p>
     * 使用 {@linkplain #check(Map, String)} 之前,请先调用此方法
     * 响应参数,文档地址 https://docs.open.alipay.com/api_1/alipay.trade.page.pay/
     *
     * @param parameterMap Feedback From AliPay: For Example HttpServletRequest request.getParameterMap()
     * 支付宝的反馈信息:例如 HttpServletRequest request.getParameterMap()
     * @return Map
     */
    public static Map<String, String> reload(Map<String, String[]> parameterMap) {
        Map<String, String> map = new ConcurrentHashMap<>(PayCheckFactoryContainer.MAP_DEFAULT_SIZE);
        parameterMap
            .forEach((name, values) -> map.put(name, String.join(PayCheckFactoryContainer.RELOAD_JOIN, values)));
        return map;
    }

    /**
     * The Signature Verification Is A One-Time Signature Verification.
     * Please Conduct The Second Signature Verification In Combination With The Business
     * 1. The Merchant Needs To Verify Whether The Out Trade No In The Notification Data Is The Order Number Created In
     * The Merchant System,
     * 2. Determine Whether The Total Amount Is The Actual Amount Of The Order (That Is, The Amount When The Merchant
     * Order Is Created),
     * 3. Verify That The seller_id (or seller_email)
     * In The Notice Is The Corresponding Operator Of The out_trade_no Document
     * (Sometimes, A Merchant May Have Multiple seller_id/seller_email),
     * 4. Verify Whether The app_id Is The Merchant Itself.
     * If Any Of The Above 1, 2, 3 And 4 Fails To Pass The Verification, It Indicates That This Notification Is An
     * Exception Notification And Must Be Ignored.
     * After The Above Verification Is Passed, Merchants Must Correctly Conduct Different Business Processes According
     * To Different Types Of Business Notifications of AliPay,
     * And Filter Repeated Notification Of The Result Data.
     * In AliPay's Business Notification,
     * AliPay Will Only Be Deemed Successful As A Buyer When The Transaction Notification State Is TRADE_SUCCESS Or
     * TRADE_FINISHED.
     * <p>
     * 验签为一次验签,请结合业务二次验签
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号,
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）,
     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方
     * （有的时候,一个商户可能有多个seller_id/seller_email）,
     * 4、验证app_id是否为该商户本身.
     * 上述1、2、3、4有任何一个验证不通过,则表明本次通知是异常通知,务必忽略.
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知,
     * 正确的进行不同的业务处理,并且过滤重复的通知结果数据.
     * 在支付宝的业务通知中,
     * 只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时,支付宝才会认定为买家付款成功.
     *
     * @param reloadMap Come From {@linkplain #reload(Map)}
     * 来自 {@linkplain #reload(Map)}
     * @param publicKey publicKey
     * 支付宝公钥
     * @return boolean
     * @throws AlipayApiException AlipayApiException
     */
    public static boolean check(Map<String, String> reloadMap, String publicKey) throws AlipayApiException {
        return AlipaySignature.rsaCheckV1(reloadMap, publicKey,
            PayClientConstant.PAY_CHARSET.getValue(),
            PayClientConstant.PAY_SIGN_TYPE.getValue());
    }
}
