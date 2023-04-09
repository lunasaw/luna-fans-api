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

package io.github.lunasaw.alipay.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;

/**
 * ClassName: AppPayChain
 * Description:
 * date: 2019/12/24 18:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class AppPayChain {
    private AlipayClient           alipayClient;
    private AlipayTradeAppPayModel alipayTradeAppPayModel;

    public AppPayChain() {}

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

    public AppPayChain(AlipayClient alipayClient, AlipayTradeAppPayModel alipayTradeAppPayModel) {
        this.alipayClient = alipayClient;
        this.alipayTradeAppPayModel = alipayTradeAppPayModel;
    }

    /**
     * AliPay Server Initiatively Tells The Http/Https Path Specified In The Merchant Server.
     * <p>
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径
     *
     * @param notifyUrl notifyUrl
     * @return String
     * @throws AlipayApiException AlipayApiException
     */
    public String pay(String notifyUrl) throws AlipayApiException {
        AlipayTradeAppPayRequest payRequest = new AlipayTradeAppPayRequest();
        payRequest.setNotifyUrl(notifyUrl);
        payRequest.setBizModel(alipayTradeAppPayModel);
        return alipayClient.sdkExecute(payRequest).getBody();
    }

    /**
     * Custom Build PayRequest
     * <p>
     * 自定义构建PayRequest
     *
     * @param payRequest payRequest
     * @return String
     * @throws AlipayApiException AlipayApiException
     */
    public String pay(AlipayTradeAppPayRequest payRequest) throws AlipayApiException {
        payRequest.setBizModel(alipayTradeAppPayModel);
        return alipayClient.sdkExecute(payRequest).getBody();
    }
}
