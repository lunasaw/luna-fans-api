package com.luna.elasticsearch.demo;

import com.alibaba.fastjson.JSON;
import com.luna.common.utils.text.DateUtil;
import com.luna.elasticsearch.constants.ElastcSearchConstants;
import com.luna.elasticsearch.model.Person;
import com.luna.elasticsearch.service.PersonService;
import com.luna.elasticsearch.service.impl.BaseElasticsearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private PersonService personService;

    @Autowired
    private BaseElasticsearchService elasticsearchBase;

    /**
     * 测试删除索引
     */
    @Test
    public void deleteIndexTest() {
        personService.deleteIndex(ElastcSearchConstants.INDEX_LUNA_PERSON);
    }

    /**
     * 测试创建索引
     */
    @Test
    public void createIndexTest() {
        personService.createIndex(ElastcSearchConstants.INDEX_LUNA_PERSON);
    }

    /**
     * 测试新增
     */
    @Test
    public void insertTest() {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1L, "小李", "中国", 20, DateUtil.parseDate("1999-11-07"), "C#程序员"));
        list.add(new Person(2L, "小李", "中国", 20, DateUtil.parseDate("1999-11-07"), "C#程序员"));
        list.add(new Person(3L, "小李", "中国", 20, DateUtil.parseDate("1999-11-07"), "C#程序员"));
        personService.insert(ElastcSearchConstants.INDEX_LUNA_PERSON, list);
    }

    /**
     * 测试更新
     */
    @Test
    public void updateTest() {
        Person person = new Person(4L, "小李", "中国", 20, DateUtil.parseDate("1999-11-07"), "C#程序员");
        List<Person> list = new ArrayList<>();
        list.add(person);
        personService.update(ElastcSearchConstants.INDEX_LUNA_PERSON, list);
    }

    /**
     * 测试删除
     */
    @Test
    public void deleteTest() {
        Person person = new Person(5L, "小李", "中国", 20, DateUtil.parseDate("1999-11-07"), "C#程序员");

        personService.delete(ElastcSearchConstants.INDEX_LUNA_PERSON, person);
    }

    /**
     * 测试查询
     */
    @Test
    public void searchListTest() {
        List<Person> personList = personService.searchList(ElastcSearchConstants.INDEX_LUNA_PERSON);
        System.out.println(JSON.toJSONString(personList));
    }

}
