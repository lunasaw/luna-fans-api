package com.luna.elasticsearch.demo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Person
 *
 * @author luna
 * @version v1.0
 * @since 2019/9/15 23:04
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 8510634155374943623L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 国家
     */
    private String country;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 介绍
     */
    private String remark;




    public Person() {
    }

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

    public static final class Builder {
        private Long id;
        private String name;
        private String country;
        private Integer age;
        private Date birthday;
        private String remark;

        private Builder() {
        }

        public static Builder aPerson() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.setId(id);
            person.setName(name);
            person.setCountry(country);
            person.setAge(age);
            person.setBirthday(birthday);
            person.setRemark(remark);
            return person;
        }
    }
}


