package com.ifeng.fhh.fhhService.model.transform.account;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

import java.util.Date;

/**
 * @Auther: zhunn
 * @Date: 2020/9/7 11:18
 * @Description:
 */
public class AccountAuditUpdateReq {

    //自媒体id，更新时必传
    private Long eAccountId;

    //审核不通过原因
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String auditReason;

    //审核人
    private String auditUserName;

    //审核人Id
    private String auditUserId;

    //审核时间
    private Date auditTime;

    // 账号状态（1：待审核 ，2：审核通过，,3：审核未通过,4:永久审核不通过）
    private Integer status;

    //更新账号信息时间
    private Date updateAccountTime;

    //账号修改信息-审核状态：1-审核中，2-审核通过，3-审核不通过
    private Integer accountInfoStatus;

    //账号修改信息-审核原因ID
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String accountInfoAuditReason;

    //(是否是编辑再审核） 1：是 0：不是(默认）
    private Integer isEdit;


    public Long geteAccountId() {
        return eAccountId;
    }

    public void seteAccountId(Long eAccountId) {
        this.eAccountId = eAccountId;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateAccountTime() {
        return updateAccountTime;
    }

    public void setUpdateAccountTime(Date updateAccountTime) {
        this.updateAccountTime = updateAccountTime;
    }

    public Integer getAccountInfoStatus() {
        return accountInfoStatus;
    }

    public void setAccountInfoStatus(Integer accountInfoStatus) {
        this.accountInfoStatus = accountInfoStatus;
    }

    public String getAccountInfoAuditReason() {
        return accountInfoAuditReason;
    }

    public void setAccountInfoAuditReason(String accountInfoAuditReason) {
        this.accountInfoAuditReason = accountInfoAuditReason;
    }

    public Integer getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }
}
