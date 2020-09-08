package com.luna.jpa.test;

import com.luna.jpa.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @Package: com.luna.jpa.test
 * @ClassName: JpqlTest
 * @Author: luna
 * @CreateTime: 2020/9/8 20:14
 * @Description:
 */
public class JpqlTest {

    /**
     * sql
     * jpql
     */
    @Test
    public void atest() {
        EntityManager entityManager = JpaUtils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 查询全部
        // 创建Query查询对象 执行jpql 对象
        String jpql = "select name from User";
        Query query = entityManager.createQuery(jpql);
        List resultList = query.getResultList();
        resultList.forEach(user -> {
            System.out.println(user);
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
        String jpql = "select name from User order by id DESC";
        Query query = entityManager.createQuery(jpql);
        List resultList = query.getResultList();
        resultList.forEach(user -> {
            System.out.println(user);
        });
        // 提交事务
        transaction.commit();
        // 释放资源
        entityManager.close();
    }

    @Test
    public void ctest() {
        EntityManager entityManager = JpaUtils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 查询全部
        // 创建Query查询对象 执行jpql 对象
        String jpql = "select count (id) from User ";
        Query query = entityManager.createQuery(jpql);
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);
        // 提交事务
        transaction.commit();
        // 释放资源
        entityManager.close();
    }
}
