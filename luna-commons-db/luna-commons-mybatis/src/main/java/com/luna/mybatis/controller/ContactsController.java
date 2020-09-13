package com.luna.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.mybatis.entity.Contacts;
import com.luna.mybatis.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2020-09-13 23:36:41
 */
@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @GetMapping("/get/{id}")
    public ResultDTO<Contacts> getById(@PathVariable Long id) {
        Contacts contacts = contactsService.getById(id);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            contacts != null ? contacts : new Contacts());
    }

    @GetMapping("/get")
    public ResultDTO<Contacts> getByEntity(Contacts contacts) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, contactsService.getByEntity(contacts));
    }

    @GetMapping("/list")
    public ResultDTO<List<Contacts>> list(Contacts contacts) {
        List<Contacts> contactsList = contactsService.listByEntity(contacts);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, contactsList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo> listPageByEntity(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, Contacts contacts) {
        PageInfo pageInfo = contactsService.listPageByEntity(page, size, contacts);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
        PageInfo pageInfo = contactsService.listPage(page, size);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<Contacts> insert(@RequestBody Contacts contacts) {
        contactsService.insert(contacts);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, contacts);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<Contacts>> insert(@RequestBody List<Contacts> contacts) {
        contactsService.insertBatch(contacts);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, contacts);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody Contacts contacts) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, contactsService.update(contacts) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<Contacts> contacts) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            contactsService.updateBatch(contacts) == contacts.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable Long id) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, contactsService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity/{id}")
    public ResultDTO<Boolean> deleteOne(@RequestBody Contacts contacts) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            contactsService.deleteByEntity(contacts) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = contactsService.deleteByIds(ids);
        }
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
    }

}