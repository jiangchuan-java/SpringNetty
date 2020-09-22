package com.ifeng.fhh.fhhService.tools.httpClient;

import com.ifeng.fhh.zmt.web.worker.Aladdin;
import com.ifeng.fhh.zmt.web.worker.WorkingContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.SSLContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-15
 */
public class ApacheAsyncHttpClient implements HttpClientTemplate{

    @Autowired
    private Aladdin aladdin;

    public static final String DEFAULT_CHARSET = "UTF-8";

    private static final String URL_TYPE_HTTPS = "https";

    private CloseableHttpAsyncClient asyncHttpClient;

    private CloseableHttpAsyncClient asyncHttpsClient;



    public ApacheAsyncHttpClient() throws Exception {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();
        // Allow TLSv1 protocol only
        SSLIOSessionStrategy sslSessionStrategy = new SSLIOSessionStrategy(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLIOSessionStrategy.getDefaultHostnameVerifier());

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();

        asyncHttpClient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setMaxConnPerRoute(50)
                .build();

        asyncHttpsClient =  HttpAsyncClients.custom()
                .setSSLStrategy(sslSessionStrategy)
                .setDefaultRequestConfig(requestConfig)
                .setMaxConnPerRoute(50)
                .build();

        asyncHttpClient.start();
        asyncHttpsClient.start();

    }

    @Override
    public CompletableFuture<String> get(String url) {
        CompletableFuture<HttpResponse> requestFuture = new CompletableFuture<>();
        CompletableFuture<String> responseFuture = new CompletableFuture<>();

        HttpGet httpGet = new HttpGet(url);
        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
            if (URL_TYPE_HTTPS.equals(httpUrl.getProtocol())) {
                asyncHttpsClient.execute(httpGet, wrapCallback(requestFuture));
            } else {
                asyncHttpClient.execute(httpGet, wrapCallback(requestFuture));
            }
        } catch (MalformedURLException e) {
            requestFuture.completeExceptionally(e);
        }
        requestFuture.whenComplete((httpResponse, throwable)->{
            try {
                if(throwable != null){
                    responseFuture.completeExceptionally(throwable);
                } else {
                    responseFuture.complete(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
                }
            }catch (Exception e){
                responseFuture.completeExceptionally(e);
            }

        });
        return responseFuture;
    }

    @Override
    public CompletableFuture<String> get(String url, Map<String, String> headers, int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        CompletableFuture<HttpResponse> requestFuture = new CompletableFuture<>();
        CompletableFuture<String> responseFuture = new CompletableFuture<>();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        if (Objects.nonNull(headers) && !headers.isEmpty())
            addHeaders(httpGet, headers);

        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
            if (URL_TYPE_HTTPS.equals(httpUrl.getProtocol())) {
                asyncHttpsClient.execute(httpGet, wrapCallback(requestFuture));
            } else {
                asyncHttpClient.execute(httpGet, wrapCallback(requestFuture));
            }
        } catch (MalformedURLException e) {
            requestFuture.completeExceptionally(e);
        }
        requestFuture.whenComplete((httpResponse, throwable)->{
            try {
                if(throwable != null){
                    responseFuture.completeExceptionally(throwable);
                } else {
                    responseFuture.complete(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
                }
            }catch (Exception e){
                responseFuture.completeExceptionally(e);
            }

        });
        return responseFuture;
    }

    @Override
    public CompletableFuture<String> post(String url, String params) {
        CompletableFuture<HttpResponse> requestFuture = new CompletableFuture<>();
        CompletableFuture<String> responseFuture = new CompletableFuture<>();

        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(params, DEFAULT_CHARSET);
        httpPost.setEntity(entity);

        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
            if (URL_TYPE_HTTPS.equals(httpUrl.getProtocol())) {
                asyncHttpsClient.execute(httpPost, wrapCallback(requestFuture));
            } else {
                asyncHttpClient.execute(httpPost, wrapCallback(requestFuture));
            }
        } catch (MalformedURLException e) {
            requestFuture.completeExceptionally(e);
        }
        requestFuture.whenComplete((httpResponse, throwable)->{
            try {
                if(throwable != null){
                    responseFuture.completeExceptionally(throwable);
                } else {
                    responseFuture.complete(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
                }
            }catch (Exception e){
                responseFuture.completeExceptionally(e);
            }

        });
        return responseFuture;
    }

    @Override
    public CompletableFuture<String> post(String url, String params, Map<String, String> headers, int socketTimeout, int connectTimeout, int connectionRequestTimeout) {
        CompletableFuture<HttpResponse> requestFuture = new CompletableFuture<>();
        CompletableFuture<String> responseFuture = new CompletableFuture<>();

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();

        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(params, DEFAULT_CHARSET);
        httpPost.setEntity(entity);

        httpPost.setConfig(requestConfig);
        if (Objects.nonNull(headers) && !headers.isEmpty())
            addHeaders(httpPost, headers);

        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
            if (URL_TYPE_HTTPS.equals(httpUrl.getProtocol())) {
                asyncHttpsClient.execute(httpPost, wrapCallback(requestFuture));
            } else {
                asyncHttpClient.execute(httpPost, wrapCallback(requestFuture));
            }
        } catch (MalformedURLException e) {
            requestFuture.completeExceptionally(e);
        }
        requestFuture.whenComplete((httpResponse, throwable)->{
            try {
                if(throwable != null){
                    responseFuture.completeExceptionally(throwable);
                } else {
                    responseFuture.complete(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
                }
            }catch (Exception e){
                responseFuture.completeExceptionally(e);
            }

        });
        return responseFuture;
    }

    public void addHeaders(HttpRequestBase request, Map<String, String> headers) {

        for (Map.Entry<String, String> header : headers.entrySet()) {
            request.addHeader(header.getKey(), header.getValue());
        }
    }


    private <T> FutureCallback<T> wrapCallback(CompletableFuture<T> future) {
        Objects.requireNonNull(aladdin, "aladdin cannot be null");
        //执行异步操作之前，获取当前线程绑定的context，一定是自己当前执行的，异步之后在获取肯定就不是自己了
        //每个线程会绑定多个context，当执行某个context时，获取的才是当前自身的
        //context绑定了执行线程，回来通过context获取之前的线程投放任务
        WorkingContext context = this.aladdin.getOrCreateContext();
        return new FutureCallback<T>() {
            @Override
            public void completed(T t) {
                context.runOnContext(()->{
                    future.complete(t);
                });
            }

            @Override
            public void failed(Exception e) {
                context.runOnContext(()->{
                    future.completeExceptionally(e);
                });
            }

            @Override
            public void cancelled() {

            }
        };
    }

    public Aladdin getAladdin() {
        return aladdin;
    }

    public void setAladdin(Aladdin aladdin) {
        this.aladdin = aladdin;
    }

}
