package com.luna.cache.mapper;

import com.luna.cache.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2020-09-14 13:19:31
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Department getById(@NotNull Integer id);

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
     * 通过Id列表作为筛选条件查询列表，列表长度不为0
     *
     * @param list 列表
     * @return 对象列表
     */
    List<Department> listByIds(@NotEmpty List<Integer> list);

    /**
     * 新增实体属性不为null的列
     *
     * @param department 实例
     * @return 影响行数
     */
    int insert(@NotNull Department department);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例
     * @return 影响行数
     */
    int insertBatch(@NotEmpty List<Department> list);

    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param department 实例
     * @return 影响行数
     */
    int update(@NotNull Department department);

    /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param where 条件
     * @param where set
     * @return 影响行数
     */
    int updateByField(@NotNull @Param("where") Department where, @NotNull @Param("set") Department set);

    /**
     * 通过主键修改实体列表，列表长度不能为0，注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 列表
     * @return 影响行数
     */
    int updateBatch(@NotEmpty List<Department> list);

    /**
     * 通过主键删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@NotNull Integer id);

    /**
     * 通过实体非空属性删除
     *
     * @param department 实体
     * @return 影响行数
     */
    int deleteByEntity(@NotNull Department department);

    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 列表
     * @return 影响行数
     */
    int deleteByIds(@NotEmpty List<Integer> list);

    /**
     * 查询行数
     *
     * @return 影响行数
     */
    int countAll();

    /**
     * 通过实体非空查询行数
     *
     * @param department 实体
     * @return 影响行数
     */
    int countByEntity(Department department);

}