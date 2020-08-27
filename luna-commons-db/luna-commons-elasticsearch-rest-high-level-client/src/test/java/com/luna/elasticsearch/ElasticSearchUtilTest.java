package com.luna.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.luna.elasticsearch.contants.ElasticsearchConstant;
import com.luna.elasticsearch.demo.pojo.Person;
import com.luna.elasticsearch.dto.PageDTO;
import com.luna.elasticsearch.util.ElasticSearchDeleteUtil;
import com.luna.elasticsearch.util.ElasticSearchGetUtil;
import com.luna.elasticsearch.util.ElasticSearchInsertUtil;
import com.luna.elasticsearch.util.ElasticSearchUpdateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.elasticsearch
 * @ClassName: ElasticSearchUtilTest
 * @Author: luna
 * @CreateTime: 2020/8/25 14:24
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchUtilTest {

    /**
     * 删除索引和ID对应数据
     */
    @Test
    public void deleteIndexTest() {
        ElasticSearchDeleteUtil.deleteRequest(ElasticsearchConstant.INDEX_NAME, "luna");
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() {
        ElasticSearchDeleteUtil.deleteIndexRequest(ElasticsearchConstant.INDEX_NAME);
    }

    /**
     * 判断索引是否存在
     */
    @Test
    public void checkIndexExist() {
        boolean indexExist = ElasticSearchInsertUtil.isIndexExist(ElasticsearchConstant.INDEX_NAME);
        System.out.println(indexExist);
    }

    /**
     * 测试创建索引
     */
    @Test
    public void createIndex() {
        ElasticSearchInsertUtil.createIndexRequest(ElasticsearchConstant.INDEX_NAME);
    }

    /**
     * 测试添加数据
     */
    @Test
    public void insertDataTest() {
        Person person = Person.Builder.aPerson().age(11).birthday(new Date()).country("CN").id(1L).name("小明")
            .remark("test1").build();
        ElasticSearchInsertUtil.addData(person, ElasticsearchConstant.INDEX_NAME);
    }

    /**
     * 测试添加数据
     */
    @Test
    public void insertDataWithIDTest() {
        Person person = Person.Builder.aPerson().age(11).birthday(new Date()).country("CN").id(1L).name("小明")
            .remark("test2").build();
        ElasticSearchInsertUtil.addData(person, ElasticsearchConstant.INDEX_NAME, "luna");
    }

    /**
     * 测试获取数据
     */
    @Test
    public void getDataTest() {
        Map<String, Object> stringObjectMap =
            ElasticSearchGetUtil.searchDataById(ElasticsearchConstant.INDEX_USER, "2", "name,age");
        System.out.println(JSON.toJSONString(stringObjectMap));
    }

    /**
     * 测试更新
     */
    @Test
    public void updateTest() {
        Person person = Person.Builder.aPerson().age(11).birthday(new Date()).country("CN").id(1L).name("小明")
            .remark("testupdate").build();
        ElasticSearchUpdateUtil.updateRequest(ElasticsearchConstant.INDEX_NAME, "luna", person);
    }

    /**
     * 删除数据
     */
    @Test
    public void deleteTest() {
        ElasticSearchDeleteUtil.deleteRequest(ElasticsearchConstant.INDEX_NAME, "luna");
    }

    /**
     * 查询索引对应的全部数据
     */
    @Test
    public void getAll() {
        List<Map<String, Object>> list = ElasticSearchGetUtil.searchAll(ElasticsearchConstant.INDEX_NAME);
        System.out.println(JSON.toJSONString(list));
    }

    /**
     * 根据索引和ID查询数据
     */
    @Test
    public void searchData() {
        List<Map<String, Object>> list =
            ElasticSearchGetUtil.searchListData(ElasticsearchConstant.INDEX_NAME, null, null, null, "age", null);
        System.out.println(JSON.toJSONString(list));
    }

    /**
     * 根据索引和ID查询数据
     */
    @Test
    public void searchDataByPage() {
        PageDTO pageDTO =
            ElasticSearchGetUtil.searchDataPage(ElasticsearchConstant.INDEX_NAME, 1, 2, null, "age", "remark", "50%",
                "age", null, null, null, "age");
        System.out.println(JSON.toJSONString(pageDTO));
    }

}
