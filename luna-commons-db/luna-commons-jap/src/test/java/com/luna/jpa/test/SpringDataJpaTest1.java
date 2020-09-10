package com.luna.jpa.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.luna.common.utils.md5.Md5Utils;
import com.luna.jpa.JpaApplicationtTest;
import com.luna.jpa.entity.User;
import com.luna.jpa.repository.UserDao;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Package: com.luna.jpa.test
 * @ClassName: SpringDataJpaTest
 * @Author: luna
 * @CreateTime: 2020/9/9 13:23
 * @Description:
 */
public class SpringDataJpaTest1 extends JpaApplicationtTest {

    private static final Logger log = LoggerFactory.getLogger(SpringDataJpaTest1.class);

    @Autowired
    private UserDao             userDao;

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        String salt = IdUtil.fastSimpleUUID();
        User testSave3 = new User();
        testSave3.setName("张三");
        testSave3.setPassword(Md5Utils.md5("czy1024"));
        testSave3.setSalt("czy");
        testSave3.setStatus(1);
        testSave3.setPhoneNumber("1999999999");
        testSave3.setEmail("testSave3@luna.com");
        testSave3.setCreateTime(new Date());
        testSave3.setLastUpdateTime(new Date());
        userDao.save(testSave3);
        Assert.assertNotNull(testSave3.getId());
        Optional<User> byId = userDao.findById(testSave3.getId());
        Assert.assertTrue(byId.isPresent());
        log.debug("【byId】= {}", byId.get());
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        long count = userDao.count();
        userDao.deleteById(1L);
        long left = userDao.count();
        Assert.assertEquals("", count - 1, left);
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate() {
        userDao.findById(2L).ifPresent(user -> {
            user.setName("JPA修改名字");
            userDao.save(user);
        });
        Assert.assertEquals("JPA修改名字", userDao.findById(2L).get().getName());
    }

    /**
     * 测试查询单个
     */
    @Test
    public void testQueryOne() {
        Optional<User> byId = userDao.findById(1L);
        Assert.assertTrue(byId.isPresent());
        log.debug("【byId】= {}", byId.get());
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testQueryAll() {
        List<User> users = userDao.findAll();
        Assert.assertNotEquals(0, users.size());
        log.debug("【users】= {}", users);
    }

    /**
     * 测试分页排序查询
     */
    @Test
    public void testQueryPage() {
        // 初始化数据
        initData();
        // JPA分页的时候起始页是页码减1
        Integer currentPage = 0;
        Integer pageSize = 5;
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);
        Page<User> userPage = userDao.findAll(pageRequest);

        Assert.assertEquals(5, userPage.getSize());
        Assert.assertEquals(userDao.count(), userPage.getTotalElements());
        log.debug("【id】= {}", userPage.getContent().stream().map(User::getId).collect(Collectors.toList()));
    }

    /**
     * 初始化10条数据
     */
    private void initData() {
        List<User> userList = Lists.newArrayList();
        for (int i = 1; i <= 10; i++) {
            String salt = IdUtil.fastSimpleUUID();
            int index = 3 + i;
            User user = User.builder().name("testSave" + index).password(SecureUtil.md5("123456" + salt)).salt(salt)
                .email("testSave" + index + "@xkcoding.com").phoneNumber("1730000000" + index).status(1)
                .lastLoginTime(new DateTime()).build();
            userList.add(user);
        }
        userDao.saveAll(userList);
    }

}
