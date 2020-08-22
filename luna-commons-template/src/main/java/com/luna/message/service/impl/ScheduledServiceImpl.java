package com.luna.message.service.impl;

import com.luna.message.dao.ScheduledMapper;
import com.luna.message.pojo.Scheduled;
import com.luna.message.service.ScheduledService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Scheduled业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ScheduledServiceImpl implements ScheduledService {

    @Autowired
    private ScheduledMapper scheduledMapper;

    /**
     * Scheduled条件+分页查询
     * 
     * @param scheduled 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Scheduled> findPage(Scheduled scheduled, int page, int size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(scheduled);
        // 执行搜索
        return new PageInfo<Scheduled>(scheduledMapper.selectByExample(example));
    }

    /**
     * Scheduled分页查询
     * 
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Scheduled> findPage(int page, int size) {
        // 静态分页
        PageHelper.startPage(page, size);
        // 分页查询
        return new PageInfo<Scheduled>(scheduledMapper.selectAll());
    }

    /**
     * Scheduled条件查询
     * 
     * @param scheduled
     * @return
     */
    @Override
    public List<Scheduled> findList(Scheduled scheduled) {
        // 构建查询条件
        Example example = createExample(scheduled);
        // 根据构建的条件查询数据
        return scheduledMapper.selectByExample(example);
    }

    /**
     * Scheduled构建查询对象
     * 
     * @param scheduled
     * @return
     */
    public Example createExample(Scheduled scheduled) {
        Example example = new Example(Scheduled.class);
        Example.Criteria criteria = example.createCriteria();
        if (scheduled != null) {
            // 主键id
            if (!StringUtils.isEmpty(scheduled.getCronId())) {
                criteria.andEqualTo("cronId", scheduled.getCronId());
            }
            // 定时任务完整类名
            if (!StringUtils.isEmpty(scheduled.getCronKey())) {
                criteria.andEqualTo("cronKey", scheduled.getCronKey());
            }
            // cron表达式
            if (!StringUtils.isEmpty(scheduled.getCronExpression())) {
                criteria.andEqualTo("cronExpression", scheduled.getCronExpression());
            }
            // 任务描述
            if (!StringUtils.isEmpty(scheduled.getTaskExplain())) {
                criteria.andEqualTo("taskExplain", scheduled.getTaskExplain());
            }
            // 状态,1:正常;2:停用
            if (!StringUtils.isEmpty(scheduled.getStatus())) {
                criteria.andEqualTo("status", scheduled.getStatus());
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
    public void delete(Integer id) {
        scheduledMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Scheduled
     * 
     * @param scheduled
     */
    @Override
    public void update(Scheduled scheduled) {
        scheduledMapper.updateByPrimaryKey(scheduled);
    }

    /**
     * 增加Scheduled
     * 
     * @param scheduled
     */
    @Override
    public void add(Scheduled scheduled) {
        scheduledMapper.insert(scheduled);
    }

    /**
     * 根据ID查询Scheduled
     * 
     * @param id
     * @return
     */
    @Override
    public Scheduled findById(Integer id) {
        return scheduledMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Scheduled全部数据
     * 
     * @return
     */
    @Override
    public List<Scheduled> findAll() {
        return scheduledMapper.selectAll();
    }
}
