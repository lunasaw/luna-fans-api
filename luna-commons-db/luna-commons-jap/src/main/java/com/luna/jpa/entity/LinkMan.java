package com.luna.jpa.entity;

import com.luna.jpa.entity.base.AbstractAuditModel;
import lombok.*;

import javax.persistence.*;

/**
 * @Package: com.luna.jpa.entity
 * @ClassName: LinkMan
 * @Author: luna
 * @CreateTime: 2020/9/10 21:42
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
// 声明此类是一个实体类
@Entity
// 实体类和表的映射关系
@Table(name = "orm_user_linkman")
@ToString(callSuper = true)
public class LinkMan extends AbstractAuditModel {

    @Column(name = "lkm_name")
    private String lkmName;    // 联系人姓名
    @Column(name = "lkm_gender")
    private String lkmGender;  // 联系人性别
    @Column(name = "lkm_phone")
    private String lkmPhone;   // 联系人办公电话
    @Column(name = "lkm_mobile")
    private String lkmMobile;  // 联系人手机
    @Column(name = "lkm_email")
    private String lkmEmail;   // 联系人邮箱
    @Column(name = "lkm_position")
    private String lkmPosition;// 联系人职位
    @Column(name = "lkm_memo")
    private String lkmMemo;    // 联系人备注

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
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "orm_user_id", referencedColumnName = "id")
    private User   user;
}
