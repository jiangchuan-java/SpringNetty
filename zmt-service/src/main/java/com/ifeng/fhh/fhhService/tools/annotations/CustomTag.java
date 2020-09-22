package com.ifeng.fhh.fhhService.tools.annotations;

import java.lang.annotation.*;

/**
 * Created by qinfuji on 18-3-14.
 */
@Target({ElementType.PARAMETER , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomTag {
    String value();
}
