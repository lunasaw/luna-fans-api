package com.luna.message.template.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luna.message.template.dao.TemplateMapper;
import com.luna.message.template.pojo.TemplateDO;
import com.luna.message.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:luna
 * @Description:Template业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;


    /**
     * Template条件+分页查询
     * @param templateDO 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<TemplateDO> findPage(TemplateDO templateDO, int page, int size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(templateDO);
        // 执行搜索
        return new PageInfo<TemplateDO>(templateMapper.selectByExample(example));
    }

    /**
     * Template分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<TemplateDO> findPage(int page, int size) {
        // 静态分页
        PageHelper.startPage(page, size);
        // 分页查询
        return new PageInfo<TemplateDO>(templateMapper.selectAll());
    }

    /**
     * Template条件查询
     * @param templateDO
     * @return
     */
    @Override
    public List<TemplateDO> findList(TemplateDO templateDO) {
        // 构建查询条件
        Example example = createExample(templateDO);
        // 根据构建的条件查询数据
        return templateMapper.selectByExample(example);
    }


    /**
     * Template构建查询对象
     * @param templateDO
     * @return
     */
    public Example createExample(TemplateDO templateDO) {
        Example example = new Example(TemplateDO.class);
        Example.Criteria criteria = example.createCriteria();
        if (templateDO != null) {
            // 主键
            if (!StringUtils.isEmpty(templateDO.getId())) {
                criteria.andEqualTo("id", templateDO.getId());
            }
            // 创建时间
            if (!StringUtils.isEmpty(templateDO.getCreateTime())) {
                criteria.andEqualTo("createTime", templateDO.getCreateTime());
            }
            // 修改时间
            if (!StringUtils.isEmpty(templateDO.getModifiedTime())) {
                criteria.andEqualTo("modifiedTime", templateDO.getModifiedTime());
            }
            // 标题
            if (!StringUtils.isEmpty(templateDO.getSubject())) {
                criteria.andEqualTo("subject", templateDO.getSubject());
            }
            // 内容
            if (!StringUtils.isEmpty(templateDO.getContent())) {
                criteria.andEqualTo("content", templateDO.getContent());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id) {
        templateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Template
     * @param templateDO
     */
    @Override
    public void update(TemplateDO templateDO) {
        templateMapper.updateByPrimaryKey(templateDO);
    }

    /**
     * 增加Template
     * @param templateDO
     */
    @Override
    public void add(TemplateDO templateDO) {
        templateMapper.insert(templateDO);
    }

    /**
     * 根据ID查询Template
     * @param id
     * @return
     */
    @Override
    public TemplateDO findById(Long id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Template全部数据
     * @return
     */
    @Override
    public List<TemplateDO> findAll() {
        return templateMapper.selectAll();
    }
}
