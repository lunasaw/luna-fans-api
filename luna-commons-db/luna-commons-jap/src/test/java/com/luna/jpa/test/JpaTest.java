package com.luna.jpa.test;

import com.luna.common.utils.md5.Md5Utils;
import com.luna.jpa.entity.User;
import com.luna.jpa.utils.JpaUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

/**
 * @Package: com.luna.jpa.test
 * @ClassName: JpaTest
 * @Author: luna
 * @CreateTime: 2020/9/8 15:42
 * @Description:
 */
public class JpaTest<main> {

    /**
     * 得到实体管理类工厂对象
     * 获取管理器
     * <p>
     * 开启事务
     * <p/>
     * 完成操作
     * 提交事务,或者回滚
     * 关闭释放资源
     */
    @Test
    public void atest() {
        // // 加载配置文件创建工厂对象
        // EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("luna-jpa");
        // // 通过工厂创建实体管理器
        // EntityManager entityManager = entityManagerFactory.createEntityManager();
        // // 获取事务对象
        // EntityTransaction transaction = entityManager.getTransaction();
        EntityManager entityManager = JpaUtils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();
        // 完成增删改查操作
        User testSave3 = new User();
        testSave3.setName("张三");
        testSave3.setPassword(Md5Utils.md5("czy1024"));
        testSave3.setSalt("czy");
        testSave3.setStatus(1);
        testSave3.setPhoneNumber("1999999999");
        testSave3.setEmail("testSave3@xkcoding.com");
        testSave3.setCreateTime(new Date());
        testSave3.setLastUpdateTime(new Date());
        entityManager.persist(testSave3);
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void btest() {
        EntityManager entityManager = JpaUtils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();
        // 完成增删改查操作
        User user = entityManager.find(User.class, 1L);
        transaction.commit();
        Assert.assertNotNull(user);
        entityManager.close();
    }

    @Test
    public void ctest() {
        EntityManager entityManager = JpaUtils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();
        // 完成增删改查操作 getReference 获取动态代理对象 这个方法不会立即调用sql查询,当调用查询结果对象的时候才会发送sql
        // 延迟加载 或者懒加载,得到的是一个动态代理加载
        User user = entityManager.getReference(User.class, 1L);
        transaction.commit();
        Assert.assertNotNull(user);
        entityManager.close();
    }

    @Test
    public void dtest() {
        EntityManager entityManager = JpaUtils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();
        // 完成增删改查操作
        User user = entityManager.find(User.class, 1L);
        Assert.assertNotNull(user);
        entityManager.remove(user);
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void etest() {
        EntityManager entityManager = JpaUtils.entityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();
        // 完成增删改查操作
        User user = entityManager.find(User.class, 2L);
        user.setName("李四");
        entityManager.merge(user);
        transaction.commit();
        entityManager.close();
    }

}
