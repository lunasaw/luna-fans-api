package com.luna.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Contacts)实体类
 *
 * @author luna
 * @since 2020-09-14 10:35:31
 */
public class Contacts implements Serializable {
    private static final long serialVersionUID = 794465611371681708L;
    /**
     * 联系人编号(主键)
     */

    private Long              id;
    /**
     * 联系人姓名
     */

    private String            name;
    /**
     * 联系人性别
     */

    private String            gender;
    /**
     * 联系人办公电话
     */

    private String            phone;
    /**
     * 联系人手机
     */

    private String            mobile;
    /**
     * 联系人邮箱
     */

    private String            email;
    /**
     * 联系人职位
     */

    private String            position;
    /**
     * 联系人备注
     */

    private String            memo;
    /**
     * 客户id(外键)
     */

    private Integer           userId;
    /**
     * 创建时间
     */

    private Date              createTime;
    /**
     * 上次更新时间
     */

    private Date              lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}