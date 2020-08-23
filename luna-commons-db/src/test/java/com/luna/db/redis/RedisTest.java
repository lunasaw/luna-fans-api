package com.luna.db.redis;

import com.luna.db.redis.util.RedisUtil;
import org.junit.Test;

import com.luna.db.DbApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Package: com.luna.db.redis
 * @ClassName: Test
 * @Author: luna
 * @CreateTime: 2020/8/23 16:31
 * @Description:
 */
public class RedisTest extends DbApplicationTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void atest() {
        redisUtil.set("luna","luna,redis");
    }

}
