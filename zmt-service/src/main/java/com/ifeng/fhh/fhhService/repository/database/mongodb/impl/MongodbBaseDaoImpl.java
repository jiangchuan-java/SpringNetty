package com.ifeng.fhh.fhhService.repository.database.mongodb.impl;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.MongoTemplate;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.MongodbBaseDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * mongodb 基础数据访问接口实现类
 * <p>
 * Created by licheng1 on 2016/12/23.
 */
public abstract class MongodbBaseDaoImpl implements MongodbBaseDao {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public void setMongoTemplate(MongoTemplate template) {
        this.mongoTemplate = template;
    }

    @Override
    public MongoTemplate getMongoTemplate() {
        return this.mongoTemplate;
    }
}
