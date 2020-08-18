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

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;

/**
 * ClassName: PagePayChain
 * Description:
 * date: 2019/12/24 21:32
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */

public class PagePayChain {
    private AlipayClient            alipayClient;
    private AlipayTradePagePayModel alipayTradePagePayModel;

    public PagePayChain() {}

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

    public PagePayChain(AlipayClient alipayClient, AlipayTradePagePayModel alipayTradePagePayModel) {
        this.alipayClient = alipayClient;
        this.alipayTradePagePayModel = alipayTradePagePayModel;
    }

    /**
     * Generate Payment Page
     * <p>
     * 生成支付页面
     *
     * @param returnUrl After the transaction is completed, the page will take the initiative to jump to the HTTP /
     * HTTPS path specified in the merchant server
     * 交易完成后页面主动跳转,商户服务器里指定的页面http/https路径
     * @param notifyUrl AliPay Server Initiatively Tells The Http/Https Path Specified In The Merchant Server.
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径
     * @return pay
     * @throws AlipayApiException AlipayApiException
     */
    public String pay(String returnUrl, String notifyUrl) throws AlipayApiException {
        AlipayTradePagePayRequest pagePayRequest = new AlipayTradePagePayRequest();
        pagePayRequest.setReturnUrl(returnUrl);
        pagePayRequest.setNotifyUrl(notifyUrl);
        pagePayRequest.setBizModel(alipayTradePagePayModel);
        return alipayClient.pageExecute(pagePayRequest).getBody();
    }

    /**
     * Custom Build PayRequest
     * <p>
     * 自定义构建PayRequest
     *
     * @param pagePayRequest pagePayRequest
     * @return String
     * @throws AlipayApiException AlipayApiException
     */
    public String pay(AlipayTradePagePayRequest pagePayRequest) throws AlipayApiException {
        pagePayRequest.setBizModel(alipayTradePagePayModel);
        return alipayClient.pageExecute(pagePayRequest).getBody();
    }
}
