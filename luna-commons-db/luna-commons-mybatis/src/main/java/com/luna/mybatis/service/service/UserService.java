package com.luna.mybatis.service.service;

import com.luna.mybatis.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getById(Integer id);

    User getByEntity(User user);

    List<User> listByEntity(User user);

    List<User> listByIds(List<Integer> ids);

    int insert(User user);

    int insertBatch(List<User> list);

    int update(User user);

    int updateBatch(List<User> list);

    int deleteById(Integer id);

    int deleteByEntity(User user);

    int deleteByIds(List<Integer> list);

    int countAll();

    int countByEntity(User user);
}