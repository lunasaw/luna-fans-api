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

package io.github.lunasaw.alipay.container;

/**
 * ClassName: PayClientConstant
 * Description:
 * date: 2019/12/24 16:54
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public enum PayClientConstant {
    /**
     * AliPay Online Environment Gateway
     * <p>
     * AliPay线上环境网关
     */
    PAY_GATEWAY("https://openapi.alipay.com/gateway.do"),
    /**
     * AliPay Sandbox Environment Gateway
     * <p>
     * AliPay沙箱环境网关
     */
    DEV_PAY_GATEWAY("https://openapi.alipaydev.com/gateway.do"),
    /**
     * Support ONLY JSON
     * <p>
     * 仅支持JSON
     */
    PAY_FORMAT("JSON"),
    /**
     * Encoding Format Requested, Default UTF-8
     * <p>
     * 请求使用的编码格式,默认UTF-8
     */
    PAY_CHARSET("UTF-8"),
    /**
     * Signature Algorithm, Default RSA2
     * <p>
     * 签名算法,默认RSA2
     */
    PAY_SIGN_TYPE("RSA2");

    private String value;

    PayClientConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
