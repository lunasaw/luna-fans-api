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

package com.luna.ali.alipay.container;

/**
 * ClassName: PayParamConstant
 * Description:
 * date: 2019/12/24 17:51
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public enum PayParamConstant {
    /**
     * Product Code, Product Code Signed By AliPay And Merchants,
     * Fixed Value QUICK_MSECURITY_PAY
     * <p>
     * 销售产品码，商家和支付宝签约的产品码,
     * 为固定值 QUICK_MSECURITY_PAY
     */
    APP_PAY_PARAM_PRODUCT_CODE("QUICK_MSECURITY_PAY"),
    /**
     * Product Code, The Product Code Signed By AliPay And Merchants.
     * Please Fill In The Fixed Value Of The Product: QUICK_WAP_WAY
     * <p>
     * 销售产品码，商家和支付宝签约的产品码.
     * 该产品请填写固定值：QUICK_WAP_WAY
     */
    WAP_PAY_PARAM_PRODUCT_CODE("QUICK_WAP_WAY"),
    /**
     * Sell Product Code, Name Of Product Code Signed With AliPay.
     * Note: Currently Only Supported:FAST_INSTANT_TRADE_PAY
     * <p>
     * 销售产品码，与支付宝签约的产品码名称.
     * 注：目前仅支持FAST_INSTANT_TRADE_PAY
     */
    PAGE_PAT_PARAM_PRODUCT_CODE("FAST_INSTANT_TRADE_PAY");

    private String value;

    PayParamConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
