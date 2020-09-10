package com.luna.jpa.test;

import com.alibaba.fastjson.JSON;
import com.luna.jpa.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.luna.jpa.repository.UserDao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Package: com.luna.jpa.test
 * @ClassName: SpringDataJpaTest2
 * @Author: luna
 * @CreateTime: 2020/9/9 13:41
 * @Description:
 */
public class SpringDataJpaTest2 extends SpringDataJpaTest1 {

    @Autowired
    UserDao userDAO;

    /**
     * 事务控制
     * getOne 延迟加载
     * findOne 立即加载
     */
    @Test
    @Transactional
    public void atest() {
        User one = userDAO.getOne(3L);
        System.out.println(JSON.toJSONString(one));
    }

    @Test
    public void btest() {
        Optional<User> byId = userDAO.findById(3L);
        User byJpql = userDAO.findByJpql(byId.get().getName());
        System.out.println(JSON.toJSONString(byJpql));
    }

    @Test
    public void ctest() {
        User testSave3 = userDAO.findByJpql2("testSave3", 3L);
        System.out.println(JSON.toJSONString(testSave3));
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void dtest() {
        int jpql3 = userDAO.findByJpql3("jpql修改名称", 3L);
        System.out.println(JSON.toJSONString(jpql3));
    }

    @Test
    public void etest() {
        List<Object[]> byJpql4 = userDAO.findByJpql4();
        byJpql4.forEach(objects -> {
            Arrays.toString(objects);
        });
    }

    @Test
    public void ftest() {
        List<Object[]> byJpql4 = userDAO.findByJpql5("test");
        byJpql4.forEach(objects -> {
            Arrays.toString(objects);
        });
    }

    @Test
    public void gtest() {
        User testSave3 = userDAO.findByNameAndAndId("testSave4", 6L);
        System.out.println(JSON.toJSONString(testSave3));
    }

    @Test
    public void htest() {
        List<User> testSave = userDAO.findByNameLike("testSave%");
        for (User user : testSave) {
            System.out.println(JSON.toJSONString(user));
        }
    }
}
