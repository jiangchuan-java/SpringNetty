package com.ifeng.fhh.fhhService.repository.database.mongodb.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略数据库字段标记注解
 * <p>
 * Created by licheng1 on 2016/12/26.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseIgnore {
}
