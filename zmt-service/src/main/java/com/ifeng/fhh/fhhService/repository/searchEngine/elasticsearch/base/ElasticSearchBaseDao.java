package com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.base;

import com.ifeng.fhh.fhhService.repository.Serializer;
import com.ifeng.fhh.zmt.tools.JackSonUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * elasticsearch 基础数据访问接口
 * <p>
 * Created by licheng1 on 2017/4/28.
 */
public interface ElasticSearchBaseDao extends Serializer<Map<String, Object>> {

    /**
     * 注入elasticsearch数据访问模板
     *
     * @param template template
     */
    void setElasticSearchTemplate(ElasticSearchTemplate template);

    /**
     * 获取elasticsearch数据访问模板
     *
     * @return template
     */
    ElasticSearchTemplate getElasticSearchTemplate();

    /**
     * 搜索引擎记录序列化
     *
     * @param t          pojo对象
     * @param escapeNull 是否忽略空值
     * @param <T>        类型
     * @return map
     * @throws Exception
     */
    @Override
    default <T> Map<String, Object> serialize(T t, boolean escapeNull) throws Exception {
        Objects.requireNonNull(t);
        Class<?> clazz = t.getClass();
        Map<String, Object> source = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (this.checkIgnore(field)) {
                continue;
            }
            /*搜索引擎名称*/
            String name = this.fieldName(field);
            Object value = field.get(t);
            if (escapeNull && value == null) {
                continue;
            }
            source.put(name, value);
        }
        /*去除主键*/
        source.remove("_id");
        source.remove("id");
        return source;
    }

    /**
     * invoke serialize(t, true)
     *
     * @param t   pojo对象
     * @param <T> 类型
     * @return map
     * @throws Exception
     */
    @Override
    default <T> Map<String, Object> serialize(T t) throws Exception {
        return this.serialize(t, true);
    }

    /**
     * 搜索引擎记录反序列化
     *
     * @param map    map
     * @param tClass pojo类对象
     * @param <T>    类型
     * @return pojo集合
     * @throws Exception
     */
    @Override
    default <T> T deserialize(Map<String, Object> map, Class<T> tClass) throws Exception {
        String json = JackSonUtils.bean2JsonUTCDate(map);
        return JackSonUtils.json2BeanUTCDate(
                json,
                tClass
        );
    }

    /**
     * 搜索引擎记录集合序列化
     *
     * @param tList      pojo集合
     * @param escapeNull 是否忽略空值
     * @param <T>        类型
     * @return map集合
     * @throws Exception
     */
    @Override
    default <T> List<Map<String, Object>> serializeList(List<T> tList, boolean escapeNull) throws Exception {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (T t : tList) {
            maps.add(
                    this.serialize(t, escapeNull)
            );
        }
        return maps;
    }

    /**
     * invoke serializeList(tList, true)
     *
     * @param tList pojo集合
     * @param <T>   类型
     * @return map集合
     * @throws Exception
     */
    @Override
    default <T> List<Map<String, Object>> serializeList(List<T> tList) throws Exception {
        return this.serializeList(tList, true);
    }

    /**
     * 搜索引擎记录集合反序列化
     *
     * @param maps   map集合
     * @param tClass pojo类对象
     * @param <T>    类型
     * @return pojo集合
     * @throws Exception
     */
    @Override
    default <T> List<T> deserializeList(List<Map<String, Object>> maps, Class<T> tClass) throws Exception {
        List<T> tList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            tList.add(
                    this.deserialize(map, tClass)
            );
        }
        return tList;
    }

    /**
     * 检测字段忽略标记
     *
     * @param field 字段
     * @return 是否忽略
     */
    default boolean checkIgnore(Field field) {
        SearchEngineIgnore ignore = field.getAnnotation(SearchEngineIgnore.class);
        return ignore != null;
    }

    /**
     * 检测字段搜索引擎名称
     *
     * @param field 字段
     * @return 名称
     */
    default String fieldName(Field field) {
        String name = field.getName();
        SearchEngineProperty property = field.getAnnotation(SearchEngineProperty.class);
        if (property != null) {
            name = property.name();
        }
        return name;
    }
}
