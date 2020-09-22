package com.ifeng.fhh.zmt.web.spring;


import com.ifeng.fhh.zmt.web.netty.HttpRequestDispatcherHandler;
import com.ifeng.fhh.zmt.web.netty.HttpResponseHandler;
import com.ifeng.fhh.zmt.web.netty.NettyServer;
import com.ifeng.fhh.zmt.web.route.ActionRequest;
import com.ifeng.fhh.zmt.web.route.RouteAction;
import com.ifeng.fhh.zmt.web.worker.Aladdin;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-2
 */
public class SpringWebNettyApplication extends AnnotationConfigApplicationContext {

    private Class<?> sourceClass;

    private NettyServer nettyServer;

    private HttpRequestDispatcherHandler httpRequestDispatcherHandler;

    private HttpResponseHandler httpResponseHandler;

    private int nettyBossThreads;

    private int nettyWorkerThreads;

    private int normalWorkerThreads;

    private String[] args;

    public SpringWebNettyApplication(Class<?> sourceClass, int nettyBossThreads, int nettyWorkerThreads, int normalWorkerThreads) {
        this.sourceClass = sourceClass;
        this.nettyBossThreads = nettyBossThreads;
        this.nettyWorkerThreads = nettyWorkerThreads;
        this.normalWorkerThreads = normalWorkerThreads;
    }

    /**
     * 启动入口
     *
     * @param args
     * @return
     */
    public ConfigurableApplicationContext run(String[] args) {
        this.args = args;
        this.register(sourceClass);
        this.registerBean(Aladdin.class, normalWorkerThreads);
        this.refresh();
        this.registerShutdownHook();
        return this;
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        try {
            createWebServer();
        } catch (Throwable ex) {
            throw new ApplicationContextException("Unable to start reactive web server", ex);
        }
    }

    @Override
    protected void finishRefresh() {
        super.finishRefresh();
        try {
            initWebServer();
        } catch (Throwable ex) {
            throw new ApplicationContextException("Unable to start reactive web server", ex);
        }
    }

    /**
     * 创建netty服务，并添加对应的handler
     *
     * @throws Exception
     */
    private void createWebServer() throws Exception {
        this.httpRequestDispatcherHandler = new HttpRequestDispatcherHandler(this);
        this.httpResponseHandler = new HttpResponseHandler();
        List<ChannelInboundHandlerAdapter> inboundHandlers = new ArrayList<>();
        inboundHandlers.add(httpRequestDispatcherHandler);
        List<ChannelOutboundHandlerAdapter> outboundHandlers = new ArrayList<>();
        outboundHandlers.add(httpResponseHandler);
        this.nettyServer = new NettyServer(nettyBossThreads, nettyWorkerThreads, inboundHandlers, outboundHandlers);
    }

    /**
     * 初始化web服务
     * 1：读取所有action类
     * 2：解析并映射uri与action的关系
     * 3：启动netty监听端口
     *
     * @throws Exception
     */
    private void initWebServer() throws Exception {
        Map<String, RouteAction> routeActions = this.getBeansOfType(RouteAction.class);
        for(RouteAction routeAction : routeActions.values()){
            Class classObject = routeAction.getClass();
            if(AopUtils.isAopProxy(routeAction)){
                classObject = AopUtils.getTargetClass(routeAction);
            }
            Method routeMethod = classObject.getDeclaredMethod("route", ActionRequest.class);

            /*注解是常量，编译的时候就生产好了，在二进制class文件里的常量池中*/
            RequestMapping requestMapping = routeMethod.getAnnotation(RequestMapping.class);
            RequestMethod[] methods = requestMapping.method();
            String[] pathArray = requestMapping.path();
            for(String path : pathArray){
                httpRequestDispatcherHandler.registerAction(path, routeAction);
                logger.info(path+" >>>>>>>> "+routeAction);
            }
        }

        String server_port = "8080";
        Integer port;
        for(String arg : args){
            if(arg.contains("--server.port=8125")){
                server_port = arg.replace("--server.port=","");
                break;
            }
        }
        try {
            port = Integer.valueOf(server_port);
        } catch (NumberFormatException e) {
            throw new Exception("Argument of spring app 'server.port' is illegal.", e);
        }
        this.nettyServer.start(port);
    }


}
