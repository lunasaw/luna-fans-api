package com.luna.api.xiaoma.dto;

/**
 * @Package: com.luna.api.xiaoma.dto
 * @ClassName: LinkDTO
 * @Author: luna
 * @CreateTime: 2020/9/28 11:20
 * @Description:
 */
public class LinkDTO {

    /** 短链接名称 */
    private String name;

    /** 跳转链接URL */
    private String originUrl;

    /** 短链接URL */
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
