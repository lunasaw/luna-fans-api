package com.luna.cache.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Spring Boot Demo Orm 系列示例表(Department)实体类
 *
 * @author luna
 * @since 2020-09-14 13:19:31
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 647092422317686616L;
    /**
     * 主键
     */

    private Integer           id;
    /**
     * 部门名称
     */

    private String            name;
    /**
     * 上级id
     */

    private Integer           superior;
    /**
     * 层级
     */

    private Integer           levels;
    /**
     * 排序
     */

    private Integer           orderNo;
    /**
     * 创建时间
     */

    private Date              createTime;
    /**
     * 上次更新时间
     */

    private Date              lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSuperior() {
        return superior;
    }

    public void setSuperior(Integer superior) {
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