package com.ifeng.fhh.fhhService.model.domain;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

import java.util.Date;

/**
 * Created by chenwj3 on 2016/11/15.
 */
public class AccountEdit {

    //主键Id
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String _id;

    //账号类型
    private Integer weMediaType;

    // 账号自媒体id
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String weMediaId;

    //自媒体名称
    private String weMediaName;

    /**
     * 分类id
     */
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String categoryId;

    //修改的头像
    private String weMediaImg;

    //自媒体描述
    private String weMediaDescription;

    //运营者姓名
    private String operatorName;

    //身份证号
    private String idCard;

    //身份证图片
    private String idCardImg;

    //运营者地址
    private String operatorAddress;

    //运营者邮箱
    private String operatorMail;

    //运营者手机
    private String operatorTelephone;

    //其他联系方式
    private String otherContacts;

    //修改时间
    private Date updateTime;

    //状态
    private Integer status;

    //账号类型
    private Integer accountType;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getWeMediaType() {
        return weMediaType;
    }

    public void setWeMediaType(Integer weMediaType) {
        this.weMediaType = weMediaType;
    }

    public String getWeMediaId() {
        return weMediaId;
    }

    public void setWeMediaId(String weMediaId) {
        this.weMediaId = weMediaId;
    }

    public String getWeMediaName() {
        return weMediaName;
    }

    public void setWeMediaName(String weMediaName) {
        this.weMediaName = weMediaName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getWeMediaImg() {
        return weMediaImg;
    }

    public void setWeMediaImg(String weMediaImg) {
        this.weMediaImg = weMediaImg;
    }

    public String getWeMediaDescription() {
        return weMediaDescription;
    }

    public void setWeMediaDescription(String weMediaDescription) {
        this.weMediaDescription = weMediaDescription;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardImg() {
        return idCardImg;
    }

    public void setIdCardImg(String idCardImg) {
        this.idCardImg = idCardImg;
    }

    public String getOperatorAddress() {
        return operatorAddress;
    }

    public void setOperatorAddress(String operatorAddress) {
        this.operatorAddress = operatorAddress;
    }

    public String getOperatorMail() {
        return operatorMail;
    }

    public void setOperatorMail(String operatorMail) {
        this.operatorMail = operatorMail;
    }

    public String getOperatorTelephone() {
        return operatorTelephone;
    }

    public void setOperatorTelephone(String operatorTelephone) {
        this.operatorTelephone = operatorTelephone;
    }

    public String getOtherContacts() {
        return otherContacts;
    }

    public void setOtherContacts(String otherContacts) {
        this.otherContacts = otherContacts;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}
