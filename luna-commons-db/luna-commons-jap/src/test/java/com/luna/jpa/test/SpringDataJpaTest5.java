package com.luna.jpa.test;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Sets;
import com.luna.jpa.JpaApplicationtTest;
import com.luna.jpa.entity.Department;
import com.luna.jpa.entity.User;
import com.luna.jpa.repository.DepartmentDao;
import com.luna.jpa.repository.UserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Package: com.luna.jpa.test
 * @ClassName: SpringDataJpaTest5
 * @Author: luna
 * @CreateTime: 2020/9/11 20:08
 * @Description:
 */
public class SpringDataJpaTest5 extends JpaApplicationtTest {

    private static final Logger log = LoggerFactory.getLogger(SpringDataJpaTest5.class);

    @Autowired
    private DepartmentDao       departmentDao;
    @Autowired
    private UserDao             userDao;

    /**
     * 测试保存 ,根节点
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testSave() {
        Collection<Department> objects = departmentDao.findDepartmentsByLevels(0);

        if (objects.size() == 0) {
            Department testSave1 = new Department();
            testSave1.setName("testSave1");
            testSave1.setOrderNo(0);
            testSave1.setLevels(0);
            testSave1.setSuperior(null);

            Department testSave1_1 = new Department();
            testSave1_1.setName("testSave1_1");
            testSave1_1.setOrderNo(0);
            testSave1_1.setLevels(1);
            testSave1_1.setSuperior(testSave1);

            Department testSave1_2 = new Department();
            testSave1_2.setName("testSave1_1");
            testSave1_2.setOrderNo(0);
            testSave1_2.setLevels(1);
            testSave1_2.setSuperior(testSave1);

            Department testSave1_1_1 = new Department();
            testSave1_1_1.setName("testSave1_1");
            testSave1_1_1.setOrderNo(0);
            testSave1_1_1.setLevels(2);
            testSave1_1_1.setSuperior(testSave1_1);

            objects.add(testSave1);
            objects.add(testSave1_1);
            objects.add(testSave1_2);
            objects.add(testSave1_1_1);
            departmentDao.saveAll(objects);

            Collection<Department> deptall = departmentDao.findAll();
            log.debug("【部门】= {}", JSONArray.toJSONString((List)deptall));
        }

        Department department = departmentDao.findById(29L).get();
        Collection<User> userlist = department.getUserList();
        // 关联关系由user维护中间表，department userlist不会发生变化，可以增加查询方法来处理 重写getUserList方法

        User user = userDao.findById(1L).get();
        user.setName("清空部门");
        user.setDepartmentList(null);
        userDao.save(user);
        log.debug("用户部门={}", userDao.findById(1L).get().getDepartmentList());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void atest() {
        Set<Department> objects = Sets.newConcurrentHashSet();
        Department testSave1 = new Department();
        testSave1.setName("testSave1");
        testSave1.setOrderNo(0);
        testSave1.setLevels(0);
        testSave1.setSuperior(null);

        Department testSave1_1 = new Department();
        testSave1_1.setName("testSave1_1");
        testSave1_1.setOrderNo(0);
        testSave1_1.setLevels(1);
        testSave1_1.setSuperior(testSave1);

        Department testSave1_2 = new Department();
        testSave1_2.setName("testSave1");
        testSave1_2.setOrderNo(0);
        testSave1_2.setLevels(1);
        testSave1_2.setSuperior(testSave1);

        Department testSave1_1_1 = new Department();
        testSave1_1_1.setName("testSave1_1");
        testSave1_1_1.setOrderNo(0);
        testSave1_1_1.setLevels(2);
        testSave1_1_1.setSuperior(testSave1_1);

        objects.add(testSave1);
        objects.add(testSave1_1);
        objects.add(testSave1_2);
        objects.add(testSave1_1_1);

        List<User> byStatus = userDao.findByStatus(1);
        byStatus.forEach(user -> {
            user.setDepartmentList(objects);
            userDao.save(user);
        });
        departmentDao.saveAll(objects);
    }

}
