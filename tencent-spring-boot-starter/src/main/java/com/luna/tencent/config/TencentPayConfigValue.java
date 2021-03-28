package com.luna.tencent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Package: com.luna.tencent.config
 * @ClassName: TencentPayConfigValue
 * @Author: luna
 * @CreateTime: 2020/8/16 14:18
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "luna.weixin")
public class TencentPayConfigValue {

    private String appid;

    private String partner;

    /** 密钥 */
    private String partnerkey;

    private String notifyurl;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPartnerkey() {
        return partnerkey;
    }

    public void setPartnerkey(String partnerkey) {
        this.partnerkey = partnerkey;
    }

    public String getNotifyurl() {
        return notifyurl;
    }

    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
    }
}
