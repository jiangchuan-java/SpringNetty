package com.ifeng.fhh.fhhService.repository;

import java.util.List;

/**
 * 持久层序列化接口
 * <p>
 * Created by licheng1 on 2017/4/26.
 */
public interface Serializer<R> {

    <T> R serialize(T t, boolean escapeNull) throws Exception;

    <T> R serialize(T t) throws Exception;

    <T> T deserialize(R r, Class<T> tClass) throws Exception;

    <T> List<R> serializeList(List<T> tList, boolean escapeNull) throws Exception;

    <T> List<R> serializeList(List<T> tList) throws Exception;

    <T> List<T> deserializeList(List<R> rList, Class<T> tClass) throws Exception;
}
