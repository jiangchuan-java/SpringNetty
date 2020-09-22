package com.ifeng.fhh.fhhService.repository.database.mongodb.base;

/**
 * 数据库类型
 * <p>
 * Created by licheng1 on 2017/4/26.
 */
public enum DBType {

    BSON_OBJECT_ID("bson_objectId"),
    BSON_SUB_DOCUMENT("bson_sub_document");

    private String name;

    DBType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
