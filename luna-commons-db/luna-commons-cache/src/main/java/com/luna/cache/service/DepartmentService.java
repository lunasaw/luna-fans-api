package com.luna.cache.service;

import com.github.pagehelper.PageInfo;
import com.luna.cache.entity.Department;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2020-09-14 13:19:31
 */
public interface DepartmentService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Department getById(Integer id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param department 条件
     * @return 对象
     */
    Department getByEntity(Department department);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param department 条件
     * @return 对象列表
     */
    List<Department> listByEntity(Department department);

    /**
     * 条件分页查询
     *
     * @param department 查询条件
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo listPageByEntity(int page, int pageSize, Department department);

    /**
     * 条件分页查询
     *
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<Department> listByIds(List<Integer> ids);

    /**
     * 插入
     *
     * @param department 对象
     * @return 影响行数
     */
    int insert(Department department);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<Department> list);

    /**
     * 更新
     *
     * @param department 对象
     * @return 影响行数
     */
    int update(Department department);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<Department> list);

    /**
     * 删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 条件删除
     *
     * @param department 对象
     * @return 影响行数
     */
    int deleteByEntity(Department department);

    /**
     * 主键列表删除
     *
     * @param list 主键列表
     * @return 影响行数
     */
    int deleteByIds(List<Integer> list);

    /**
     * 数据条目
     *
     * @return 影响行数
     */
    int countAll();

    /**
     * 条件查询数目
     *
     * @param department 对象
     * @return 影响行数
     */
    int countByEntity(Department department);
}