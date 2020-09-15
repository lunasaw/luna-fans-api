package com.luna.jpa.entity;

import com.luna.jpa.entity.base.AbstractAuditModel;

import javax.persistence.*;

@Entity
@Table(name = "orm_contacts")
public class Contacts extends AbstractAuditModel {

    @Column(name = "name")
    private String name;
    /** 联系人姓名 */
    @Column(name = "gender")
    private String gender;
    /** 联系人性别 */
    @Column(name = "phone")
    private String phone;
    /** 联系人办公电话 */
    @Column(name = "mobile")
    private String mobile;
    /** 联系人手机 */
    @Column(name = "email")
    private String email;
    /** 联系人邮箱 */
    @Column(name = "position")
    private String position;
    @Column(name = "memo")
    private String memo;
    /** 联系人备注 */

    /**
     * 配置联系人到客户的多对一关系
     * 使用注解的形式配置多对一关系
     * 1.配置表关系
     * 
     * @ManyToOne : 配置多对一关系
     * targetEntity：对方的实体类字节码
     * 2.配置外键（中间表）
     *
     * * 配置外键的过程，配置到了多的一方，就会在多的一方维护外键
     *
     */
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User   user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
