package com.luna.message.service.impl;

import com.luna.message.dao.TemplateMapper;
import com.luna.message.pojo.Template;
import com.luna.message.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Template业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    /**
     * Template条件+分页查询
     * 
     * @param template 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Template> findPage(Template template, int page, int size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(template);
        // 执行搜索
        return new PageInfo<Template>(templateMapper.selectByExample(example));
    }

    /**
     * Template分页查询
     * 
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Template> findPage(int page, int size) {
        // 静态分页
        PageHelper.startPage(page, size);
        // 分页查询
        return new PageInfo<Template>(templateMapper.selectAll());
    }

    /**
     * Template条件查询
     * 
     * @param template
     * @return
     */
    @Override
    public List<Template> findList(Template template) {
        // 构建查询条件
        Example example = createExample(template);
        // 根据构建的条件查询数据
        return templateMapper.selectByExample(example);
    }

    /**
     * Template构建查询对象
     * 
     * @param template
     * @return
     */
    public Example createExample(Template template) {
        Example example = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if (template != null) {
            // 主键
            if (!StringUtils.isEmpty(template.getId())) {
                criteria.andEqualTo("id", template.getId());
            }
            // 创建时间
            if (!StringUtils.isEmpty(template.getCreateTime())) {
                criteria.andEqualTo("createTime", template.getCreateTime());
            }
            // 修改时间
            if (!StringUtils.isEmpty(template.getModifiedTime())) {
                criteria.andEqualTo("modifiedTime", template.getModifiedTime());
            }
            // 标题
            if (!StringUtils.isEmpty(template.getSubject())) {
                criteria.andEqualTo("subject", template.getSubject());
            }
            // 内容
            if (!StringUtils.isEmpty(template.getContent())) {
                criteria.andEqualTo("content", template.getContent());
            }
        }
        return example;
    }

    /**
     * 删除
     * 
     * @param id
     */
    @Override
    public void delete(Long id) {
        templateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Template
     * 
     * @param template
     */
    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKey(template);
    }

    /**
     * 增加Template
     * 
     * @param template
     */
    @Override
    public void add(Template template) {
        templateMapper.insert(template);
    }

    /**
     * 根据ID查询Template
     * 
     * @param id
     * @return
     */
    @Override
    public Template findById(Long id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Template全部数据
     * 
     * @return
     */
    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }
}
