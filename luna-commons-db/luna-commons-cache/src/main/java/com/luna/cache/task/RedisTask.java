package com.luna.cache.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.luna.cache.anno.CacheRemove;
import com.luna.cache.constant.CacheConstant;
import com.luna.common.utils.text.DateUtil;

/**
 * @Package: com.luna.cache.task
 * @ClassName: RedisTask
 * @Author: luna
 * @CreateTime: 2020/9/15 21:43
 * @Description:
 */
@Configuration
@EnableScheduling
public class RedisTask {

    private static final Logger log = LoggerFactory.getLogger(RedisTask.class);

    /**
     * 每30天清理过期缓存
     */
    @Scheduled(cron = "* * * 1/30 * ?")
    @CacheRemove(value = CacheConstant.KEY)
    public void cleanRedis() {
        log.info("cleanRedis start time={}", DateUtil.getTime());
    }

}
