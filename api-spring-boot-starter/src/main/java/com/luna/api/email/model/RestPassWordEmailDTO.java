package com.luna.api.email.model;

/**
 * @author chenzhangyue
 * 2023/3/6
 */
public class RestPassWordEmailDTO {

    /**
     * 接收用户名昵称
     */
    private String userNick;
    /**
     * 接收地址
     */
    private String email;
    /**
     * 接收用户账号
     */
    private String account;
    /**
     * 接收用户名密码
     */
    private String newPassword;
    /**
     * 发送用户名
     */
    private String fromName;
    /**
     * 发送用户密码
     */
    private String fromNick;
}
