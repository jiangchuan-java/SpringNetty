package com.ifeng.fhh.fhhService.model.domain;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseProperty;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

import java.util.Date;

/**
 * 操作数据日志
 * <p>
 * Created by penghb on 2017/4/21.
 */
public class OperationDataLog {

    /**
     * 主键
     */
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    @DatabaseProperty(name = "_id")
    private String id;

    /**
     * 日志Id
     */
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String logId;

    /**
     * 旧值
     */
    private String oldValue;

    /**
     * 新值
     */
    private String newValue;

    /**
     * 过期时间
     */
    private Date expireTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
