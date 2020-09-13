package com.luna.mybatis.service.impl;

import com.luna.mybatis.entity.User;
import com.luna.mybatis.mapper.UserMapper;
import com.luna.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource(type = UserMapper.class)
    private UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public User getByEntity(User user) {
        return userMapper.getByEntity(user);
    }

    @Override
    public List<User> listByEntity(User user) {
        return userMapper.listByEntity(user);
    }

    @Override
    public List<User> listByIds(List<Integer> ids) {
        return userMapper.listByIds(ids);
    }

    @Override
    public int insert(User user) {
        Date date = new Date();
        user.setCreateTime(date);
        user.setLastUpdateTime(date);
        return userMapper.insert(user);
    }

    @Override
    public int insertBatch(List<User> list) {
        return userMapper.insertBatch(list);
    }

    @Override
    public int update(User user) {
        user.setLastUpdateTime(new Date());
        return userMapper.update(user);
    }

    @Override
    public int updateBatch(List<User> list) {
        return userMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(User user) {
        return userMapper.deleteByEntity(user);
    }

    @Override
    public int deleteByIds(List<Integer> list) {
        return userMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return userMapper.countAll();
    }

    @Override
    public int countByEntity(User user) {
        return userMapper.countByEntity(user);
    }

}