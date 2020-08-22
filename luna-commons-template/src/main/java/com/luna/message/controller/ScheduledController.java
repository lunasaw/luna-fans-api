package com.luna.message.controller;

import com.luna.message.pojo.Scheduled;
import com.luna.message.service.ScheduledService;
import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "ScheduledController")
@RestController
@RequestMapping("/scheduled")
@CrossOrigin
public class ScheduledController {

    @Autowired
    private ScheduledService scheduledService;

    /***
     * Scheduled分页条件搜索实现
     * 
     * @param scheduled
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Scheduled条件分页查询", notes = "分页条件查询Scheduled方法详情", tags = {"ScheduledController"})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public ResultDTO<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Scheduled对象",
        value = "传入JSON数据", required = false) Scheduled scheduled, @PathVariable int page, @PathVariable int size) {
        // 调用ScheduledService实现分页条件查询Scheduled
        PageInfo<Scheduled> pageInfo = scheduledService.findPage(scheduled, page, size);
        return new ResultDTO(true, ResultCode.SUCCESS, "查询成功", pageInfo);
    }

    /***
     * Scheduled分页搜索实现
     * 
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Scheduled分页查询", notes = "分页查询Scheduled方法详情", tags = {"ScheduledController"})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public ResultDTO<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        // 调用ScheduledService实现分页查询Scheduled
        PageInfo<Scheduled> pageInfo = scheduledService.findPage(page, size);
        return new ResultDTO<PageInfo>(true, ResultCode.SUCCESS, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * 
     * @param scheduled
     * @return
     */
    @ApiOperation(value = "Scheduled条件查询", notes = "条件查询Scheduled方法详情", tags = {"ScheduledController"})
    @PostMapping(value = "/search")
    public ResultDTO<List<Scheduled>> findList(@RequestBody(required = false) @ApiParam(name = "Scheduled对象",
        value = "传入JSON数据", required = false) Scheduled scheduled) {
        // 调用ScheduledService实现条件查询Scheduled
        List<Scheduled> list = scheduledService.findList(scheduled);
        return new ResultDTO<List<Scheduled>>(true, ResultCode.SUCCESS, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * 
     * @param id
     * @return
     */
    @ApiOperation(value = "Scheduled根据ID删除", notes = "根据ID删除Scheduled方法详情", tags = {"ScheduledController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public ResultDTO delete(@PathVariable Integer id) {
        // 调用ScheduledService实现根据主键删除
        scheduledService.delete(id);
        return new ResultDTO(true, ResultCode.SUCCESS, "删除成功");
    }

    /***
     * 修改Scheduled数据
     * 
     * @param scheduled
     * @param id
     * @return
     */
    @ApiOperation(value = "Scheduled根据ID修改", notes = "根据ID修改Scheduled方法详情", tags = {"ScheduledController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public ResultDTO update(
        @RequestBody @ApiParam(name = "Scheduled对象", value = "传入JSON数据", required = false) Scheduled scheduled,
        @PathVariable Integer id) {
        // 设置主键值
        scheduled.setCronId(id);
        // 调用ScheduledService实现修改Scheduled
        scheduledService.update(scheduled);
        return new ResultDTO(true, ResultCode.SUCCESS, "修改成功");
    }

    /***
     * 新增Scheduled数据
     * 
     * @param scheduled
     * @return
     */
    @ApiOperation(value = "Scheduled添加", notes = "添加Scheduled方法详情", tags = {"ScheduledController"})
    @PostMapping
    public ResultDTO
        add(@RequestBody @ApiParam(name = "Scheduled对象", value = "传入JSON数据", required = true) Scheduled scheduled) {
        // 调用ScheduledService实现添加Scheduled
        scheduledService.add(scheduled);
        return new ResultDTO(true, ResultCode.SUCCESS, "添加成功");
    }

    /***
     * 根据ID查询Scheduled数据
     * 
     * @param id
     * @return
     */
    @ApiOperation(value = "Scheduled根据ID查询", notes = "根据ID查询Scheduled方法详情", tags = {"ScheduledController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public ResultDTO<Scheduled> findById(@PathVariable Integer id) {
        // 调用ScheduledService实现根据主键查询Scheduled
        Scheduled scheduled = scheduledService.findById(id);
        return new ResultDTO<Scheduled>(true, ResultCode.SUCCESS, "查询成功", scheduled);
    }

    /***
     * 查询Scheduled全部数据
     * 
     * @return
     */
    @ApiOperation(value = "查询所有Scheduled", notes = "查询所Scheduled有方法详情", tags = {"ScheduledController"})
    @GetMapping
    public ResultDTO<List<Scheduled>> findAll() {
        // 调用ScheduledService实现查询所有Scheduled
        List<Scheduled> list = scheduledService.findAll();
        return new ResultDTO<List<Scheduled>>(true, ResultCode.SUCCESS, "查询成功", list);
    }
}
