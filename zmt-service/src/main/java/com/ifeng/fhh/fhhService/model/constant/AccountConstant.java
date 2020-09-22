package com.ifeng.fhh.fhhService.model.constant;

/**
 * 自媒体账号常量
 * Created by chenwj3 on 2017/6/27.
 */
public class AccountConstant {

    /**
     * 自媒体类型 公共
     */
    public static final int ACCOUNTTYPE_PUBLIC = 1;

    /**
     * 自媒体类型 邀约
     */
    public static final int ACCOUNTTYPE_INVITATION = 2;

    /**
     * 自媒体类型 视频账号
     */
    public static final int ACCOUNTTYPE_VIDEO = 3;

    /**
     * 自媒体类型 一点账号
     */
    public static final int ACCOUNTTYPE_YIDIAN = 4;

    /**
     * 账号状态 待审核
     */
    public static final int STATUS_NO_EXAMINE = 1;

    /**
     * 账号状态 审核通过
     */
    public static final int STATUS_EXAMINE_SUCCESS = 2;

    /**
     * 账号状态 审核未通过
     */
    public static final int STATUS_EXAMINE_FAIL = 3;

    /**
     * 个人类型
     */
    public static final int WEMEDIATYPE_PERSON = 1;

    /**
     * 机构媒体类型
     */
    public static final int WEMEDIATYPE_MEDIA = 2;

    /**
     * 其他组织类型
     */
    public static final int WEMEDIATYPE_OTHER = 3;

    /**
     * 账号来源 普通
     */
    public static final int ACCOUNTQUALITY_NORMAL = 1;

    /**
     * 账号来源 白名单账号
     */
    public static final int ACCOUNTQUALITY_WHITELIST = 2;

    /**
     * 账号来源 超级白名单账号
     */
    public static final int ACCOUNTQUALITY_SUPER_WHITELIST = 3;

    /**
     * 账号上下线 默认状态0
     */
    public static final int ONLINE_DEFAULT = 0;

    /**
     * 账号上下线 下线
     */
    public static final int ONLINE_OFFLINE = 1;

    /**
     * 账号上下线 上线
     */
    public static final int ONLINE_ONLINE = 2;

    /**
     * 是否含有视频 没有
     */
    public static final int HAVEVIDEO_NO = 1;

    /**
     * 是否含有视频 有
     */
    public static final int HAVEVIDEO_YES = 2;

    /**
     * 编辑待审核
     */
    public static final int ISEDIT_NO = 0;

    /**
     * 不是编辑待审核
     */
    public static final int ISEDIT_YES = 1;

    /**
     * 是否是内部账号 是
     */
    public static final int ISINTERNAL_YES = 1;

    /**
     * 是否是内部账号 不是
     */
    public static final int ISINTERNAL_NO = 2;


    /**
     * 积分状态：已经加过分
     */
    public static final int INTERGRAL_STATUS_ADD = 1;

    /**
     * 积分状态：已经减过分
     */
    public static final int INTERGRAL_STATUS_MINUS = 0;


    /**
     * 凤凰有版权
     */
    public static final int COYPRIGHT_NORMAL = 0;

    /**
     * 一点独家版权
     */
    public static final int COPYRIGHT_YD = 1;

    /**
     * 一点授权的版权
     */
    public static final int COPYRIGHT_YD_FHH_CAN_USED = 0;


    /**
     * 创建源 爬虫根据评论爬取一点的信息
     */
    public static final int CREATESOURCE_SPIDER_YIDIAN = 5;



    /**
     * fhh 版权：有
     */
    public static final Integer  HAVE_FHHCOPYRIGHT_YES = 1;

    /**
     * fhh 版权：无
     */
    public static final Integer  HAVE_FHHCOPYRIGHT_NO = 2;

    /**
     * isMCN: 是否是MCN账号,   0 否  1 是
     */
    public static final Integer  IS_MCN_NO = 0;

    /**
     * mcnStatus: MCN账号状态  ( 1：MCN待审核 ，2：MCN审核通过，3：MCN审核未通过 )
     */
    public static final Integer  MCN_STATUS_NO_PASS = 3;

    /**
     * 查询账号基础信息查询id类型， eAccountId
     */
    public static final String ACCOUNT_QUERY_TYPE_EACCOUNTID = "1";

    /**
     * 查询账号基础信息查询id类型， eId
     */
    public static final String ACCOUNT_QUERY_TYPE_EID = "2";

    /**
     * 查询账号基础信息查询id类型， fhtId
     */
    public static final String ACCOUNT_QUERY_TYPE_FHTID = "3";

    /**
     * 判断header里token值是否满足可返回身份证号
     */
    public static final String ACCOUNT_CAN_RETURN_IDCARD_TOKEN = "idCard_token";

    /**
     * 判断接口返回范围，下线账号是否可返回
     */
    public static final String ACCOUNT_RETURN_RANGE_ALL = "_all";

    /**
     * 体验账号
     */
    public static final Integer ACCOUNT_TYPE_EXCEPTION=6;

    /**
     * 马甲号-聚合账号
     */
    public static final int SOCK_PUPPET_TYPE_AGGREGATION = 1;

    /**
     * 马甲号-垂直账号
     */
    public static final int SOCK_PUPPET_TYPE_VERTICAL = 2;

    /**
     * 账号上下线-请求来源：0-一点
     */
    public static final Integer ACCOUNT_STATUS_UPDATE_YD = 0;

    /**
     * 账号上下线-请求来源：1-自媒体
     */
    public static final Integer ACCOUNT_STATUS_UPDATE_FHH = 1;

    /**
     * 账号上下线-请求处理状态：0-未处理
     */
    public static final Integer ACCOUNT_STATUS_NOT_UPDATE = 0;

    /**
     * 账号上下线-请求处理状态：1-处理中
     */
    public static final Integer ACCOUNT_STATUS_UPDATING = 1;

    /**
     * 账号上下线-请求处理状态：2-已处理
     */
    public static final Integer ACCOUNT_STATUS_UPDATED = 2;

    /**
     * 账号上下线-操作类型：0-下线处理
     */
    public static final int ACCOUNT_SYSTEM_OFFLINE = 0;

    /**
     * 账号上下线-操作类型：1-上线处理
     */
    public static final int ACCOUNT_SYSTEM_ONLINE = 1;

    /**
     * 是否高质量账号：是
     */
    public static final int ACCOUNT_HIGHQUALITY_YES = 1;

    /**
     * 是否高质量账号：否
     */
    public static final int ACCOUNT_HIGHQUALITY_NO = 0;

    /**
     * 创建高质量账号增加抓取标识
     */
    public static final String SPIDER_ACCOUNT_SIGN = "TB";

    /**
     * 创建高质量账号-抓取类型：微信
     */
    public static final String WEIXIN = "wx";

    /**
     * 创建高质量账号-抓取类型：一点
     */
    public static final String YIDIAN = "yidian";

    /**
     * 创建高质量账号-抓取类型：B站
     */
    public static final String BILIBILI = "bilibili";

    /**
     * 创建高质量账号-抓取类型：今日头条
     */
    public static final String TOUTIAO = "toutiao";

    /**
     * 创建高质量账号-抓取类型：快手
     */
    public static final String KUAISHOU = "kuaishou";

    /**
     * 创建高质量账号-抓取类型：抖音
     */
    public static final String DOUYIN = "douyin";

    /**
     * 创建高质量账号-抓取类型：好看视频
     */
    public static final String HAOKAN = "haokan";

    /**
     * 创建高质量账号-抓取范围：all
     */
    public static final String SPIDER_RANGE_ALL = "all";

    /**
     * 创建高质量账号-抓取范围：article
     */
    public static final String SPIDER_RANGE_ARTICLE = "article";

    /**
     * 创建高质量账号-抓取范围：video
     */
    public static final String SPIDER_RANGE_VIDEO = "video";

    /**
     * 账号抓取源链接状态：4-删除
     */
    public static final int SPIDER_RESOURCE_STATUS_DELETE = 4;

    /**
     *  账号内容类型：垂直型 1
     */
    public static final int ACCOUNT_CONTENT_TYPE_VERTICAL = 1;

    /**
     *  账号内容类型：聚合型 2
     */
    public static final int ACCOUNT_CONTENT_TYPE_AGGREGATION = 2;

    /**
     *  账号开启新内容的评论 0
     */
    public static final int COMMENT_OPEN = 0;

    /**
     *  账号关闭新聂荣的评论 1
     */
    public static final int COMMENT_CLOSE = 1;

    /**
     * 非时政
     */
    public static final int POLITICS_WXB_NO = 0;

    /**
     * 时政
     */
    public static final int POLITICS_WXB_YES = 1;

}
