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

package com.luna.ali.alipay.factory;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.luna.ali.alipay.container.PayClientConstant;
import com.luna.ali.alipay.pay.DefaultPayChain;

/**
 * ClassName: PayRootChainFactory
 * Description:
 * date: 2019/12/24 16:49
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class PayRootChainFactory {

    /**
     * Create Online Environment Pay Chain For, Default Parameters, See
     * {@linkplain PayClientConstant PayClientConstant}
     * <p>
     * 创建线上环境支付链,默认参数请参见
     * {@linkplain PayClientConstant PayClientConstant}
     *
     * @param appId Application ID allocated to developers by AliPay
     * 支付宝分配给开发者的应用ID
     * @param privateKey privateKey
     * 应用私钥
     * @param publicKey publicKey
     * 支付宝公钥
     * @return DefaultPayChain
     */
    public static DefaultPayChain createdPayChain(String appId, String privateKey, String publicKey) {
        return new DefaultPayChain(new DefaultAlipayClient(PayClientConstant.PAY_GATEWAY.getValue(), appId, privateKey,
            PayClientConstant.PAY_FORMAT.getValue(), PayClientConstant.PAY_CHARSET.getValue(), publicKey,
            PayClientConstant.PAY_SIGN_TYPE.getValue()));
    }

    /**
     * Create Sandbox Environment Pay Chain For, Default Parameters, See
     * {@linkplain PayClientConstant PayClientConstant}
     * <p>
     * 创建沙箱环境支付链,默认参数请参见
     * {@linkplain PayClientConstant PayClientConstant}
     *
     * @param appId Application ID allocated to developers by AliPay
     * 支付宝分配给开发者的应用ID
     * @param privateKey privateKey
     * 应用私钥
     * @param publicKey publicKey
     * 支付宝公钥
     * @return DefaultPayChain
     */
    public static DefaultPayChain createdDevPayChain(String appId, String privateKey, String publicKey) {
        return new DefaultPayChain(new DefaultAlipayClient(PayClientConstant.DEV_PAY_GATEWAY.getValue(), appId,
            privateKey, PayClientConstant.PAY_FORMAT.getValue(), PayClientConstant.PAY_CHARSET.getValue(), publicKey,
            PayClientConstant.PAY_SIGN_TYPE.getValue()));
    }

    /**
     * Create A Custom Payment Chain Suggested Use
     * {@linkplain DefaultAlipayClient DefaultAlipayClient}
     * <p>
     * 创建自定义支付链,建议使用
     * {@linkplain DefaultAlipayClient DefaultAlipayClient}
     *
     * @param payClient payClient
     * @return DefaultPayChain
     */
    public static DefaultPayChain createdPayChain(AlipayClient payClient) {
        return new DefaultPayChain(payClient);
    }
}
