package com.luna.mybatis.mapper;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.luna.mybatis.entity.Contacts;

@Mapper
public interface ContactsMapper {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Contacts getById(@NotNull Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param contacts 条件
     * @return 对象列表
     */
    List<Contacts> listByEntity(Contacts contacts);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param contacts 条件
     * @return 对象
     */
    Contacts getByEntity(Contacts contacts);

    /**
     * 通过Id列表作为筛选条件查询列表，列表长度不为0
     *
     * @param list 列表
     * @return 对象列表
     */
    List<Contacts> listByIds(@NotEmpty List<Long> list);

    /**
     * 新增实体属性不为null的列
     *
     * @param contacts 实例
     * @return 影响行数
     */
    int insert(@NotNull Contacts contacts);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例
     * @return 影响行数
     */
    int insertBatch(@NotEmpty List<Contacts> list);

    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param contacts 实例
     * @return 影响行数
     */
    int update(@NotNull Contacts contacts);

    /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param where 条件
     * @param where set
     * @return 影响行数
     */
    int updateByField(@NotNull @Param("where") Contacts where, @NotNull @Param("set") Contacts set);

    /**
     * 通过主键修改实体列表，列表长度不能为0，注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 列表
     * @return 影响行数
     */
    int updateBatch(@NotEmpty List<Contacts> list);

    /**
     * 通过主键删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@NotNull Long id);

    /**
     * 通过实体非空属性删除
     *
     * @param contacts 实体
     * @return 影响行数
     */
    int deleteByEntity(@NotNull Contacts contacts);

    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 列表
     * @return 影响行数
     */
    int deleteByIds(@NotEmpty List<Long> list);

    /**
     * 查询行数
     *
     * @return 影响行数
     */
    int countAll();

    /**
     * 通过实体非空查询行数
     *
     * @param contacts 实体
     * @return 影响行数
     */
    int countByEntity(Contacts contacts);

}