package com.luna.elasticsearch.template;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import com.luna.elasticsearch.ElasticsearchApplicationTests;
import com.luna.elasticsearch.model.Person;

/**
 * <p>
 * 测试 ElasticTemplate 的创建/删除
 * </p>
 *
 * @package: com.xkcoding.elasticsearch.template
 * @description: 测试 ElasticTemplate 的创建/删除
 * @author: yangkai.shen
 * @date: Created in 2018-12-20 17:46
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class TemplateTest extends ElasticsearchApplicationTests {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 测试 ElasticTemplate 创建 index
     */
    @Test
    public void testCreateIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Person.class);

        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Person.class);
    }

    /**
     * 测试 ElasticTemplate 删除 index
     */
    @Test
    public void testDeleteIndex() {
        elasticsearchTemplate.deleteIndex(Person.class);
    }
}
