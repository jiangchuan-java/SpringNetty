package com.ifeng.fhh.fhhService.model.domain;

import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DBType;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseIgnore;
import com.ifeng.fhh.fhhService.repository.database.mongodb.base.DatabaseType;
import com.ifeng.fhh.fhhService.tools.annotations.AllowNull;

import java.util.Date;
import java.util.List;

/**
 * 账号实体
 * <p>
 * Created by licheng1 on 2016/12/26.
 */
public class Account {

    //自媒体_id
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String _id;

    //主键id（自媒体id）
    private Long eAccountId;

    //凤凰通id
    private String fhtId;

    //外部id
    private String eId;

    //凤凰通名称
    private String fhtName;

    //自媒体号名称
    private String weMediaName;

    //自媒体号头像
    private String weMediaImg;

    //自媒体描述
    private String weMediaDescription;

    //领域类
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String categoryId;

    //分类名称
    private String categoryName;

    //账号类型
    private Integer accountType;

    //自媒体类型
    private Integer weMediaType;

    //申请时间
    private Date applyTime;

    //更新时间
    private Date updateTime;

    //状态
    private Integer status;

    //等级
    private Integer level;

    //账号质量
    private Integer accountQuality;

    //账号权重
    private Integer accountWeight;

    //是否是原文链接(保留)
    private Integer sourceLink;

    //订阅总数
    private Integer subscribeNum;

    //山下线
    private Integer online;

    //上线者姓名
    private String onlineUserName;

    //上线者id
    private String onlineUserId;

    //上线时间
    private Date onlineTime;

    //审核不通过原因
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String auditReason;

    //审核人
    private String auditUserName;

    //审核时间
    private Date auditTime;

    //审核人id
    private String auditUserId;

    //是否加锁（1为加锁，2为没加锁）
    private Integer isLock;

    //加锁时间
    private Date lockTime;

    //加锁人
    private String lockUserId;

    //是否有视频
    private Integer haveVideo;

    //上次插入文章时间
    private Date lastArticleTime;

    //上次插入视频时间
    private Date lastVideoTime;

    //是否是编辑待审核
    private Integer isEdit;

    //是否是编辑待审核 2非内部账号
    private Integer isInternal;

    /**
     * 账号版权标示 ,  一点文章版权信息  0:凤凰有版权 1:一点独家版权 2:一点抓取
     */
    private Integer copyright;

    /**
     * fhh版权  1：有版权   2：无版权
     */
    private Integer fhhCopyright;


    //************************************以下信息只有机构媒体和其他组织类型的多媒体拥有的信息******************************************************
    //运营者姓名
    private String operatorName;

    //运营者身份证号
    private String idCard;

    //身份证图片
    private String idCardImg;

    //运营者地址
    private String operatorAddress;

    //运营者邮箱
    private String operatorMail;

    //运营者电话
    private String operatorTelephone;

    //其他联系方式
    private String otherContacts;

    //辅助材料
    private String supportMaterials;

    //材料证明
    private String materialCertificateImg;

    //媒体机构代码证图片
    private String mediaCodePic;

    //合同授权书
    private String contractPic;

    //官方网址
    private String officialWebsite;

    //组织名称
    private String organizationName;

    //组织地址
    private String organizationAddress;

    /**
     * 是否是权威号
     */
    private Integer isAuthority;

    /**
     * 积分状态
     */
    private Integer integralStatus;

    /**
     * 分类实体
     */
    @DatabaseIgnore
    private Category category;

    //文章分发渠道
    private List<Integer> articleChannel;

    //视频分发渠道
    private List<Integer> videoChannel;

    /**
     * 是否是mcn
     * @return
     */
    private Integer isMCN;

    /**
     * mcn状态
     * @return
     */
    private Integer mcnStatus;

    /**
     * 安全等级
     */
    private Integer safeLevel;

    /**
     * 马甲号类型 1: 聚合账号 2:垂直账号
     */
    @AllowNull
    private Integer sockpuppetType;

    /**
     * 账号评级信息
     */
    private AccountRatings accountRatings;

    /**
     * 算法同步的信息
     */
    private String algorithmSyncInfo;

    /**
     * 是否是高质量账号 0 否，1 是
     */
    private Integer highQuality;

    private String updateUserId;

    private String updateUserName;

    /**
     * 账号来源：1 运营抓取； 2 频道运营；3 媒资上传；
     * 4 市场部签约稿源；5 自媒体自主入驻；6 自媒体固定收益；
     * 7 自媒体约稿收益；8 自媒体固定+约稿收益；9 MCN；
     * 10 自媒体免费邀约；11 CPM；12 一点资讯；13 UGC；
     * 14 CP拓展；15 采买MCN；16 合作MCN；17 做号MCN；
     */
    private Integer dataSource;

    /**
     * 账号认证信息
     */
    private String authInfo;

    private Integer accountRatingGroup;

    /**
     * 账号的内容类型：1：垂直型；2：聚合型；
     */
    private Integer accountContentType;

    /**
     *  0 关闭，1开启，默认null开启
     */
    private Integer commentStatus;

    /**
     * 账号行为控制
     */
    private String behavioralControl;

    /**
     * 账号得分
     */
    private Double score_double;

    /**
     * 机构类型
     */
    private Integer orgType;

    //wxb 账号运营分类
    private String accounCategory_wxb;

    //wxb 时政属性
    private Integer politics_wxb;

    //是否禁言:1-禁言，0-非禁言
    private Integer silence;

    private Integer accountInfoStatus;

    //权威标签,存入es用标识，1-有权威标签，0-无权威标签
    private String authorityTag;
    //注册所在地
    private String registerAddress;

    //区域信息
    private String area;

    // 荣誉id（关联honor表）
    @DatabaseType(type = DBType.BSON_OBJECT_ID)
    private String honorId;
    // 荣誉标识（logo）
    private String honorImg;
    // 荣誉描述
    private String honorDesc;
    // 禁言开始时间
    private Date silenceBeginTime;
    // 禁言结束时间
    private Date silenceEndTime;
    // 禁言操作者
    private String silenceOperator;
    // 禁言操作时间
    private Date silenceOperateTime;
    // 运营分类
    private String operationCategory;
    // 管理者
    private String manager;
    // 最后下线/禁言理由
    private String lastReason;
    // 账号别名
    private String accountAlias;
    // 备注信息
    private String notes;
    // 统一社会信用代码
    private String socialCreditCode;

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

    public String getFhtId() {
        return fhtId;
    }

    public void setFhtId(String fhtId) {
        this.fhtId = fhtId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getFhtName() {
        return fhtName;
    }

    public void setFhtName(String fhtName) {
        this.fhtName = fhtName;
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

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getWeMediaType() {
        return weMediaType;
    }

    public void setWeMediaType(Integer weMediaType) {
        this.weMediaType = weMediaType;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAccountQuality() {
        return accountQuality;
    }

    public void setAccountQuality(Integer accountQuality) {
        this.accountQuality = accountQuality;
    }

    public Integer getAccountWeight() {
        return accountWeight;
    }

    public void setAccountWeight(Integer accountWeight) {
        this.accountWeight = accountWeight;
    }

    public Integer getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(Integer sourceLink) {
        this.sourceLink = sourceLink;
    }

    public Integer getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(Integer subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getOnlineUserName() {
        return onlineUserName;
    }

    public void setOnlineUserName(String onlineUserName) {
        this.onlineUserName = onlineUserName;
    }

    public String getOnlineUserId() {
        return onlineUserId;
    }

    public void setOnlineUserId(String onlineUserId) {
        this.onlineUserId = onlineUserId;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
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

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public String getLockUserId() {
        return lockUserId;
    }

    public void setLockUserId(String lockUserId) {
        this.lockUserId = lockUserId;
    }

    public Integer getHaveVideo() {
        return haveVideo;
    }

    public void setHaveVideo(Integer haveVideo) {
        this.haveVideo = haveVideo;
    }

    public Date getLastArticleTime() {
        return lastArticleTime;
    }

    public void setLastArticleTime(Date lastArticleTime) {
        this.lastArticleTime = lastArticleTime;
    }

    public Date getLastVideoTime() {
        return lastVideoTime;
    }

    public void setLastVideoTime(Date lastVideoTime) {
        this.lastVideoTime = lastVideoTime;
    }

    public Integer getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }

    public Integer getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(Integer isInternal) {
        this.isInternal = isInternal;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getIsAuthority() {
        return isAuthority;
    }

    public void setIsAuthority(Integer isAuthority) {
        this.isAuthority = isAuthority;
    }

    public Integer getIntegralStatus() {
        return integralStatus;
    }

    public void setIntegralStatus(Integer integralStatus) {
        this.integralStatus = integralStatus;
    }

    public List<Integer> getArticleChannel() {
        return articleChannel;
    }

    public void setArticleChannel(List<Integer> articleChannel) {
        this.articleChannel = articleChannel;
    }

    public List<Integer> getVideoChannel() {
        return videoChannel;
    }

    public void setVideoChannel(List<Integer> videoChannel) {
        this.videoChannel = videoChannel;
    }

    public Integer getCopyright() {
        return copyright;
    }

    public void setCopyright(Integer copyright) {
        this.copyright = copyright;
    }

    public Integer getFhhCopyright() {
        return fhhCopyright;
    }

    public void setFhhCopyright(Integer fhhCopyright) {
        this.fhhCopyright = fhhCopyright;
    }

    public Integer getIsMCN() {
        return isMCN;
    }

    public void setIsMCN(Integer isMCN) {
        this.isMCN = isMCN;
    }

    public Integer getMcnStatus() {
        return mcnStatus;
    }

    public void setMcnStatus(Integer mcnStatus) {
        this.mcnStatus = mcnStatus;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getSafeLevel() {
        return safeLevel;
    }

    public void setSafeLevel(Integer safeLevel) {
        this.safeLevel = safeLevel;
    }

    public Integer getSockpuppetType() {
        return sockpuppetType;
    }

    public void setSockpuppetType(Integer sockpuppetType) {
        this.sockpuppetType = sockpuppetType;
    }

    public AccountRatings getAccountRatings() {
        return accountRatings;
    }

    public void setAccountRatings(AccountRatings accountRatings) {
        this.accountRatings = accountRatings;
    }

    public String getAlgorithmSyncInfo() {
        return algorithmSyncInfo;
    }

    public void setAlgorithmSyncInfo(String algorithmSyncInfo) {
        this.algorithmSyncInfo = algorithmSyncInfo;
    }

    public Integer getHighQuality() {
        return highQuality;
    }

    public void setHighQuality(Integer highQuality) {
        this.highQuality = highQuality;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    public Integer getAccountContentType() {
        return accountContentType;
    }

    public void setAccountContentType(Integer accountContentType) {
        this.accountContentType = accountContentType;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }
    public Integer getAccountRatingGroup() {
        return accountRatingGroup;
    }

    public void setAccountRatingGroup(Integer accountRatingGroup) {
        this.accountRatingGroup = accountRatingGroup;
    }

    public String getBehavioralControl() {
        return behavioralControl;
    }

    public void setBehavioralControl(String behavioralControl) {
        this.behavioralControl = behavioralControl;
    }

    public Double getScore_double() {
        return score_double;
    }

    public void setScore_double(Double score_double) {
        this.score_double = score_double;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getAccounCategory_wxb() {
        return accounCategory_wxb;
    }

    public void setAccounCategory_wxb(String accounCategory_wxb) {
        this.accounCategory_wxb = accounCategory_wxb;
    }

    public Integer getPolitics_wxb() {
        return politics_wxb;
    }

    public void setPolitics_wxb(Integer politics_wxb) {
        this.politics_wxb = politics_wxb;
    }

    public Integer getSilence() {
        return silence;
    }

    public void setSilence(Integer silence) {
        this.silence = silence;
    }

    public Integer getAccountInfoStatus() {
        return accountInfoStatus;
    }

    public void setAccountInfoStatus(Integer accountInfoStatus) {
        this.accountInfoStatus = accountInfoStatus;
    }

    public String getAuthorityTag() {
        return authorityTag;
    }

    public void setAuthorityTag(String authorityTag) {
        this.authorityTag = authorityTag;
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

    public String getLastReason() {
        return lastReason;
    }

    public void setLastReason(String lastReason) {
        this.lastReason = lastReason;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHonorImg() {
        return honorImg;
    }

    public void setHonorImg(String honorImg) {
        this.honorImg = honorImg;
    }

    public String getAccountAlias() {
        return accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public Date getSilenceOperateTime() {
        return silenceOperateTime;
    }

    public void setSilenceOperateTime(Date silenceOperateTime) {
        this.silenceOperateTime = silenceOperateTime;
    }
}
