package com.luna.jpa.test;

import com.alibaba.fastjson.JSON;
import com.luna.common.utils.md5.Md5Utils;
import com.luna.common.utils.text.RandomNameUtil;
import com.luna.common.utils.text.RandomStr;
import com.luna.jpa.JpaApplicationtTest;
import com.luna.jpa.entity.Contacts;
import com.luna.jpa.entity.User;
import com.luna.jpa.repository.ContactsDao;
import com.luna.jpa.repository.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

/**
 * @Package: com.luna.jpa.test
 * @ClassName: SpringDataJpaTest4
 * @Author: luna
 * @CreateTime: 2020/9/10 21:50
 * @Description:
 */
public class SpringDataJpaTest4 extends JpaApplicationtTest {

    private static final String SALT = "luna";

    @Autowired
    private UserDao             customerDao;

    @Autowired
    private ContactsDao         contactsDao;

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
        User user = new User();
        user.setName(RandomNameUtil.getChineseName());
        String salt = RandomStr.generateNonceStr();
        user.setSalt(salt);
        user.setPassword(Md5Utils.md5(SALT + salt));
        user.setStatus(1);
        user.setPhoneNumber(RandomNameUtil.getTelephone());
        user.setEmail(RandomNameUtil.getEmail());
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());

        Contacts contacts = new Contacts();
        contacts.setName(RandomNameUtil.getChineseName());
        contacts.setEmail(RandomNameUtil.getEmail());
        contacts.setPhone(RandomNameUtil.getTelephone());
        contacts.setMobile(RandomNameUtil.getLandline());

        /**
         * 配置了客户到联系人的关系
         * 从客户的角度上：发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         * 由于我们配置了客户到联系人的关系：客户可以对外键进行维护
         */
        user.getContacts().add(contacts);

        customerDao.save(user);
        contactsDao.save(contacts);
    }

    @Test
    @Transactional // 配置事务
    @Rollback(false) // 不自动回滚
    public void testAdd1() {
        User user = new User();
        user.setName(RandomNameUtil.getChineseName());
        String salt = RandomStr.generateNonceStr();
        user.setSalt(salt);
        user.setPassword(Md5Utils.md5(SALT + salt));
        user.setStatus(1);
        user.setPhoneNumber(RandomNameUtil.getTelephone());
        user.setEmail(RandomNameUtil.getEmail());
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());

        Contacts contacts = new Contacts();
        contacts.setGender(RandomNameUtil.getGender());
        contacts.setName(RandomNameUtil.getChineseName());
        contacts.setEmail(RandomNameUtil.getEmail());
        contacts.setPhone(RandomNameUtil.getTelephone());
        contacts.setMobile(RandomNameUtil.getLandline());

        /**
         * 配置联系人到客户的关系（多对一）
         * 只发送了两条insert语句
         * 由于配置了联系人到客户的映射关系（多对一）
         *
         *
         */
        contacts.setUser(user);

        customerDao.save(user);
        contactsDao.save(contacts);
    }

    /**
     * 级联添加：保存一个客户的同时，保存客户的所有联系人
     * 需要在操作主体的实体类上，配置casacde属性
     */
    @Test
    @Transactional // 配置事务
    @Rollback(false) // 不自动回滚
    public void testCascadeAdd() {
        User user = new User();
        user.setName(RandomNameUtil.getChineseName());
        String salt = RandomStr.generateNonceStr();
        user.setSalt(salt);
        user.setPassword(Md5Utils.md5(SALT + salt));
        user.setStatus(1);
        user.setPhoneNumber(RandomNameUtil.getTelephone());
        user.setEmail(RandomNameUtil.getEmail());
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());

        Contacts contacts = new Contacts();
        contacts.setName(RandomNameUtil.getChineseName());
        contacts.setEmail(RandomNameUtil.getEmail());
        contacts.setPhone(RandomNameUtil.getTelephone());
        contacts.setMobile(RandomNameUtil.getLandline());

        user.getContacts().add(contacts);
        contacts.setUser(user);

        customerDao.save(user);
    }

    /**
     * 级联删除：
     * 删除1号客户的同时，删除1号客户的所有联系人
     */
    @Test
    @Transactional // 配置事务
    @Rollback(false) // 不自动回滚
    public void testCascadeRemove() {
        // 1.查询1号客户
        User customer = customerDao.findById(9l).get();
        // 2.删除1号客户
        customerDao.delete(customer);
    }

    /**
     * 默认采用延迟加载 在使用的时候才查询
     * 若不需要延迟 修改配置
     *
     */
    @Test
    @Transactional // 配置事务
    public void testSelectOneToMany() {
        User customer = customerDao.findById(3l).get();
        Set<Contacts> contacts = customer.getContacts();
        System.out.println(JSON.toJSONString(contacts));
    }

    /**
     * 默认使用立即加载
     * 可在查询主体中设置
     */
    @Test
    @Transactional // 配置事务
    public void testSelectManyToOne() {
        Contacts contact = contactsDao.findById(4L).get();
        System.out.println(contact.getUser());
        System.out.println(JSON.toJSONString(contact));
    }
}
