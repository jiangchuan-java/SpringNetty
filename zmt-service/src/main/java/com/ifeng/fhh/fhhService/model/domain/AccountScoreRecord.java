package com.ifeng.fhh.fhhService.model.domain;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

import java.util.Date;

/**
 * @Auther: zhunn
 * @Date: 2019/12/3 15:46
 * @Description: 账号分值操作记录表实体
 */
public class AccountScoreRecord {

    /**
     * 主键id
     */
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String _id;

    /**
     * 账号id
     */
    private Long eAccountId;

    /**
     * 扣分值
     */
    private Double negativeScore;

    /**
     * 扣分前
     */
    private Double beforeExecuteScore;

    /**
     * 扣分后
     */
    private Double afterExecuteScore;

    /**
     * 描述
     */
    private String desc;

    /**
     * 录入时间
     */
    private Date operateTime;

    /**
     * 录入人
     */
    private String operatorName;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long geteAccountId() {
        return eAccountId;
    }

    public void seteAccountId(Long eAccountId) {
        this.eAccountId = eAccountId;
    }

    public Double getNegativeScore() {
        return negativeScore;
    }

    public void setNegativeScore(Double negativeScore) {
        this.negativeScore = negativeScore;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Double getBeforeExecuteScore() {
        return beforeExecuteScore;
    }

    public void setBeforeExecuteScore(Double beforeExecuteScore) {
        this.beforeExecuteScore = beforeExecuteScore;
    }

    public Double getAfterExecuteScore() {
        return afterExecuteScore;
    }

    public void setAfterExecuteScore(Double afterExecuteScore) {
        this.afterExecuteScore = afterExecuteScore;
    }
}
