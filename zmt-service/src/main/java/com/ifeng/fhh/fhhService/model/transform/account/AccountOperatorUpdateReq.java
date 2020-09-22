package com.ifeng.fhh.fhhService.model.transform.account;

import java.util.Date;

/**
 * @Auther: zhunn
 * @Date: 2020/9/7 11:18
 * @Description:
 */
public class AccountOperatorUpdateReq {

    //自媒体id，更新时必传
    private Long eAccountId;

    //自媒体号名称
    private String weMediaName;

    //自媒体描述
    private String weMediaDescription;

    //自媒体类型
    private Integer weMediaType;

    //机构类型
    private Integer orgType;

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

    //区域信息
    private String area;

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

    //0 关闭，1开启，默认null开启
    private Integer commentStatus;

    // 账号别名
    private String accountAlias;

    // 更新人
    private String updateUserId;

    // 更新时间
    private Date updateTime;

    public Long geteAccountId() {
        return eAccountId;
    }

    public void seteAccountId(Long eAccountId) {
        this.eAccountId = eAccountId;
    }

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

    public String getWeMediaName() {
        return weMediaName;
    }

    public void setWeMediaName(String weMediaName) {
        this.weMediaName = weMediaName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getAccountAlias() {
        return accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
