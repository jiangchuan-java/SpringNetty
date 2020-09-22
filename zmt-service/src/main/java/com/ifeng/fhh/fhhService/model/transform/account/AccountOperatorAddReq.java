package com.ifeng.fhh.fhhService.model.transform.account;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

import java.util.Date;

/**
 * @Auther: zhunn
 * @Date: 2020/9/7 11:18
 * @Description:
 */
public class AccountOperatorAddReq {

    //自媒体描述
    private String weMediaDescription;

    //自媒体类型
    private Integer weMediaType;

    //机构类型
    private Integer orgType;

    //自媒体号名称
    private String weMediaName;

    //自媒体号头像
    private String weMediaImg;

    //领域类
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String categoryId;

    // 运营分类
    private String operationCategory;

    // 管理者
    private String manager;

    //权威标签
    private String authorityTag;

    //账号权重
    private Integer accountWeight;

    //fhh版权  1：有版权   2：无版权
    private Integer fhhCopyright;

    //账号来源
    private Integer dataSource;

    //上下线
    private Integer online;

    //区域信息
    private String area;

    // 禁言开始时间
    private Date silenceBeginTime;

    // 禁言结束时间
    private Date silenceEndTime;

    // 禁言操作者
    private String silenceOperator;

    // 禁言操作时间
    private Date silenceOperateTime;

    //是否是高质量账号 0 否，1 是
    private Integer highQuality;
    //备注
    private String notes;

    //账号行为控制
    private String behavioralControl;

    // 荣誉id（关联honor表）
    private String honorId;

    // 荣誉描述
    private String honorDesc;

    // 账号别名
    private String accountAlias;

    //申请时间
    private Date applyTime;

    //审核时间
    private Date auditTime;

    public String getWeMediaDescription() {
        return weMediaDescription;
    }

    public void setWeMediaDescription(String weMediaDescription) {
        this.weMediaDescription = weMediaDescription;
    }

    public Integer getWeMediaType() {
        return weMediaType;
    }

    public void setWeMediaType(Integer weMediaType) {
        this.weMediaType = weMediaType;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getWeMediaName() {
        return weMediaName;
    }

    public void setWeMediaName(String weMediaName) {
        this.weMediaName = weMediaName;
    }

    public String getWeMediaImg() {
        return weMediaImg;
    }

    public void setWeMediaImg(String weMediaImg) {
        this.weMediaImg = weMediaImg;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getOperationCategory() {
        return operationCategory;
    }

    public void setOperationCategory(String operationCategory) {
        this.operationCategory = operationCategory;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAuthorityTag() {
        return authorityTag;
    }

    public void setAuthorityTag(String authorityTag) {
        this.authorityTag = authorityTag;
    }

    public Integer getAccountWeight() {
        return accountWeight;
    }

    public void setAccountWeight(Integer accountWeight) {
        this.accountWeight = accountWeight;
    }

    public Integer getFhhCopyright() {
        return fhhCopyright;
    }

    public void setFhhCopyright(Integer fhhCopyright) {
        this.fhhCopyright = fhhCopyright;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getSilenceBeginTime() {
        return silenceBeginTime;
    }

    public void setSilenceBeginTime(Date silenceBeginTime) {
        this.silenceBeginTime = silenceBeginTime;
    }

    public Date getSilenceEndTime() {
        return silenceEndTime;
    }

    public void setSilenceEndTime(Date silenceEndTime) {
        this.silenceEndTime = silenceEndTime;
    }

    public String getSilenceOperator() {
        return silenceOperator;
    }

    public void setSilenceOperator(String silenceOperator) {
        this.silenceOperator = silenceOperator;
    }

    public Date getSilenceOperateTime() {
        return silenceOperateTime;
    }

    public void setSilenceOperateTime(Date silenceOperateTime) {
        this.silenceOperateTime = silenceOperateTime;
    }

    public Integer getHighQuality() {
        return highQuality;
    }

    public void setHighQuality(Integer highQuality) {
        this.highQuality = highQuality;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBehavioralControl() {
        return behavioralControl;
    }

    public void setBehavioralControl(String behavioralControl) {
        this.behavioralControl = behavioralControl;
    }

    public String getHonorId() {
        return honorId;
    }

    public void setHonorId(String honorId) {
        this.honorId = honorId;
    }

    public String getHonorDesc() {
        return honorDesc;
    }

    public void setHonorDesc(String honorDesc) {
        this.honorDesc = honorDesc;
    }

    public String getAccountAlias() {
        return accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
