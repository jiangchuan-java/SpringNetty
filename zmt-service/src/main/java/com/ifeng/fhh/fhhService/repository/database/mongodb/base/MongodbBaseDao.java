package com.ifeng.fhh.fhhService.repository.database.mongodb.base;

import com.ifeng.fhh.fhhService.repository.Serializer;
import com.ifeng.fhh.fhhService.tools.annotations.AllowNull;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * mongodb基础数据持久接口
 * <p>
 * Created by licheng1 on 2016/12/22.
 */
public interface MongodbBaseDao extends Serializer<Document> {

    /**
     * 注入mongodb数据访问模板
     *
     * @param template 模板
     */
    void setMongoTemplate(MongoTemplate template);

    /**
     * 获取mongodb数据访问模板
     *
     * @return template 模板
     */
    MongoTemplate getMongoTemplate();

    /**
     * 数据库记录集合反序列化
     *
     * @param documents bson集合
     * @param clazz     pojo类
     * @param <T>       pojo类型
     * @return pojo list
     * @throws Exception
     */
    @Override
    default <T> List<T> deserializeList(List<Document> documents, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        for (Document document : documents) {
            list.add(
                    this.deserialize(document, clazz)
            );
        }
        return list;
    }

    /**
     * invoke serializeList(list, true)
     *
     * @param list pojo集合
     * @param <T>  pojo类型
     * @return bson list
     * @throws Exception
     */
    @Override
    default <T> List<Document> serializeList(List<T> list) throws Exception {
        return this.serializeList(list, true);
    }

    /**
     * 数据库记录集合序列化
     *
     * @param list       pojo集合
     * @param escapeNull 是否跳过null值
     * @param <T>        pojo类型
     * @return bson list
     * @throws Exception
     */
    @Override
    default <T> List<Document> serializeList(List<T> list, boolean escapeNull) throws Exception {
        List<Document> documents = new ArrayList<>();
        for (T t : list) {
            documents.add(
                    this.serialize(t, escapeNull)
            );
        }
        return documents;
    }


    default <K, V> Document serializeMap(Map<K, V> map, boolean escapeNull) throws Exception {
        Document document = new Document();
        for (K key : map.keySet()) {
            document.append(key.toString(), map.get(key));
        }
        return document;
    }

    /**
     * 数据库记录序列化
     *
     * @param t          pojo
     * @param escapeNull 是否跳过null值
     * @param <T>        pojo类型
     * @return bson
     * @throws Exception
     */
    @Override
    default <T> Document serialize(T t, boolean escapeNull) throws Exception {
        Objects.requireNonNull(t);
        Class<?> clazz = t.getClass();
        Document document = new Document();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (this.checkIgnore(field)) {
                continue;
            }
            /*数据库类型*/
            Class<?> fieldType = this.fieldType(field);
            /*数据库名称*/
            String name = this.fieldName(field);
            Object value = field.get(t);
            AllowNull allowNull = field.getAnnotation(AllowNull.class);
            if (Objects.nonNull(allowNull)) ;
            else {
                if (escapeNull && value == null) {
                    continue;
                }
            }
            value = this.convertValue(value, fieldType);
            document.append(name, value);
        }
        return document;
    }

    /**
     * 序列化->包含父类属性
     *
     * @param list
     * @param <T>
     * @return
     * @throws Exception
     */
    default <T> List<Document> serializeListWithParents(List<T> list) throws Exception {
        return this.serializeListWithParents(list, true);
    }


    /**
     * 序列化->包含父类属性
     *
     * @param list
     * @param <T>
     * @return
     * @throws Exception
     */
    default <T> List<Document> serializeListWithParents(List<T> list, boolean escapeNull) throws Exception {
        List<Document> documents = new ArrayList<>();
        for (T t : list) {
            documents.add(
                    this.serializeWithParents(t, escapeNull)
            );
        }
        return documents;
    }

    /**
     * 数据库记录序列化包含父类中的字段
     *
     * @param t          pojo
     * @param escapeNull 是否跳过null值
     * @param <T>        pojo类型
     * @return bson
     * @throws Exception
     */
    default <T> Document serializeWithParents(T t, boolean escapeNull) throws Exception {
        Objects.requireNonNull(t);
        Class<?> clazz = t.getClass();
        List<Field> totalFiledList = new ArrayList<>();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                List<Field> list = Arrays.asList(fields);
                totalFiledList.addAll(list);
            } catch (Exception e) {
                //这里甚么都不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会进入
            }
        }
        Document document = new Document();
        for (Field field : totalFiledList) {
            field.setAccessible(true);
            if (this.checkIgnore(field)) {
                continue;
            }
            /*数据库类型*/
            Class<?> fieldType = this.fieldType(field);
            /*数据库名称*/
            String name = this.fieldName(field);
            Object value = field.get(t);
            if (escapeNull && value == null) {
                continue;
            }
            value = this.convertValue(value, fieldType);
            document.append(name, value);
        }
        return document;
    }

    /**
     * invoke serialize(t, true)
     *
     * @param t   pojo
     * @param <T> pojo类型
     * @return bson
     * @throws Exception
     */
    @Override
    default <T> Document serialize(T t) throws Exception {
        return this.serialize(t, true);
    }


    /**
     * 数据库记录反序列化
     *
     * @param bson  数据库记录
     * @param clazz pojo类
     * @param <T>   类型
     * @return pojo
     * @throws Exception
     */
    @Override
    default <T> T deserialize(Document bson, Class<T> clazz) throws Exception {
        Objects.requireNonNull(bson);
        Objects.requireNonNull(clazz);
        Object o = clazz.newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (this.checkIgnore(field)) {
                continue;
            }
            /*数据库类型*/
            Class<?> fieldType = this.fieldType(field);
            Type genericType = field.getGenericType();
            /*数据库名称*/
            String name = this.fieldName(field);
            Object value = bson.get(name, fieldType);
            /*field.getType()为java(pojo)类型*/
            value = this.wrapValue(value, field.getType(), genericType);
            field.set(o, value);
        }
        o = Objects.requireNonNull(o);
        return clazz.cast(o);
    }

    /**
     * 检查ObjectId hexString有效性
     *
     * @param hexString hexString
     */
    default void validObjectId(String hexString) {
        if (!ObjectId.isValid(hexString))
            throw new RuntimeException("illegal ObjectId hexString");
    }

    /**
     * 创建ObjectId
     *
     * @param hex hexString
     * @return objectId
     */
    default ObjectId objectId(String hex) {
        Objects.requireNonNull(hex);
        validObjectId(hex);
        return new ObjectId(hex);
    }

    /**
     * 检测字段忽略标记
     *
     * @param field 字段
     * @return 是否忽略
     */
    default boolean checkIgnore(Field field) {
        DatabaseIgnore ignore = field.getAnnotation(DatabaseIgnore.class);
        return ignore != null;
    }

    /**
     * 生成ObjectId
     *
     * @return ObjectId
     */
    default ObjectId objectId() {
        return new ObjectId();
    }

    /**
     * 检测字段数据库名称
     *
     * @param field 字段
     * @return 名称
     */
    default String fieldName(Field field) {
        String name = field.getName();
        DatabaseProperty property = field.getAnnotation(DatabaseProperty.class);
        if (property != null) {
            name = property.name();
        }
        return name;
    }

    /**
     * 检测字段数据库类型
     *
     * @param field 字段
     * @return 类型
     */
    default Class<?> fieldType(Field field) {
        Class<?> clazz = field.getType();
        DatabaseType databaseType = field.getAnnotation(DatabaseType.class);
        if (databaseType != null) {
            DBType type = databaseType.type();
            if (Objects.equals(type, DBType.BSON_OBJECT_ID))
                clazz = ObjectId.class;
            else if (Objects.equals(type, DBType.BSON_SUB_DOCUMENT))
                clazz = Document.class;
        }
        return clazz;
    }

    /**
     * 转换持久化类型
     *
     * @param value 字段值
     * @param type  数据库字段类型
     * @return 新值
     */
    default Object convertValue(Object value, Class<?> type) throws Exception {
        if (value != null && type != null) {
            if (Objects.equals(type, ObjectId.class)) {
                value = objectId(value.toString());
            } else if (value instanceof Number
                    || value instanceof Character
                    || value instanceof Boolean) {

            } else if (type == String.class) {

            } else if (type == Date.class) {

            } else {
                if (type == List.class) {
                    List list = List.class.cast(value);
                    if (list.size() > 0) {
                        Object member = list.get(0);
                        if (member instanceof Number
                                || member instanceof Character
                                || member instanceof Boolean
                                || member instanceof String
                                || member instanceof Date) {
                        } else {
                            value = serializeList(list);
                        }
                    }
                } else {
                    value = serialize(value);
                }
            }
        }
        return value;
    }

    /**
     * 转换pojo类型
     *
     * @param value 字段值
     * @param type  pojo字段类型
     * @return 新值
     */
    default Object wrapValue(Object value, Class<?> type, Type genericType) throws Exception {
        if (value != null && type != null) {
            /*特殊处理放于分支顶端*/
            if (value instanceof ObjectId && Objects.equals(type, String.class)) {
                value = value.toString();
            } else if (value instanceof List && Objects.equals(type, List.class)) {
                List<?> array = List.class.cast(value);
                if (array.size() > 0) {
                    if (array.get(0) instanceof Document) {
                        ParameterizedType parameterizedType = ParameterizedType.class.cast(genericType);
                        Type[] args = parameterizedType.getActualTypeArguments();
                        Type listArg = args[0];
                        List<Document> documents = array.stream()
                                .map(Document.class::cast)
                                .collect(Collectors.toList());
                        value = this.deserializeList(documents, Class.class.cast(listArg));
                    }
                }
            } else if (value instanceof Document && Objects.equals(type, Map.class)) {
                Map map = new HashMap();
                Document document = Document.class.cast(value);
                for (String key : document.keySet()) {
                    map.put(key, document.get(key));
                }
                value = map;
            } else if (value instanceof Document) {
                Document document = Document.class.cast(value);
                value = this.deserialize(document, type);
            }
        }
        return value;
    }
}
