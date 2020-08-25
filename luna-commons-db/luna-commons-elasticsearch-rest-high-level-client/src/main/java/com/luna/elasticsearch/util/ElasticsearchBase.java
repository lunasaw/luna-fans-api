package com.luna.elasticsearch.util;

import com.luna.elasticsearch.config.ElasticsearchProperties;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * BaseElasticsearchService
 *
 * @author fxbin
 * @version 1.0v
 * @since 2019/9/16 15:44
 */
@Component
public class ElasticsearchBase {

    public static final Logger             log = LoggerFactory.getLogger(ElasticsearchBase.class);

    @Autowired
    public RestHighLevelClient             restHighLevelClient;

    @Autowired
    public ElasticsearchProperties         elasticsearchProperties;

    public static ElasticsearchProperties properties;

    public static RestHighLevelClient     client;

    /**
     * @PostContruct是spring框架的注解
     * spring容器初始化的时候执行该方法
     */
    @PostConstruct
    public void init() {
        properties = this.elasticsearchProperties;
        client = this.restHighLevelClient;
    }

    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();

        // 默认缓冲限制为100MB，此处修改为30MB。
        builder.setHttpAsyncResponseConsumerFactory(
            new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

}
