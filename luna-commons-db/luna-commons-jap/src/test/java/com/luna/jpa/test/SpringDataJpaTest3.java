package com.luna.jpa.test;

import com.alibaba.fastjson.JSON;
import com.luna.jpa.JpaApplicationtTest;
import com.luna.jpa.entity.User;
import com.luna.jpa.repository.UserDao;
import com.luna.jpa.utils.CriteriaUtils;
import com.luna.jpa.utils.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/**
 * @Package: com.luna.jpa.test
 * @ClassName: SpringDataJpaTest3
 * @Author: luna
 * @CreateTime: 2020/9/9 22:33
 * @Description:
 */
public class SpringDataJpaTest3 extends JpaApplicationtTest {

    @Autowired
    UserDao userDao;

    /**
     * 动态单条件查询
     *
     */
    @Test
    public void atest() {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 获取比较的属性 *属性名称
                Path<Object> name = root.get("name");
                // 构造查询
                return criteriaBuilder.equal(name, "testSave4");
            }
        };
        Optional<User> one = userDao.findOne(specification);
        System.out.println(one.get());
    }

    /**
     * 多条件模糊查询
     */
    @Test
    public void btest() {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> name = root.get("name");
                Path<Object> salt = root.get("salt");
                Predicate nameResult = criteriaBuilder.equal(name, "testSave4");
                Predicate saltResult = criteriaBuilder.equal(salt, "0cb0fd2f1ae94d2b83046ffcd865cbe9");
                // 组合条件 满足1并且满足2
                return criteriaBuilder.and(nameResult, saltResult);
                // 满足1或者满足2
                // criteriaBuilder.or(nameResult, saltResult);
            }
        };
        Optional<User> one = userDao.findOne(specification);
        System.out.println(one.get());
    }

    /**
     * 动态模糊查询
     */
    @Test
    public void ctest() {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> name = root.get("name");
                // 可gt lt ge le like
                // 指定参数类型
                return criteriaBuilder.like(name.as(String.class), "testSave%");
            }
        };
        // List<User> all = userDao.findAll(specification);

        // 待顺序查询
        // 创建排序对象 asc 升序 desc 倒序
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        List<User> all = userDao.findAll(specification, Sort.by(order));
        System.out.println(JSON.toJSONString(all));
    }

    @Test
    public void dtest() {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> name = root.get("name");
                // 可gt lt ge le like
                // 指定参数类型
                return criteriaBuilder.like(name.as(String.class), "testSave%");
            }
        };

        PageRequest pageRequest = PageRequest.of(1, 4, Sort.by(Sort.Direction.DESC, "id"));
        Page<User> all = userDao.findAll(specification, pageRequest);
        System.out.println(all.getContent());
        System.out.println(all.getTotalElements());
        System.out.println(JSON.toJSONString(all));
    }

    @Test
    @Transactional
    public void selectUtilTest() {
        CriteriaUtils<User> c = new CriteriaUtils<User>();
        c.add(Restrictions.eq("name", "谢艳枫", true));
        c.add(Restrictions.eq("email", "lfnw6hs5wnn3s@ask.com", true));
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return c.toPredicate(root, query, criteriaBuilder);
            }
        };

        List<User> all = userDao.findAll(specification);
        all.forEach(user -> {
            System.out.println(user.getName());
        });
    }
}
