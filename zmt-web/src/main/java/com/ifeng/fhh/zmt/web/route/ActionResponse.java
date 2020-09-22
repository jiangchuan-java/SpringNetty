package com.ifeng.fhh.zmt.web.route;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-22
 */
public class ActionResponse {

    private HttpResponseStatus status;

    private Map<CharSequence, String> heads = new HashMap<>();

    private String resp;

    public ActionResponse () {
        heads.put(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8");
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public void setStatus(HttpResponseStatus status) {
        this.status = status;
    }

    public Map<CharSequence, String> getHeads() {
        return heads;
    }

    public void setHeads(Map<CharSequence, String> heads) {
        this.heads = heads;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
}
