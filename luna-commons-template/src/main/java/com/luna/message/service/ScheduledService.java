package com.luna.message.service;

import com.luna.message.pojo.Scheduled;
import com.github.pagehelper.PageInfo;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Scheduled业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface ScheduledService {

    /***
     * Scheduled多条件分页查询
     * 
     * @param scheduled
     * @param page
     * @param size
     * @return
     */
    PageInfo<Scheduled> findPage(Scheduled scheduled, int page, int size);

    /***
     * Scheduled分页查询
     * 
     * @param page
     * @param size
     * @return
     */
    PageInfo<Scheduled> findPage(int page, int size);

    /***
     * Scheduled多条件搜索方法
     * 
     * @param scheduled
     * @return
     */
    List<Scheduled> findList(Scheduled scheduled);

    /***
     * 删除Scheduled
     * 
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Scheduled数据
     * 
     * @param scheduled
     */
    void update(Scheduled scheduled);

    /***
     * 新增Scheduled
     * 
     * @param scheduled
     */
    void add(Scheduled scheduled);

    /**
     * 根据ID查询Scheduled
     * 
     * @param id
     * @return
     */
    Scheduled findById(Integer id);

    /***
     * 查询所有Scheduled
     * 
     * @return
     */
    List<Scheduled> findAll();
}
