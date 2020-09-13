package com.luna.mybatis.service;

import com.luna.mybatis.entity.Contacts;

import java.util.List;

public interface ContactsService {

    Contacts getById(Long id);

    Contacts getByEntity(Contacts contacts);

    List<Contacts> listByEntity(Contacts contacts);

    List<Contacts> listByIds(List<Long> ids);

    int insert(Contacts contacts);

    int insertBatch(List<Contacts> list);

    int update(Contacts contacts);

    int updateBatch(List<Contacts> list);

    int deleteById(Long id);

    int deleteByEntity(Contacts contacts);

    int deleteByIds(List<Long> list);

    int countAll();

    int countByEntity(Contacts contacts);
}