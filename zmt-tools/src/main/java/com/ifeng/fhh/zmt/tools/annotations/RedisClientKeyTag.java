package com.ifeng.fhh.zmt.tools.annotations;

import java.lang.annotation.*;

/**
 * Created by qinfuji on 18-3-13.
 */
@Target({ElementType.PARAMETER , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisClientKeyTag {
    String value() default "RedisClientKey";
}
