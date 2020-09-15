package com.luna.jpa.entity;

import com.luna.jpa.entity.base.AbstractAuditModel;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * <p>
 * 部门实体类
 * </p>
 *
 * @package: com.luna.orm.jpa.entity
 * @description: 部门实体类
 * @author: 76peter
 * @date: Created in 2019/10/1 18:07
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: 76peter
 */
@Entity
@Table(name = "orm_department")
public class Department extends AbstractAuditModel {

    /**
     * 部门名
     */
    @Column(name = "name", columnDefinition = "varchar(255) not null")
    private String                 name;

    /**
     * 上级部门id
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "superior", referencedColumnName = "id")
    private Department             superior;
    /**
     * 所属层级
     */
    @Column(name = "levels", columnDefinition = "int not null default 0")
    private Integer                levels;
    /**
     * 排序
     */
    @Column(name = "order_no", columnDefinition = "int not null default 0")
    private Integer                orderNo;
    /**
     * 子部门集合
     */
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "superior")
    private Collection<Department> children = new HashSet<>();

    /**
     * 部门下用户集合
     */
    @ManyToMany(mappedBy = "departmentList")
    private Collection<User>       userList = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getSuperior() {
        return superior;
    }

    public void setSuperior(Department superior) {
        this.superior = superior;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Collection<Department> getChildren() {
        return children;
    }

    public void setChildren(Collection<Department> children) {
        this.children = children;
    }

    public Collection<User> getUserList() {
        return userList;
    }

    public void setUserList(Collection<User> userList) {
        this.userList = userList;
    }

    public Department() {}

}
