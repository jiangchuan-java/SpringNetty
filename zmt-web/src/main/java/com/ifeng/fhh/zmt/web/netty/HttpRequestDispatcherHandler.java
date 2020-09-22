package com.ifeng.fhh.zmt.web.netty;

import com.ifeng.fhh.zmt.web.route.ActionRequest;
import com.ifeng.fhh.zmt.web.route.ActionResponse;
import com.ifeng.fhh.zmt.web.route.RouteAction;
import com.ifeng.fhh.zmt.web.worker.Aladdin;
import com.ifeng.fhh.zmt.web.worker.WorkingContext;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-2
 */
@ChannelHandler.Sharable
public class HttpRequestDispatcherHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestDispatcherHandler.class);

    /*为啥用hashmap呢，因为spring初始化是synchorinzed加持的，所以不会有线程安全问题*/
    private Map<String, RouteAction> routeActionMap = new HashMap<String, RouteAction>();

    private ConfigurableApplicationContext springApplicationContext;

    public HttpRequestDispatcherHandler(ConfigurableApplicationContext springApplicationContext) {
        this.springApplicationContext = springApplicationContext;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info(Thread.currentThread().getName() + " >>>>>>>> read");
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            LOGGER.info(Thread.currentThread().getName() + " >>>>>>>> uri : {}", httpRequest.uri());
            dispatch(ctx, httpRequest);
        }
    }


    /**
     * 处理具体的uri
     *
     * @param ctx netty的channel上下文
     * @param httpRequest netty的请求对象
     */
    public void dispatch(ChannelHandlerContext ctx, FullHttpRequest httpRequest){

        ActionRequest actionRequest = new ActionRequest(httpRequest);
        doDispatch(ctx, actionRequest);
    }




    /**
     * 注册uri映射关系
     *
     * @param path uri
     * @param action 处理uri的requestMapping
     */
    public void registerAction(String path, RouteAction action){
        this.routeActionMap.put(path, action);
    }



    /**
     * 处理的具体实现
     *
     * @param ctx netty的channel上下文
     * @param actionRequest 转换后的请求对象，可以在业务中使用的
     */
    public void doDispatch(ChannelHandlerContext ctx, ActionRequest actionRequest) {
        Aladdin aladdin = springApplicationContext.getBean(Aladdin.class);
        String path = actionRequest.getPath();
        RouteAction routeAction = routeActionMap.get(path);
        if(routeAction != null){
            WorkingContext context = aladdin.getOrCreateContext();
            context.runOnContext(()->{
                LOGGER.info(Thread.currentThread().getName()+ " >>>>>>>> route action");
                CompletableFuture<ActionResponse> routeFuture = routeAction.route(actionRequest);
                routeFuture.whenComplete(new BiConsumer<ActionResponse, Throwable>() {
                    @Override
                    public void accept(ActionResponse result, Throwable throwable) {
                        doResponse(ctx, result, throwable);
                    }
                });
            });
        } else {
            LOGGER.info("path: {} not exits ", path);
            // TODO: 20-8-31 404 page not found
            do404Response(ctx, null);
        }


    }

    /**
     * 响应结果
     *
     * @param ctx netty的channel上下文
     * @param response uri的响应结果
     * @param throwable 异常
     */
    private void doResponse(ChannelHandlerContext ctx, ActionResponse response, Throwable throwable) {
        LOGGER.info(Thread.currentThread().getName()+ " >>>>>>>> response");
        // 1.设置响应

        FullHttpResponse resp = null;
        if(throwable != null){
            resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    response.getStatus(),
                    Unpooled.copiedBuffer(response.getResp(), CharsetUtil.UTF_8));
        } else {
            resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    response.getStatus(),
                    Unpooled.copiedBuffer(response.getResp(), CharsetUtil.UTF_8));
        }

        for(CharSequence key : response.getHeads().keySet()){
            resp.headers().set(key, response.getHeads().get(key));
        }

        resp.headers().set(HttpHeaderNames.CONTENT_LENGTH, resp.content().readableBytes());

        // 2.发送
        // 注意必须在使用完之后，close channel
        //ctx.writeAndFlush(resp); //这种方式是找next()，然后进行调用
        ctx.channel().writeAndFlush(resp); //这种方式是从tail开始，从后往前进行调用
    }

    /**
     * uri找不到时，404
     *
     * @param ctx
     * @param result
     */
    private void do404Response(ChannelHandlerContext ctx, String result) {
        LOGGER.info(Thread.currentThread().getName()+ " >>>>>>>> response");
        // 1.设置响应


        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.NOT_FOUND);


        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        resp.headers().set(HttpHeaderNames.CONTENT_LENGTH, resp.content().readableBytes());

        // 2.发送
        // 注意必须在使用完之后，close channel
        //ctx.writeAndFlush(resp); //这种方式是找next()，然后进行调用
        ctx.channel().writeAndFlush(resp); //这种方式是从tail开始，从后往前进行调用
    }
}