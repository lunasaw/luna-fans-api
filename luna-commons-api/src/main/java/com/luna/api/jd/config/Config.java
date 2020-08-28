package com.luna.api.jd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luna.elasticsearch.util.BulkRestUtil;
import com.luna.elasticsearch.util.DocRestUtil;
import com.luna.elasticsearch.util.IndexRestUtil;
import com.luna.elasticsearch.util.SearchRestUtil;

/**
 * @Package: com.luna.api.jd
 * @ClassName: Config
 * @Author: luna
 * @CreateTime: 2020/8/28 14:09
 * @Description:
 */
@Configuration
public class Config {

    @Bean
    public BulkRestUtil bulkRestUtil(){
        return new BulkRestUtil();
    }

    @Bean
    public DocRestUtil docRestUtil(){
        return new DocRestUtil();
    }

    @Bean
    public IndexRestUtil indexRestUtil(){
        return new IndexRestUtil();
    }

    @Bean
    public SearchRestUtil searchRestUtil(){
        return new SearchRestUtil();
    }
}
