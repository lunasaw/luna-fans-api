package com.luna.message.template.service;

import com.github.pagehelper.PageInfo;
import com.luna.message.template.pojo.TemplateDO;

import java.util.List;

/****
 * @Author:luna
 * @Description:Template业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface TemplateService {

    /***
     * Template多条件分页查询
     * 
     * @param templateDO
     * @param page
     * @param size
     * @return
     */
    PageInfo<TemplateDO> findPage(TemplateDO templateDO, int page, int size);

    /***
     * Template分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<TemplateDO> findPage(int page, int size);

    /***
     * Template多条件搜索方法
     * 
     * @param templateDO
     * @return
     */
    List<TemplateDO> findList(TemplateDO templateDO);

    /***
     * 删除Template
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Template数据
     * 
     * @param templateDO
     */
    void update(TemplateDO templateDO);

    /***
     * 新增Template
     * 
     * @param templateDO
     */
    void add(TemplateDO templateDO);

    /**
     * 根据ID查询Template
     * @param id
     * @return
     */
    TemplateDO findById(Long id);

    /***
     * 查询所有Template
     * @return
     */
    List<TemplateDO> findAll();
}
