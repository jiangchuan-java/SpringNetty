package com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略搜索引擎字段标记注解
 * <p>
 * Created by licheng1 on 2017/4/26.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchEngineIgnore {
}
