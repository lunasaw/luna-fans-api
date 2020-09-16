package com.luna.cache.config;

import com.google.common.hash.Hashing;
import com.luna.cache.constant.CacheConstant;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.time.Duration;

/**
 * @author luna
 */
@EnableCaching
@Configuration
@ComponentScan(basePackages = "com.luna.redis")
public class CacheConfig extends CachingConfigurerSupport {

    private static final Logger      log = LoggerFactory.getLogger(CacheConfig.class);



    @Autowired
    private LettuceConnectionFactory redisConnectionFactory;

    @Override
    public CacheManager cacheManager() {
        // 重新配置缓存
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        // 设置缓存的默认超时时间：30分钟
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(30L))
            .disableCachingNullValues()
            .disableKeyPrefix()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer((new GenericJackson2JsonRedisSerializer())));

        return RedisCacheManager.builder(RedisCacheWriter
            .nonLockingRedisCacheWriter(redisConnectionFactory))
            .cacheDefaults(redisCacheConfiguration).build();
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder key = new StringBuilder(CacheConstant.KEY);
                key.append(":").append(target.getClass().getSimpleName()).append(":").append(method.getName())
                    .append(":");
                if (params.length == 0) {
                    return key.append(CacheConstant.NO_PARAM_KEY).toString();
                }
                return getObject(method, key, log, params);
            }
        };
    }

    @NotNull
    static Object getObject(Method method, StringBuilder key, Logger log, Object[] params) {
        for (Object param : params) {
            if (param == null) {
                log.warn("input null param for Spring cache, use default key={}", CacheConstant.NULL_PARAM_KEY);
                key.append(CacheConstant.NULL_PARAM_KEY);
            } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
                int length = Array.getLength(param);
                for (int i = 0; i < length; i++) {
                    key.append(Array.get(param, i));
                    key.append(',');
                }
            } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
                key.append(param);
            } else {
                key.append(param.hashCode());
            }
            key.append('-');
        }

        String finalKey = key.toString();
        long cacheKeyHash = Hashing.murmur3_128().hashString(finalKey, Charset.defaultCharset()).asLong();
        log.debug("using cache key={} hashCode={}", finalKey, cacheKeyHash);
        return key.toString();
    }

}
