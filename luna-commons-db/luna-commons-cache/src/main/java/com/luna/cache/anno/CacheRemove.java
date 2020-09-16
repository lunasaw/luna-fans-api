package com.luna.cache.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @author luna
 */
@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheRemove {

    /**
     * 需要清除的大类(模糊匹配) 例如 dictTableCache 所有缓存
     *
     * @return
     */
    String value() default "";

    /**
     * 需要清除的具体的key类型(模糊匹配)
     *
     * @return
     */
    String[] key() default {};

    /**
     * 需要清理当前类的缓存
     * 
     * @return
     */
    boolean cleanCalss() default false;
}