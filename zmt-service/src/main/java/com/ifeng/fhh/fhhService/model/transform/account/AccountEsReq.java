package com.ifeng.fhh.fhhService.model.transform.account;

import java.util.List;

/**
 * @Auther: zhunn
 * @Date: 2020/8/31 15:04
 * @Description: 查询es中账号信息，查询条件
 */
public class AccountEsReq {

    private int pageSize = 30;
    private int pageNum = 0;
    private String sortField = "applyTime";
    private String sortRule = "desc";

    //自媒体主键id
    private String _id;

    //自媒体id
    private Long eAccountId;

    //凤凰通id
    private String fhtId;

    //凤凰通名称
    private String fhtName;

    //自媒体号名称(模糊匹配查询)
    private String weMediaName;

    //领域类
    private String categoryId;

    //账号类型
    private List<Integer> accountType;

    //自媒体类型
    private List<Integer> weMediaType;

    //机构类型
    private List<Integer> orgType;

    //上下线
    private Integer online;

    //状态
    private List<Integer> status;

    //等级
    private List<Integer> level;

    //账号来源
    private List<Integer> dataSource;

    //是否有视频
    private Integer haveVideo;

    //fhh版权  1：有版权   2：无版权
    private Integer fhhCopyright;

    //是否是高质量账号 0 否，1 是
    private Integer highQuality;

    //账号的内容类型：1：垂直型；2：聚合型；
    private Integer accountContentType;

    //运营者姓名
    private String operatorName;

    //身份证
    private String idCard;

    //运营者邮箱
    private String operatorMail;

    //运营者电话
    private String operatorTelephone;

    //账号修改信息-审核状态：1-审核中，2-审核通过，3-审核不通过
    private Integer accountInfoStatus;

    //账号运营分类-wxb属性（15个运用分类）
    private String accounCategory_wxb;

    //0：非时政 ，1：时政
    private Integer politics_wxb;

    //上线时间
    private String applyTimeStart;

    //上线时间
    private String applyTimeEnd;

    //账号行为控制(json字符串，模糊匹配查询)
    private String behavioralControl;

    //权威标签
    private String authorityTag;

    // 荣誉id（关联honor表）
    private String honorId;

    // 荣誉描述
    private String honorDesc;

    //是否禁言:1-禁言，0-非禁言
    private Integer silence;

    //是否有fhtId:1-有，0-无
    private Integer haveFhtId;

    // 禁言操作者
    private String silenceOperator;

    // 运营分类
    private String operationCategory;

    // 管理者
    private String manager;

    // 最后下线/禁言理由
    private String lastReason;

    // 账号别名
    private String accountAlias;

    // 统一社会信用代码
    private String socialCreditCode;

    // 账号得分起始值
    private Double scoreDoubleStart;
    // 账号得分结束值
    private Double scoreDoubleEnd;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getAccountType() {
        return accountType;
    }

    public void setAccountType(List<Integer> accountType) {
        this.accountType = accountType;
    }

    public List<Integer> getWeMediaType() {
        return weMediaType;
    }

    public void setWeMediaType(List<Integer> weMediaType) {
        this.weMediaType = weMediaType;
    }

    public List<Integer> getOrgType() {
        return orgType;
    }

    public void setOrgType(List<Integer> orgType) {
        this.orgType = orgType;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    public List<Integer> getLevel() {
        return level;
    }

    public void setLevel(List<Integer> level) {
        this.level = level;
    }

    public List<Integer> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<Integer> dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getHaveVideo() {
        return haveVideo;
    }

    public void setHaveVideo(Integer haveVideo) {
        this.haveVideo = haveVideo;
    }

    public Integer getFhhCopyright() {
        return fhhCopyright;
    }

    public void setFhhCopyright(Integer fhhCopyright) {
        this.fhhCopyright = fhhCopyright;
    }

    public Integer getHighQuality() {
        return highQuality;
    }

    public void setHighQuality(Integer highQuality) {
        this.highQuality = highQuality;
    }

    public Integer getAccountContentType() {
        return accountContentType;
    }

    public void setAccountContentType(Integer accountContentType) {
        this.accountContentType = accountContentType;
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

    public Integer getAccountInfoStatus() {
        return accountInfoStatus;
    }

    public void setAccountInfoStatus(Integer accountInfoStatus) {
        this.accountInfoStatus = accountInfoStatus;
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

    public String getApplyTimeStart() {
        return applyTimeStart;
    }

    public void setApplyTimeStart(String applyTimeStart) {
        this.applyTimeStart = applyTimeStart;
    }

    public String getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(String applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }

    public String getBehavioralControl() {
        return behavioralControl;
    }

    public void setBehavioralControl(String behavioralControl) {
        this.behavioralControl = behavioralControl;
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

    public Integer getSilence() {
        return silence;
    }

    public void setSilence(Integer silence) {
        this.silence = silence;
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

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortRule() {
        return sortRule;
    }

    public void setSortRule(String sortRule) {
        this.sortRule = sortRule;
    }

    public Integer getHaveFhtId() {
        return haveFhtId;
    }

    public void setHaveFhtId(Integer haveFhtId) {
        this.haveFhtId = haveFhtId;
    }

    public String getAccountAlias() {
        return accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public Double getScoreDoubleStart() {
        return scoreDoubleStart;
    }

    public void setScoreDoubleStart(Double scoreDoubleStart) {
        this.scoreDoubleStart = scoreDoubleStart;
    }

    public Double getScoreDoubleEnd() {
        return scoreDoubleEnd;
    }

    public void setScoreDoubleEnd(Double scoreDoubleEnd) {
        this.scoreDoubleEnd = scoreDoubleEnd;
    }
}
