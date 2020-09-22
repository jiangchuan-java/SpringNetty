package com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.impl;

import com.ifeng.fhh.fhhService.model.constant.AccountConstant;
import com.ifeng.fhh.fhhService.model.domain.Account;
import com.ifeng.fhh.fhhService.model.transform.PagedAccount;
import com.ifeng.fhh.fhhService.model.transform.account.AccountEsReq;
import com.ifeng.fhh.fhhService.repository.searchEngine.AccountSEDao;
import com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.base.ElasticSearchBaseDaoImpl;
import com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.base.QueryOptions;
import com.ifeng.fhh.zmt.tools.DateUtils;
import com.ifeng.fhh.zmt.tools.EmptyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.search.sort.SortBuilders.fieldSort;

/**
 * 账号elasticsearch数据访问接口实现
 * <p>
 * Created by licheng1 on 2017/5/3.
 */
@Repository
public class AccountSEDaoImpl extends ElasticSearchBaseDaoImpl implements AccountSEDao {

    @Value("${searchEngine.elasticsearch.account.index}")
    private String index;

    @Value("account")
    private String type;

    @Value("${searchEngine.elasticsearch.account.indexV2}")
    private String indexV2;

    @Value("account_v2")
    private String typeV2;

    @Override
    public CompletableFuture<Void> add(Account account) {
        CompletableFuture<Void> addFuture = new CompletableFuture();

        try {
            Objects.requireNonNull(account);
            String id = Objects.requireNonNull(account.get_id());
            Map<String, Object> source = serialize(account);
            elasticSearchTemplate.index(this.index, this.type, id, source).whenComplete((response, throwable) -> {
                if (throwable != null) {
                    addFuture.completeExceptionally(throwable);
                } else {
                    RestStatus status = response.status();
                    if (status.equals(RestStatus.OK) ||
                            status.equals(RestStatus.CREATED)) {
                        addFuture.complete(null);
                    } else {
                        addFuture.completeExceptionally(new Throwable("index account fail, id : "
                                + id
                                + ", response : "
                                + status));
                    }
                }

            });
        } catch (Exception e) {
            addFuture.completeExceptionally(e);
        }

        return addFuture;
    }

    @Override
    public CompletableFuture<Void> addV2(Account account) {
        CompletableFuture<Void> addFuture = new CompletableFuture();

        try {
            Objects.requireNonNull(account);
            String id = Objects.requireNonNull(account.get_id());
            Map<String, Object> source = serialize(account);
            elasticSearchTemplate.index(this.indexV2, this.typeV2, id, source).whenComplete((response, throwable) -> {
                if (throwable != null) {
                    addFuture.completeExceptionally(throwable);
                } else {
                    RestStatus status = response.status();
                    if (status.equals(RestStatus.OK) ||
                            status.equals(RestStatus.CREATED)) {
                        addFuture.complete(null);
                    } else {
                        addFuture.completeExceptionally(new Throwable("addV2 index account fail, id : "
                                + id
                                + ", response : "
                                + status));
                    }
                }

            });
        } catch (Exception e) {
            addFuture.completeExceptionally(e);
        }

        return addFuture;
    }

    /**
     * 使用QueryBuilder
     * termQuery("key", obj) 完全匹配
     * termsQuery("key", obj1, obj2..)   一次匹配多个值
     * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
     * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
     * matchAllQuery();         匹配所有文件
     */
    @Override
    public CompletableFuture<PagedAccount> findAllSortedByWeight(int from, int size) {
        CompletableFuture<PagedAccount> findFuture = new CompletableFuture();
        if (from < 0) {
            findFuture.completeExceptionally(new Throwable("find from must not be negative"));
            return findFuture;
        }
        if (size < 1) {
            findFuture.completeExceptionally(new Throwable("find size must be positive"));
            return findFuture;
        }
        String[] indices = new String[]{this.index};
        String[] types = new String[]{this.type};
        QueryBuilder queryBuilder = boolQuery()
                .must(
                        termQuery("online", AccountConstant.ONLINE_ONLINE)
                );
        QueryOptions options = new QueryOptions()
                .setFrom(from)
                .setSize(size)
                .addSort(
                        fieldSort("accountWeight")
                                .order(SortOrder.DESC)
                );
        elasticSearchTemplate.searchWithOptions(indices, types, queryBuilder, options).whenComplete((response, throwable) -> {
            if (throwable != null) {
                findFuture.completeExceptionally(throwable);
            } else {
                RestStatus status = response.status();
                PagedAccount pagedAccount;
                if (status.equals(RestStatus.OK)) {
                    pagedAccount = new PagedAccount();
                    try {
                        SearchHits hits = response.getHits();
                        SearchHit[] searchHits = hits.getHits();
                        int total = ((Long) hits.totalHits()).intValue();
                        pagedAccount.setTotal(total);
                        List<Account> accountList = null;
                        if (searchHits.length > 0) {
                            accountList = new ArrayList<>();
                            for (SearchHit hit : searchHits) {
                                String id = hit.getId();
                                Account account = deserialize(hit.getSource(), Account.class);
                                account.set_id(id);
                                accountList.add(account);
                            }
                        }
                        pagedAccount.setAccountList(accountList);
                        findFuture.complete(pagedAccount);
                    } catch (Exception e) {
                        findFuture.completeExceptionally(e);
                    }
                } else {
                    findFuture.completeExceptionally(new Throwable("search paged account sort by weight fail, response : "
                            + status));
                }
            }
        });
        return findFuture;
    }

    @Override
    public CompletableFuture<PagedAccount> findVideoAccountByWeMediaName(String weMediaName, int from, int size) {
        CompletableFuture<PagedAccount> findFuture = new CompletableFuture();
        if (EmptyUtils.isEmpty(weMediaName)) {
            findFuture.completeExceptionally(new Throwable("find weMediaName must not be null"));
            return findFuture;
        }
        if (from < 0) {
            findFuture.completeExceptionally(new Throwable("find from must not be negative"));
            return findFuture;
        }
        if (size < 1) {
            findFuture.completeExceptionally(new Throwable("find size must be positive"));
            return findFuture;
        }
        String[] indices = new String[]{this.index};
        String[] types = new String[]{this.type};
        QueryBuilder queryBuilder = boolQuery()
                //.must(QueryBuilders.matchAllQuery())
                .must(QueryBuilders.matchPhraseQuery("weMediaName", weMediaName))
                //.must(QueryBuilders.fuzzyQuery("weMediaName",weMediaName))
                .must(QueryBuilders.termQuery("haveVideo", AccountConstant.HAVEVIDEO_YES))
                .must(QueryBuilders.termQuery("online", AccountConstant.ONLINE_ONLINE));
        QueryOptions options = new QueryOptions()
                .setFrom(from)
                .setSize(size);
        elasticSearchTemplate.searchWithOptions(indices, types, queryBuilder, options).whenComplete((searchResponse, throwable) -> {
            if (throwable != null) {
                findFuture.completeExceptionally(throwable);
            } else {
                RestStatus status = searchResponse.status();
                PagedAccount pagedAccount;
                if (status.equals(RestStatus.OK)) {
                    pagedAccount = new PagedAccount();
                    try {
                        SearchHits hits = searchResponse.getHits();
                        SearchHit[] searchHits = hits.getHits();
                        List<Account> accountList = null;
                        if (searchHits.length > 0) {
                            accountList = new ArrayList<>();
                            for (SearchHit hit : searchHits) {
                                String id = hit.getId();
                                Account account = deserialize(hit.getSource(), Account.class);
                                account.set_id(id);
                                accountList.add(account);
                            }
                        }
                        pagedAccount.setAccountList(accountList);
                        findFuture.complete(pagedAccount);
                    } catch (Exception e) {
                        findFuture.completeExceptionally(e);
                    }
                } else {
                    //status!=ok 说明异步查询失败，返回的findFuture中就没有pageAccount这个对象了
                    //查询结果为空，则pageAccount对象的list属性为null
                    findFuture.completeExceptionally(new Throwable("search account haveVideo=2 by weMediaName fail, response : "
                            + status));
                }
            }
        });
        return findFuture;
    }

    @Override
    public CompletableFuture<PagedAccount> findAccountByCondition(AccountEsReq accountParam) {
        CompletableFuture<PagedAccount> finalFuture = new CompletableFuture<>();
        try {
            Objects.requireNonNull(accountParam);

            String[] indices = new String[]{this.indexV2};
            String[] types = new String[]{this.typeV2};
            BoolQueryBuilder queryBuilder = boolQuery();

            String paramId = accountParam.get_id();
            if (StringUtils.isNotBlank(paramId)) {
                queryBuilder.must(termQuery("_id", paramId));
            }
            Long eAccountId = accountParam.geteAccountId();
            if (eAccountId != null && eAccountId > 0) {
                queryBuilder.must(termQuery("eAccountId", eAccountId.longValue()));
            }
            String fhtId = accountParam.getFhtId();
            if (StringUtils.isNotBlank(fhtId)) {
                queryBuilder.must(termQuery("fhtId.keyword", fhtId));
            }
            String fhtName = accountParam.getFhtName();
            if (StringUtils.isNotBlank(fhtName)) {
                queryBuilder.must(termQuery("fhtName.keyword", fhtName));
            }
            String weMediaName = accountParam.getWeMediaName();
            if (StringUtils.isNotBlank(weMediaName)) {
                queryBuilder.must(matchPhraseQuery("weMediaName", weMediaName));
            }
            String categoryId = accountParam.getCategoryId();
            if (StringUtils.isNotBlank(categoryId)) {
                queryBuilder.must(termQuery("categoryId.keyword", categoryId));
            }
            List<Integer> accountType = accountParam.getAccountType();
            if (CollectionUtils.isNotEmpty(accountType)) {
                queryBuilder.must(termsQuery("accountType", accountType));
            }
            List<Integer> weMediaType = accountParam.getWeMediaType();
            if (CollectionUtils.isNotEmpty(weMediaType)) {
                queryBuilder.must(termsQuery("weMediaType", weMediaType));
            }
            List<Integer> orgType = accountParam.getOrgType();
            if (CollectionUtils.isNotEmpty(orgType)) {
                queryBuilder.must(termsQuery("orgType", orgType));
            }
            Integer online = accountParam.getOnline();
            if (online != null) {
                queryBuilder.must(termQuery("online", online.intValue()));
            }
            List<Integer> auditStatus = accountParam.getStatus();
            if (CollectionUtils.isNotEmpty(auditStatus)) {
                queryBuilder.must(termsQuery("status", auditStatus));
            }
            List<Integer> level = accountParam.getLevel();
            if (CollectionUtils.isNotEmpty(level)) {
                queryBuilder.must(termsQuery("level", level));
            }
            List<Integer> dataSource = accountParam.getDataSource();
            if (CollectionUtils.isNotEmpty(dataSource)) {
                queryBuilder.must(termsQuery("dataSource", dataSource));
            }
            Integer haveVideo = accountParam.getHaveVideo();
            if (haveVideo != null) {
                queryBuilder.must(termQuery("haveVideo", haveVideo.intValue()));
            }
            Integer fhhCopyright = accountParam.getFhhCopyright();
            if (fhhCopyright != null) {
                queryBuilder.must(termQuery("fhhCopyright", fhhCopyright.intValue()));
            }
            Integer highQuality = accountParam.getHighQuality();
            if (highQuality != null) {
                queryBuilder.must(termQuery("highQuality", highQuality.intValue()));
            }
            Integer accountContentType = accountParam.getAccountContentType();
            if (accountContentType != null) {
                queryBuilder.must(termQuery("accountContentType", accountContentType.intValue()));
            }
            String operatorName = accountParam.getOperatorName();
            if (StringUtils.isNotBlank(operatorName)) {
                queryBuilder.must(termQuery("operatorName.keyword", operatorName));
            }
            String idCard = accountParam.getIdCard();
            if (StringUtils.isNotBlank(idCard)) {
                queryBuilder.must(termQuery("idCard.keyword", idCard));
            }
            String operatorMail = accountParam.getOperatorMail();
            if (StringUtils.isNotBlank(operatorMail)) {
                queryBuilder.must(matchQuery("operatorMail.keyword", operatorMail));
            }
            String operatorTelephone = accountParam.getOperatorTelephone();
            if (StringUtils.isNotBlank(operatorTelephone)) {
                queryBuilder.must(termQuery("operatorTelephone.keyword", operatorTelephone));
            }
            Integer accountInfoStatus = accountParam.getAccountInfoStatus();
            if (accountInfoStatus != null) {
                queryBuilder.must(termQuery("accountInfoStatus", accountInfoStatus.intValue()));
            }
            String accounCategory_wxb = accountParam.getAccounCategory_wxb();
            if (StringUtils.isNotBlank(accounCategory_wxb)) {
                queryBuilder.must(matchPhraseQuery("accounCategory_wxb", accounCategory_wxb));
            }
            Integer politics_wxb = accountParam.getPolitics_wxb();
            if (politics_wxb != null) {
                queryBuilder.must(termQuery("politics_wxb", politics_wxb.intValue()));
            }
            String operationCategory = accountParam.getOperationCategory();
            if (StringUtils.isNotBlank(operationCategory)) {
                queryBuilder.must(termQuery("operationCategory.keyword", operationCategory));
            }
            String manager = accountParam.getManager();
            if (StringUtils.isNotBlank(manager)) {
                queryBuilder.must(termQuery("manager.keyword", manager));
            }
            String behavioralControl = accountParam.getBehavioralControl();
            if (StringUtils.isNotBlank(behavioralControl)) {
                queryBuilder.must(matchPhraseQuery("behavioralControl", behavioralControl));
            }
            String honorId = accountParam.getHonorId();
            if (StringUtils.isNotBlank(honorId)) {
                queryBuilder.must(termQuery("honorId.keyword", honorId));
            }
            String authorityTag = accountParam.getAuthorityTag();
            if (StringUtils.isNotBlank(authorityTag)) {
                queryBuilder.must(termQuery("authorityTag.keyword", authorityTag));
            }
            String accountAlias = accountParam.getAccountAlias();
            if (StringUtils.isNotBlank(accountAlias)) {
                queryBuilder.must(matchPhraseQuery("accountAlias", accountAlias));
            }

            Integer silence = accountParam.getSilence();
            if (silence != null) {
                if (silence == 0) {//1-禁言，0-非禁言
                    queryBuilder.mustNot(rangeQuery("silenceEndTime").gte(System.currentTimeMillis()));
                } else if (silence == 1) {
                    queryBuilder.must(rangeQuery("silenceEndTime").gte(System.currentTimeMillis()));
                }
            }
            Integer haveFhtId = accountParam.getHaveFhtId();
            if (haveFhtId != null) {
                if (haveFhtId == 0) {//1-有，0-无
                    queryBuilder.mustNot(existsQuery("fhtId.keyword"));
                } else if (haveFhtId == 1) {
                    queryBuilder.must(existsQuery("fhtId.keyword"));
                }
            }

            String socialCreditCode = accountParam.getSocialCreditCode();
            if (StringUtils.isNotBlank(socialCreditCode)) {
                queryBuilder.must(termQuery("socialCreditCode.keyword", socialCreditCode));
            }

            String startTime = accountParam.getApplyTimeStart();
            String endTime = accountParam.getApplyTimeEnd();
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                queryBuilder.must(rangeQuery("applyTime").gte(DateUtils.parse(startTime).getTime()).lte(DateUtils.parse(endTime).getTime()));
            }

            Double scoreDoubleStart = accountParam.getScoreDoubleStart();
            Double scoreDoubleEnd = accountParam.getScoreDoubleEnd();
            if (scoreDoubleStart != null && scoreDoubleEnd != null) {
                queryBuilder.must(rangeQuery("score_double").gte(scoreDoubleStart).lte(scoreDoubleEnd));
            }


            SortOrder sortOrder = SortOrder.DESC;
            if (accountParam.getSortRule().equals("asc")) {
                sortOrder = SortOrder.ASC;
            }
            QueryOptions options = new QueryOptions()
                    .addSort(
                            fieldSort(accountParam.getSortField())
                                    .order(sortOrder)
                    );
            if (accountParam.getPageSize() > 0 && accountParam.getPageNum() >= 0) {
                options.setFrom(accountParam.getPageSize() * accountParam.getPageNum())
                        .setSize(accountParam.getPageSize());
            }
            elasticSearchTemplate.searchWithOptions(indices, types, queryBuilder, options).whenComplete((response, throwable) -> {
                if(throwable!=null){
                    finalFuture.completeExceptionally(throwable);
                } else {
                    RestStatus status = response.status();
                    PagedAccount pagedAccount;
                    if (status.equals(RestStatus.OK)) {
                        pagedAccount = new PagedAccount();
                        try {
                            SearchHits hits = response.getHits();
                            SearchHit[] searchHits = hits.getHits();
                            int total = ((Long) hits.totalHits()).intValue();
                            pagedAccount.setTotal(total);
                            List<Account> accountList = null;
                            if (searchHits.length > 0) {
                                accountList = new ArrayList<>();
                                for (SearchHit hit : searchHits) {
                                    String id = hit.getId();
                                    Account account = deserialize(hit.getSource(), Account.class);
                                    account.set_id(id);
                                    accountList.add(account);
                                }
                            }
                            pagedAccount.setAccountList(accountList);
                            finalFuture.complete(pagedAccount);
                        } catch (Exception e) {
                            finalFuture.completeExceptionally(e);
                        }
                    } else {
                        finalFuture.completeExceptionally(new Throwable("search paged account by contion fail, response : "
                                + status));
                    }
                }
            });
        } catch (Exception e) {
            finalFuture.completeExceptionally(e);
        }
        return finalFuture;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndexV2() {
        return indexV2;
    }

    public void setIndexV2(String indexV2) {
        this.indexV2 = indexV2;
    }

    public String getTypeV2() {
        return typeV2;
    }

    public void setTypeV2(String typeV2) {
        this.typeV2 = typeV2;
    }

}
