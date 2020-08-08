package com.luna.message.entity;

import java.util.Map;

/**
 * @Package: com.luna.message.entity
 * @ClassName: ModelContentDTO
 * @Author: luna
 * @CreateTime: 2020/8/8 17:21
 * @Description:
 */
public class ModelContentDTO {

    /** logo的地址 */
    private String              logo;

    /** 接收者昵称 */
    private String              outerName;

    /** 按钮的信息 */
    private String              button;

    /** 按钮的地址 */
    private String              buttonSrc;

    /** 介绍 */
    private Map<String, String> contents;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOuterName() {
        return outerName;
    }

    public void setOuterName(String outerName) {
        this.outerName = outerName;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getButtonSrc() {
        return buttonSrc;
    }

    public void setButtonSrc(String buttonSrc) {
        this.buttonSrc = buttonSrc;
    }

    public Map<String, String> getContents() {
        return contents;
    }

    public void setContents(Map<String, String> contents) {
        this.contents = contents;
    }
}
