package com.luna.jpa.test;

import com.luna.common.utils.md5.Md5Utils;
import com.luna.jpa.JpaApplicationtTest;
import com.luna.jpa.entity.LinkMan;
import com.luna.jpa.entity.User;
import com.luna.jpa.repository.LinkManDao;
import com.luna.jpa.repository.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Package: com.luna.jpa.test
 * @ClassName: SpringDataJpaTest4
 * @Author: luna
 * @CreateTime: 2020/9/10 21:50
 * @Description:
 */
public class SpringDataJpaTest4 extends JpaApplicationtTest {

    @Autowired
    private UserDao    userDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个客户，保存一个联系人
     * 效果：客户和联系人作为独立的数据保存到数据库中
     * 联系人的外键为空
     * 原因？
     * 实体类中没有配置关系
     */
    @Test
    @Transactional // 配置事务
    @Rollback(false) // 不自动回滚
    public void testAdd() {
        // 创建一个客户，创建一个联系人
        User testSave3 = new User();
        testSave3.setName("小明");
        testSave3.setPassword(Md5Utils.md5("czy1024"));
        testSave3.setSalt("czy");
        testSave3.setStatus(1);
        testSave3.setPhoneNumber("1999999997");
        testSave3.setEmail("testSave001@luna.com");
        testSave3.setCreateTime(new Date());
        testSave3.setLastUpdateTime(new Date());

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");
        /**
         * 配置了客户到联系人的关系
         * 从客户的角度上：发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         * 由于我们配置了客户到联系人的关系：客户可以对外键进行维护
         */
        List<LinkMan> linkMans = testSave3.getLinkMans();
        linkMans.add(linkMan);
        testSave3.setLinkMans(linkMans);

        linkManDao.save(linkMan);
        userDao.save(testSave3);
    }

    @Test
    @Transactional // 配置事务
    @Rollback(false) // 不自动回滚
    public void testAdd1() {
        // 创建一个客户，创建一个联系人
        User testSave3 = new User();
        testSave3.setName("小明");
        testSave3.setPassword(Md5Utils.md5("czy1024"));
        testSave3.setSalt("czy");
        testSave3.setStatus(1);
        testSave3.setPhoneNumber("1999999997");
        testSave3.setEmail("testSave001@luna.com");
        testSave3.setCreateTime(new Date());
        testSave3.setLastUpdateTime(new Date());

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        /**
         * 配置联系人到客户的关系（多对一）
         * 只发送了两条insert语句
         * 由于配置了联系人到客户的映射关系（多对一）
         *
         *
         */
        linkMan.setUser(testSave3);

        userDao.save(testSave3);
        linkManDao.save(linkMan);
    }
}
