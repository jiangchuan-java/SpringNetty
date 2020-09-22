package com.ifeng.fhh.zmt.web.util;

import com.google.gson.Gson;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-2
 */
public class GsonUtil {

    private static Gson gson = new Gson();

    public static <T> T json2Bean(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
