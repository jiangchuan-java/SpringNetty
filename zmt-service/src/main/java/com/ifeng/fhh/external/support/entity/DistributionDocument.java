package com.ifeng.fhh.external.support.entity;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

import java.util.Date;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-31
 */
public class DistributionDocument {

    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String _id;

    //内容id
    private String documentId;

    //内容类型
    private String type;

    //状态
    private Integer status;

    //渠道
    private String channel;

    //分发日期
    private Date distributeDate;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getDistributeDate() {
        return distributeDate;
    }

    public void setDistributeDate(Date distributeDate) {
        this.distributeDate = distributeDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}

