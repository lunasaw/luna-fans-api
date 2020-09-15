package com.luna.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.luna.jpa.entity.Contacts;

/**
 * 联系人的dao接口
 */
public interface ContactsDao extends JpaRepository<Contacts, Long>, JpaSpecificationExecutor<Contacts> {

}
