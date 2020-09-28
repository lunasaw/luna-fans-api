package com.luna.jwt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luna.jwt.entity.User;
import com.luna.jwt.mapper.UserMapper;
import com.luna.jwt.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2020-09-28 16:03:27
 */
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
    public PageInfo listPageByEntity(int page, int pageSize, User user) {
        PageHelper.startPage(page, pageSize);
        List<User> list = userMapper.listByEntity(user);
        return new PageInfo(list);
    }

    @Override
    public PageInfo listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> list = userMapper.listByEntity(new User());
        return new PageInfo(list);
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