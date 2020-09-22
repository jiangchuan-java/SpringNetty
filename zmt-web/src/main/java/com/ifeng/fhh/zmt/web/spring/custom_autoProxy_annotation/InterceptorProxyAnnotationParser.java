package com.ifeng.fhh.zmt.web.spring.custom_autoProxy_annotation;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Objects;

/**
 * @Des: 解析自定义的CGLIBAutoProxy标签，并生成动态代理类
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
public class InterceptorProxyAnnotationParser implements BeanPostProcessor, BeanFactoryPostProcessor {


    private ConfigurableListableBeanFactory beanFactory;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        InterceptorAutoProxy autoProxy = bean.getClass().getAnnotation(InterceptorAutoProxy.class);
        if (Objects.nonNull(autoProxy)) {
            String[] InterceptorNames =  autoProxy.interceptorNames();
            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
            proxyFactoryBean.setTarget(bean);
            proxyFactoryBean.setInterceptorNames(InterceptorNames);
            proxyFactoryBean.setProxyTargetClass(true);
            proxyFactoryBean.setBeanFactory(beanFactory);
            return proxyFactoryBean;
        }
        return bean;
    }

    /*只要spring不大改版，这个方法肯定先发生*/
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (this.beanFactory == null) {
            this.beanFactory = beanFactory;
        }
    }
}
