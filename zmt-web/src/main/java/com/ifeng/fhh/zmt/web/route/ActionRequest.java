package com.ifeng.fhh.zmt.web.route;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.*;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-28
 */
public class ActionRequest {

    private HttpMethod method;
    private HttpVersion version;
    private String absoluteURI;
    private String host;
    private String uri;
    private String path;
    private String query;
    private SocketAddress localAddress;
    private SocketAddress remoteAddress;
    private Map<String, String> params = new HashMap<String, String>();
    private String bodyString;
    private Map<String, String> headers = new HashMap<String, String>();

    public ActionRequest(FullHttpRequest fullHttpRequest) {
        this.method = fullHttpRequest.method();
        this.version = fullHttpRequest.protocolVersion();
        this.absoluteURI = fullHttpRequest.uri();
        this.path = fullHttpRequest.uri().split("\\?")[0];
        params(fullHttpRequest);
        headers(fullHttpRequest);
    }

    void params(FullHttpRequest fullHttpRequest) {
        if (HttpMethod.POST.equals(method)) {
            ByteBuf byteBuf = fullHttpRequest.content();
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            this.bodyString = new String(bytes);
        } else if (HttpMethod.GET.equals(method)) {
            QueryStringDecoder decoder = new QueryStringDecoder(fullHttpRequest.uri());
            Map<String, List<String>> parameters = decoder.parameters();
            for(String key : parameters.keySet()){
                this.params.put(key, parameters.get(key).get(0));
            }
        } else {
            // TODO: 20-8-28 failed handler
        }
    }

    void headers(FullHttpRequest fullHttpRequest){
        HttpHeaders httpHeaders = fullHttpRequest.headers();
        List<Map.Entry<String, String>> entries = httpHeaders.entries();
        for(Map.Entry<String, String> entry : entries){
            headers.put(entry.getKey(), entry.getValue());
        }
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public HttpVersion getVersion() {
        return version;
    }

    public void setVersion(HttpVersion version) {
        this.version = version;
    }

    public String getAbsoluteURI() {
        return absoluteURI;
    }

    public void setAbsoluteURI(String absoluteURI) {
        this.absoluteURI = absoluteURI;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public SocketAddress getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(SocketAddress localAddress) {
        this.localAddress = localAddress;
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(SocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getBodyString() {
        return bodyString;
    }

    public void setBodyString(String bodyString) {
        this.bodyString = bodyString;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
