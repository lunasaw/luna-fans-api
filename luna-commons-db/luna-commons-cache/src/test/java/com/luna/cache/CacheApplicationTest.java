package com.luna.cache;

import com.luna.cache.task.RedisTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Package: com.luna.cache
 * @ClassName: CacheApplicationTest
 * @Author: luna
 * @CreateTime: 2020/9/14 12:21
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTest {

    @Autowired
    private RedisTask redisTask;

    @Test
    public void cleanRedis() {
        redisTask.cleanRedis();
    }

}
