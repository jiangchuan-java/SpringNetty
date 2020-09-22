package com.ifeng.fhh.zmt.web.route;

import com.ifeng.fhh.zmt.tools.JackSonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Des: 路由的最上层接口，通过此接口获取其实现类，拿到所有的路由
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-28
 */
public interface RouteAction {

    CompletableFuture<ActionResponse> route(ActionRequest request);

    public static String successfulJson(Object data) {
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("code", 1);
        respMap.put("msg", "success");
        respMap.put("data", data);
        return JackSonUtils.bean2JsonWithoutError(respMap);
    }

    public static String failedJson(String msg) {
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("code", 0);
        respMap.put("msg", msg);
        respMap.put("data", "");
        return JackSonUtils.bean2JsonWithoutError(respMap);
    }
}
