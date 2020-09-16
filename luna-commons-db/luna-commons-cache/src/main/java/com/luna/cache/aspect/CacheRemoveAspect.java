package com.luna.cache.aspect;

import com.luna.cache.anno.CacheRemove;
import com.luna.redis.util.RedisKeyUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class CacheRemoveAspect {

    private static final Logger log = LoggerFactory.getLogger(CacheRemoveAspect.class);

    @Autowired
    private RedisKeyUtil        redisKeyUtil;

    /**
     * 截获标有@CacheRemove的方法
     */
    @Pointcut(value = "@annotation(com.luna.cache.anno.CacheRemove))")
    private void pointcut() {}

    /**
     * 功能描述: 切面在截获方法返回值之后
     * 
     * @param joinPoint
     */
    @AfterReturning(value = "pointcut()")
    private void process(JoinPoint joinPoint) {
        // 获取被代理的类
        Object target = joinPoint.getTarget();
        // 获取切入方法的数据
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        // 获取切入方法
        Method method = signature.getMethod();
        // 获得注解
        CacheRemove cacheRemove = method.getAnnotation(CacheRemove.class);

        if (cacheRemove != null) {
            if (cacheRemove.cleanCalss()) {
                // 清除当前类的缓存
                cleanRedisCache("*" + target.getClass().toString() + "*");
            }

            String value = cacheRemove.value();
            if (!"".equals(value)) {
                // 清除缓存的项目所有redis业务部缓存
                cleanRedisCache("*" + value + "*");
            }
            // 需要移除的正则key
            String[] keys = cacheRemove.key();
            for (String key : keys) {
                // 清除指定清除的key的缓存
                cleanRedisCache("*" + key + "*");
            }
        }
    }

    private void cleanRedisCache(String key) {
        if (key != null) {
            List<String> keys = redisKeyUtil.getKeys(key);
            redisKeyUtil.deleteKey(keys);
            // 删除缓存
            log.info("清除 " + key + " 缓存");
        }
    }

}