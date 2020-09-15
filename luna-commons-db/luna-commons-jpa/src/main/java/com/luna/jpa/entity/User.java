package com.luna.jpa.entity;

import com.luna.jpa.entity.base.AbstractAuditModel;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @package: com.luna.orm.jpa.entity
 * @description: 用户实体类
 * @author: yangkai.shen
 * @date: Created in 2018/11/7 14:06
 * @copyright: Copyright (c)
 * @version: V1.0
 * @modified: 76peter
 */
// 声明此类是一个实体类
@Entity
// 实体类和表的映射关系
@Table(name = "orm_user")
public class User extends AbstractAuditModel {
    /**
     * 用户名
     */
    @Column(name = "name")
    private String                 name;

    /**
     * 加密后的密码
     */
    @Column(name = "password")
    private String                 password;

    /**
     * 加密使用的盐
     */
    @Column(name = "salt")
    private String                 salt;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String                 email;

    /**
     * 手机号码
     */
    @Column(name = "phone_number")
    private String                 phoneNumber;

    /**
     * 状态，-1：逻辑删除，0：禁用，1：启用
     */
    @Column(name = "status")
    private Integer                status;

    /**
     * 上次登录时间
     */
    @Column(name = "last_login_time")
    private Date                   lastLoginTime;

    /**
     * 关联部门表
     * 1、关系维护端，负责多对多关系的绑定和解除
     * 2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(User)
     * 3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(Department)
     * 4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
     * 即表名为user_department
     * 关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id,这里使用referencedColumnName指定
     * 关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,department_id
     * 主表就是关系维护端对应的表，从表就是关系被维护端对应的表
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "orm_user_dept", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "dept_id", referencedColumnName = "id"))
    private Collection<Department> departmentList;

    // 配置客户和联系人之间的关系（一对多关系）
    /**
     * 使用注解的形式配置多表关系
     * 1.声明关系
     * 
     * @OneToMany : 配置一对多关系
     * targetEntity ：对方对象的字节码对象
     * 2.配置外键（中间表）
     * @JoinColumn : 配置外键
     * name：外键字段名称
     * referencedColumnName：参照的主表的主键字段名称
     *
     * * 在客户实体类上（一的一方）添加了外键了配置，所以对于客户而言，也具备了维护外键的作用
     *
     */

    /**
     * 放弃外键维护权
     * mappedBy：对方配置关系的属性名称\
     * cascade : 配置级联（可以配置到设置多表的映射关系的注解上）
     * CascadeType.all : 所有
     * MERGE ：更新
     * PERSIST ：保存
     * REMOVE ：删除
     *
     * fetch : 配置关联对象的加载方式
     * EAGER ：立即加载
     * LAZY ：延迟加载
     * 
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @OneToMany(targetEntity = LinkMan.class)
    // @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<Contacts>          contacts = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Collection<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(Collection<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Set<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contacts> contacts) {
        this.contacts = contacts;
    }
}
