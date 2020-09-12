package com.luna.mybatis.service;

import com.luna.mybatis.entity.User;
import com.luna.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Package: com.luna.mybatis.service
 * @ClassName: UserService
 * @Author: luna
 * @CreateTime: 2020/9/12 22:09
 * @Description:
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
