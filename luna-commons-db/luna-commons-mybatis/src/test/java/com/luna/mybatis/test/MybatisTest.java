package com.luna.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.luna.mybatis.entity.User;
import com.luna.mybatis.mapper.UserMapper;
import org.junit.Test;

import com.luna.mybatis.MybatisApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package: com.luna.mybatis.test
 * @ClassName: MybatisTest
 * @Author: luna
 * @CreateTime: 2020/9/12 21:49
 * @Description:
 */
public class MybatisTest extends MybatisApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void atest() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(user));
    }
}
