package com.luna.mongodb.demo;

import java.util.Date;

public class Person {
    /**
     * 主键
     */
    private Long    id;

    /**
     * 名字
     */
    private String  name;

    /**
     * 国家
     */
    private String  country;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date    birthday;

    /**
     * 介绍
     */
    private String  remark;

    public Person() {}

    public Person(Long id, String name, String country, Integer age, Date birthday, String remark) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.age = age;
        this.birthday = birthday;
        this.remark = remark;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
