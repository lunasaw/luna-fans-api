package com.luna.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.luna.jpa.entity.LinkMan;

/**
 * @Package: com.luna.jpa.repository
 * @ClassName: LinkManDao
 * @Author: luna
 * @CreateTime: 2020/9/10 21:51
 * @Description:
 */
@Repository
public interface LinkManDao extends JpaRepository<LinkMan, Long>, JpaSpecificationExecutor<LinkMan> {}
