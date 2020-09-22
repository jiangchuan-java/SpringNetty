package com.ifeng.fhh.fhhService.interceptor;

import com.ifeng.fhh.zmt.web.spring.custom_autoProxy_annotation.InterceptorAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
@Component(value = "JustForTest")
@InterceptorAutoProxy(interceptorNames = "AuthorityInterceptor")
public class JustForTest {

    public void doWork(){
        System.out.println("I'm doing ...");
    }

    public void stopWork(){
        System.out.println("stop work ...");
    }
}
