package com.luna.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @Package: com.luna.jpa.utils
 * @ClassName: JpaUtils
 * @Author: luna
 * @CreateTime: 2020/9/8 15:48
 * @Description: 实力资源管理器耗时
 * 程序第一次访问时候,创建manager管理
 */
public class JpaUtils {

    private static EntityManagerFactory entityManagerFactory;

    static {
        // 1. 加载配置文件
        // 2. 根据文件数据创建entityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("luna-jpa");

    }

    /**
     * 获取EntityManager对象
     * 
     * @return
     */
    public static EntityManager entityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
