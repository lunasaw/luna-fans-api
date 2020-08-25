package com.luna.elasticsearch.demo.service;

import com.luna.elasticsearch.demo.pojo.Person;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * PersonService
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/9/15 23:07
 */
@Service
public interface PersonService {

    /**
     * create Index
     *
     * @author fxbin
     * @param index elasticsearch index name
     */
    void createIndex(String index);

    /**
     * delete Index
     *
     * @author fxbin
     * @param index elasticsearch index name
     */
    void deleteIndex(String index);

    /**
     * insert document source
     *
     * @author fxbin
     * @param index elasticsearch index name
     * @param list data source
     */
    void insert(String index, List<Person> list);

    /**
     * update document source
     *
     * @author fxbin
     * @param index elasticsearch index name
     * @param list data source
     */
    void update(String index, List<Person> list);

    /**
     * delete document source
     *
     * @author fxbin
     * @param person delete data source and allow null object
     */
    void delete(String index, @Nullable Person person);

    /**
     * search all doc records
     *
     * @author fxbin
     * @param index elasticsearch index name
     * @return person list
     */
    List<Person> searchList(String index);

}
