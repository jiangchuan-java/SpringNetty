package com.ifeng.fhh.fhhService.model.domain;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseProperty;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

import java.util.Date;

/**
 * 操作日志
 * <p>
 * Created by penghb on 2017/4/21.
 */
public class OperationLog {

    /**
     * 主键
     */
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    @DatabaseProperty(name = "_id")
    private String id;

    /**
     * 目标Id
     */
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String targetId;

    /**
     * 表名
     */
    private String collectionName;

    /**
     * 用户Id
     */
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * ip
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 操作状态
     */
    private Integer operation;

    /**
     * 操作描述
     */
    private String operationDetail;

    /**
     * 日志类型
     */
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(String operationDetail) {
        this.operationDetail = operationDetail;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
