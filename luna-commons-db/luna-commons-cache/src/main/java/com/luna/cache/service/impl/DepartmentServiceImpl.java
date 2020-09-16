package com.luna.cache.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luna.cache.entity.Department;
import com.luna.cache.mapper.DepartmentMapper;
import com.luna.cache.service.DepartmentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2020-09-14 13:19:32
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource(type = DepartmentMapper.class)
    private DepartmentMapper departmentMapper;

    @Override
    @Cacheable(value = "getById")
    public Department getById(Integer id) {
        return departmentMapper.getById(id);
    }

    @Override
    @Cacheable(value = "getByEntity", keyGenerator = "keyGenerator")
    public Department getByEntity(Department department) {
        return departmentMapper.getByEntity(department);
    }

    @Override
    @Cacheable(value = "listByEntity", keyGenerator = "keyGenerator")
    public List<Department> listByEntity(Department department) {
        return departmentMapper.listByEntity(department);
    }

    @Override
    @Cacheable(value = "listPageByEntity", keyGenerator = "keyGenerator")
    public PageInfo listPageByEntity(int page, int pageSize, Department department) {
        PageHelper.startPage(page, pageSize);
        List<Department> list = departmentMapper.listByEntity(department);
        return new PageInfo(list);
    }

    @Override
    @Cacheable(value = "listPage", keyGenerator = "keyGenerator")
    public PageInfo listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Department> list = departmentMapper.listByEntity(new Department());
        return new PageInfo(list);
    }

    @Override
    @Cacheable(value = "listByIds", keyGenerator = "keyGenerator")
    public List<Department> listByIds(List<Integer> ids) {
        return departmentMapper.listByIds(ids);
    }

    @Override
    public int insert(Department department) {
        Date date = new Date();
        department.setCreateTime(date);
        department.setLastUpdateTime(date);
        return departmentMapper.insert(department);
    }

    @Override
    public int insertBatch(List<Department> list) {
        return departmentMapper.insertBatch(list);
    }

    @Override
    @CachePut(value = "department", keyGenerator = "keyGenerator")
    public int update(Department department) {
        department.setLastUpdateTime(new Date());
        return departmentMapper.update(department);
    }

    @Override
    @CachePut(value = "department", keyGenerator = "keyGenerator")
    public int updateBatch(List<Department> list) {
        return departmentMapper.updateBatch(list);
    }

    @Override
    @CacheEvict(value = "deleteById", key = "keyGenerator")
    public int deleteById(Integer id) {
        return departmentMapper.deleteById(id);
    }

    @Override
    @CacheEvict(value = "deleteByEntity", key = "keyGenerator")
    public int deleteByEntity(Department department) {
        return departmentMapper.deleteByEntity(department);
    }

    @Override
    @CacheEvict(value = "deleteByIds", key = "keyGenerator")
    public int deleteByIds(List<Integer> list) {
        return departmentMapper.deleteByIds(list);
    }

    @Override
    @Cacheable(value = "countAll", keyGenerator = "keyGenerator")
    public int countAll() {
        return departmentMapper.countAll();
    }

    @Override
    @Cacheable(value = "countByEntity", keyGenerator = "keyGenerator")
    public int countByEntity(Department department) {
        return departmentMapper.countByEntity(department);
    }

}