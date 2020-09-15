package com.luna.jpa.test;

import com.alibaba.fastjson.JSON;
import com.luna.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @Package: com.luna.jpa.test
 * @ClassName: PageTest
 * @Author: luna
 * @CreateTime: 2020/9/8 20:39
 * @Description:
 */
public class PageTest {

    @Test
    public void atest() {
        EntityManager entityManager = JpaUtils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 查询全部
        // 创建Query查询对象 执行jpql 对象
        String jpql = "select name,password From User";
        Query query = entityManager.createQuery(jpql);
        // 从0开始
        query.setFirstResult(0);
        // 查询2条
        query.setMaxResults(2);
        List resultList = query.getResultList();
        resultList.forEach(user -> {
            System.out.println(JSON.toJSONString(user));
        });
        // 提交事务
        transaction.commit();
        // 释放资源
        entityManager.close();
    }

    @Test
    public void btest() {
        EntityManager entityManager = JpaUtils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 查询全部
        // 创建Query查询对象 执行jpql 对象
        String jpql = "select name,password From User where name like ?1";
        Query query = entityManager.createQuery(jpql);
        // 从0开始
        query.setFirstResult(0);
        // 查询2条
        query.setMaxResults(2);

        query.setParameter(1, "张%");

        List resultList = query.getResultList();
        resultList.forEach(user -> {
            System.out.println(JSON.toJSONString(user));
        });
        // 提交事务
        transaction.commit();
        // 释放资源
        entityManager.close();
    }

}
