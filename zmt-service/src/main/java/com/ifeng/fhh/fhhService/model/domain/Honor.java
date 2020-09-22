package com.ifeng.fhh.fhhService.model.domain;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

/**
 * @Auther: zhunn
 * @Date: 2018/10/11 14:58
 * @Description: 荣誉表实体
 */
public class Honor {

    // 荣誉信息主键id
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String _id;

    // 荣誉名称
    private String name;

    // 荣誉图标
    private String img;

    //荣誉描述
    private String desc;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
