package com.ifeng.fhh.zmt.web.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

import java.lang.annotation.*;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ComponentScan.class) })
@ImportResource("classpath:applicationContext.xml")
public @interface SpringWebNetty {
}
