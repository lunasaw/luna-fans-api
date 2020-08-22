package com.luna.message.service;

import com.luna.message.pojo.Template;
import com.github.pagehelper.PageInfo;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Template业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface TemplateService {

    /***
     * Template多条件分页查询
     * 
     * @param template
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(Template template, int page, int size);

    /***
     * Template分页查询
     * 
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(int page, int size);

    /***
     * Template多条件搜索方法
     * 
     * @param template
     * @return
     */
    List<Template> findList(Template template);

    /***
     * 删除Template
     * 
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Template数据
     * 
     * @param template
     */
    void update(Template template);

    /***
     * 新增Template
     * 
     * @param template
     */
    void add(Template template);

    /**
     * 根据ID查询Template
     * 
     * @param id
     * @return
     */
    Template findById(Long id);

    /***
     * 查询所有Template
     * 
     * @return
     */
    List<Template> findAll();
}
