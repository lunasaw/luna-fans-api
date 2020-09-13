package com.luna.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luna.mybatis.entity.Contacts;
import com.luna.mybatis.mapper.ContactsMapper;
import com.luna.mybatis.service.ContactsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2020-09-13 23:36:41
 */
@Service
public class ContactsServiceImpl implements ContactsService {

    @Resource(type = ContactsMapper.class)
    private ContactsMapper contactsMapper;

    @Override
    public Contacts getById(Long id) {
        return contactsMapper.getById(id);
    }

    @Override
    public Contacts getByEntity(Contacts contacts) {
        return contactsMapper.getByEntity(contacts);
    }

    @Override
    public List<Contacts> listByEntity(Contacts contacts) {
        return contactsMapper.listByEntity(contacts);
    }

    @Override
    public PageInfo listPageByEntity(int page, int pageSize, Contacts contacts) {
        PageHelper.startPage(page, pageSize);
        List<Contacts> list = contactsMapper.listByEntity(contacts);
        return new PageInfo(list);
    }

    @Override
    public PageInfo listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Contacts> list = contactsMapper.listByEntity(new Contacts());
        return new PageInfo(list);
    }

    @Override
    public List<Contacts> listByIds(List<Long> ids) {
        return contactsMapper.listByIds(ids);
    }

    @Override
    public int insert(Contacts contacts) {
        Date date = new Date();
        contacts.setCreateTime(date);
        contacts.setLastUpdateTime(date);
        return contactsMapper.insert(contacts);
    }

    @Override
    public int insertBatch(List<Contacts> list) {
        return contactsMapper.insertBatch(list);
    }

    @Override
    public int update(Contacts contacts) {
        contacts.setLastUpdateTime(new Date());
        return contactsMapper.update(contacts);
    }

    @Override
    public int updateBatch(List<Contacts> list) {
        return contactsMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return contactsMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(Contacts contacts) {
        return contactsMapper.deleteByEntity(contacts);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return contactsMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return contactsMapper.countAll();
    }

    @Override
    public int countByEntity(Contacts contacts) {
        return contactsMapper.countByEntity(contacts);
    }

}