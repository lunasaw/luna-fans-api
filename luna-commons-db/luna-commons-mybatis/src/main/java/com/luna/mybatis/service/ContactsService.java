package com.luna.mybatis.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.luna.mybatis.entity.Contacts;

/**
 * @Author: luna
 * @CreateTime: 2020-09-13 23:36:41
 */
public interface ContactsService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Contacts getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param contacts 条件
     * @return 对象
     */
    Contacts getByEntity(Contacts contacts);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param contacts 条件
     * @return 对象列表
     */
    List<Contacts> listByEntity(Contacts contacts);

    /**
     * 条件分页查询
     *
     * @param Contacts contacts 查询条件
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo listPageByEntity(int page, int pageSize, Contacts contacts);

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
    List<Contacts> listByIds(List<Long> ids);

    /**
     * 插入
     *
     * @param contacts 对象
     * @return 影响行数
     */
    int insert(Contacts contacts);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<Contacts> list);

    /**
     * 更新
     *
     * @param contacts 对象
     * @return 影响行数
     */
    int update(Contacts contacts);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<Contacts> list);

    /**
     * 删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 条件删除
     *
     * @param contacts 对象
     * @return 影响行数
     */
    int deleteByEntity(Contacts contacts);

    /**
     * 主键列表删除
     *
     * @param list 主键列表
     * @return 影响行数
     */
    int deleteByIds(List<Long> list);

    /**
     * 数据条目
     *
     * @return 影响行数
     */
    int countAll();

    /**
     * 条件查询数目
     *
     * @param contacts 对象
     * @return 影响行数
     */
    int countByEntity(Contacts contacts);
}