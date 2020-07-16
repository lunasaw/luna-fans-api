package com.luna.ali.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.luna.common.utils.text.CharsetKit;

/**
 * 应用启动加载文件
 *
 * @author Louis
 * @date Dec 12, 2018
 */
@Component
@ConfigurationProperties(prefix = "luna.alipay")
public class AlipayConfigValue {

    private String       appId;

    private String       privateKey;

    private String       publicKey;

    private String       notifyUrl;

    private String       returnUrl;

    private String       signType;

    private String       gatewayUrl;

    private AlipayClient alipayClient;

    public AlipayClient getAlipayClient() {
        if (alipayClient == null) {
            this.alipayClient =
                new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", CharsetKit.UTF_8, publicKey, signType);
        }
        return alipayClient;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }
}
