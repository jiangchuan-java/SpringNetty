package com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 搜索引擎属性注解
 * <p>
 * Created by licheng1 on 2017/6/22.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchEngineProperty {

    /**
     * 搜索引擎字段属性
     *
     * @return 名称
     */
    String name();
}
