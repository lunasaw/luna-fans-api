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

package io.github.lunasaw.alipay.pay.download.param;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import io.github.lunasaw.alipay.pay.download.PayDownloadQueryChain;

/**
 * ClassName: PayDownloadQueryParamChain
 * Description:
 * date: 2019/12/25 18:59
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */

public class PayDownloadQueryParamChain {
    private AlipayClient                                   alipayClient;
    private AlipayDataDataserviceBillDownloadurlQueryModel alipayDataDataserviceBillDownloadurlQueryModel;

    public AlipayClient getAlipayClient() {
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public AlipayDataDataserviceBillDownloadurlQueryModel getAlipayDataDataserviceBillDownloadurlQueryModel() {
        return alipayDataDataserviceBillDownloadurlQueryModel;
    }

    public void setAlipayDataDataserviceBillDownloadurlQueryModel(
        AlipayDataDataserviceBillDownloadurlQueryModel alipayDataDataserviceBillDownloadurlQueryModel) {
        this.alipayDataDataserviceBillDownloadurlQueryModel = alipayDataDataserviceBillDownloadurlQueryModel;
    }

    public PayDownloadQueryParamChain(AlipayClient alipayClient,
        AlipayDataDataserviceBillDownloadurlQueryModel alipayDataDataserviceBillDownloadurlQueryModel) {
        this.alipayClient = alipayClient;
        this.alipayDataDataserviceBillDownloadurlQueryModel = alipayDataDataserviceBillDownloadurlQueryModel;
    }

    /**
     * Builder PayDownloadQueryChain
     * <p>
     * 构建 PayDownloadQueryChain
     *
     * @return PayDownloadQueryChain
     */
    public PayDownloadQueryChain builder() {
        return new PayDownloadQueryChain(alipayClient, alipayDataDataserviceBillDownloadurlQueryModel);
    }

    /**
     * !!This Is An Indispensable Parameter
     * The Type Of Bill, The Merchant Through The Interface Or Merchant Authorized By The Open Platform,
     * Its Service Providers Through The Interface Can Get The Following Types Of Bills:
     * trade, signcustomer;
     * trade Refers To The Business Account Receipts Based On The AliPay Transaction;
     * signcustomer Refers To The Account Receipts Based On The Changes In The AliPay Balance Income And Expenditure.
     * <p>
     * !!这是不可缺参数
     * 账单类型,
     * 商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型:
     * trade、signcustomer；
     * trade指商户基于支付宝交易收单的业务账单；
     * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单.
     *
     * @param billType billType
     * @return PayDownloadQueryParamChain
     */
    public PayDownloadQueryParamChain builderBillType(String billType) {
        alipayDataDataserviceBillDownloadurlQueryModel.setBillType(billType);
        return this;
    }

    /**
     * !!This Is An Indispensable Parameter
     * Bill Time: The Format Of Daily Bill Is yyyy-MM-dd,
     * And The Earliest Daily Bill From January 1, 2016 Can Be Downloaded;
     * The Format Of Monthly Bill Is yyyy-MM, And The Earliest Monthly Bill From January 2016 Can Be Downloaded.
     * <p>
     * !!这是不可缺参数
     * 账单时间:日账单格式为yyyy-MM-dd,最早可下载2016年1月1日开始的日账单；
     * 月账单格式为yyyy-MM,最早可下载2016年1月开始的月账单.
     *
     * @param billDate billDate
     * @return PayDownloadQueryParamChain
     */
    public PayDownloadQueryParamChain builderBillDate(String billDate) {
        alipayDataDataserviceBillDownloadurlQueryModel.setBillDate(billDate);
        return this;
    }
}
