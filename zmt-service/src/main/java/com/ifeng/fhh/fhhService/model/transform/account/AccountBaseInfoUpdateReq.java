package com.ifeng.fhh.fhhService.model.transform.account;


import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;

import java.util.Date;

/**
 * @Auther: zhunn
 * @Date: 2020/9/7 11:18
 * @Description:
 */
public class AccountBaseInfoUpdateReq {

    //自媒体id，更新时必传
    private Long eAccountId;

    //自媒体号名称
    private String weMediaName;

    //自媒体号头像
    private String weMediaImg;

    //自媒体描述
    private String weMediaDescription;

    //领域类
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String categoryId;

    //自媒体类型
    private Integer weMediaType;

    //区域信息
    private String area;

    //备注
    private String notes;

    //运营者姓名
    private String operatorName;

    //运营者身份证号
    private String idCard;

    //身份证图片
    private String idCardImg;

    //运营者地址
    private String operatorAddress;

    //注册所在地
    private String registerAddress;

    //运营者邮箱
    private String operatorMail;

    //运营者电话
    private String operatorTelephone;

    //辅助材料
    private String supportMaterials;

    //材料证明
    private String materialCertificateImg;

    //机构/组织名称
    private String organizationName;

    //机构/组织地址
    private String organizationAddress;

    //媒体机构代码证图片
    private String mediaCodePic;

    //合同授权书
    private String contractPic;

    //官方网址
    private String officialWebsite;

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

    public String getWeMediaDescription() {
        return weMediaDescription;
    }

    public void setWeMediaDescription(String weMediaDescription) {
        this.weMediaDescription = weMediaDescription;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getWeMediaType() {
        return weMediaType;
    }

    public void setWeMediaType(Integer weMediaType) {
        this.weMediaType = weMediaType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
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

    public String getSupportMaterials() {
        return supportMaterials;
    }

    public void setSupportMaterials(String supportMaterials) {
        this.supportMaterials = supportMaterials;
    }

    public String getMaterialCertificateImg() {
        return materialCertificateImg;
    }

    public void setMaterialCertificateImg(String materialCertificateImg) {
        this.materialCertificateImg = materialCertificateImg;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getMediaCodePic() {
        return mediaCodePic;
    }

    public void setMediaCodePic(String mediaCodePic) {
        this.mediaCodePic = mediaCodePic;
    }

    public String getContractPic() {
        return contractPic;
    }

    public void setContractPic(String contractPic) {
        this.contractPic = contractPic;
    }

    public String getOfficialWebsite() {
        return officialWebsite;
    }

    public void setOfficialWebsite(String officialWebsite) {
        this.officialWebsite = officialWebsite;
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
