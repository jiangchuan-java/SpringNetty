package com.ifeng.fhh;

import com.ifeng.fhh.zmt.web.spring.SpringWebNetty;
import com.ifeng.fhh.zmt.web.spring.SpringWebNettyApplication;
import com.ifeng.fhh.zmt.web.spring.custom_autoProxy_annotation.EnableInterceptorProxyAnnotation;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-26
 */
@SpringWebNetty
@EnableInterceptorProxyAnnotation
public class WebStarter {

    public static void main(String[] args) {
        SpringWebNettyApplication springApplication = new SpringWebNettyApplication(WebStarter.class, 1,10,10);
        springApplication.run(args);
    }
}
