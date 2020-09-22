package com.ifeng.fhh.fhhService.web.action;

import com.alibaba.fastjson.JSON;
import com.ifeng.fhh.fhhService.interceptor.JustForTest;
import com.ifeng.fhh.fhhService.model.domain.Account;
import com.ifeng.fhh.fhhService.model.domain.Honor;
import com.ifeng.fhh.fhhService.model.transform.PagedAccount;
import com.ifeng.fhh.fhhService.model.transform.account.AccountEsReq;
import com.ifeng.fhh.fhhService.repository.database.HonorDBDao;
import com.ifeng.fhh.fhhService.service.account.AccountService;
import com.ifeng.fhh.fhhService.service.category.CategoryService;
import com.ifeng.fhh.zmt.tools.EmptyUtils;
import com.ifeng.fhh.zmt.web.route.ActionRequest;
import com.ifeng.fhh.zmt.web.route.ActionResponse;
import com.ifeng.fhh.zmt.web.route.RouteAction;
import com.ifeng.fhh.zmt.web.spring.custom_autoProxy_annotation.InterceptorAutoProxy;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-7
 */
@Controller
@InterceptorAutoProxy(interceptorNames = "AuthorityInterceptor")
public class AccountInfoFromEsAction implements RouteAction {

    private static final Logger LOG = LoggerFactory.getLogger(AccountInfoFromEsAction.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HonorDBDao honorDBDao;
    @Autowired
    private JustForTest worker;

    @Override
    @RequestMapping(path = "/account/es/info/get", method = RequestMethod.POST)
    public CompletableFuture<ActionResponse> route(ActionRequest request) {

        CompletableFuture<ActionResponse> responseFuture = new CompletableFuture<>();
        CompletableFuture<PagedAccount> accountNameFuture = new CompletableFuture<>();
        String paramBody = request.getBodyString();
        try {
            if (EmptyUtils.isEmpty(paramBody)) {
                throw new Exception("param cannot be empty");
            }
            LOG.info("execute paramBody:{}, get account info from es", paramBody);
            AccountEsReq accountEsParam = JSON.parseObject(paramBody, AccountEsReq.class);
            //账号
            accountService.findAccountInfoFromEs(accountEsParam).whenComplete((accounts, throwable) -> {
                LOG.info("findAccountInfoFromEs accounts:{}, throwable：{}", accounts, throwable);
                if(throwable != null){
                    accountNameFuture.completeExceptionally(throwable);
                } else {
                    if (accounts != null && CollectionUtils.isNotEmpty(accounts.getAccountList())) {
                        CompletableFuture<Map<String, String>> categoryFuture = categoryService.findAll();
                        CompletableFuture<Map<String, Honor>> honorFuture = honorDBDao.findAll();

                        CompletableFuture.allOf(categoryFuture, honorFuture).whenComplete( (Void, anyThrowable) -> {
                            LOG.info("findAccountInfoFromEs Void:{}, anyThrowable：{}", Void, anyThrowable);
                            if(anyThrowable != null){
                                accountNameFuture.completeExceptionally(anyThrowable);
                            } else {
                                Map<String, String> categoryMap = categoryFuture.join()!= null ? categoryFuture.join() : new HashMap<>();
                                Map<String, Honor> honorMap = honorFuture.join()!= null ? honorFuture.join() : new HashMap<>();

                                LOG.info("allOf categoryMap:{}, honorMap：{}", categoryMap, honorMap);
                                for (Account account : accounts.getAccountList()) {
                                    account.setCategoryName(categoryMap.get(account.getCategoryId()));
                                    Honor honor = honorMap.get(account.getHonorId());
                                    if (honor != null) {
                                        account.setHonorImg(honor.getImg());
                                    }
                                }
                                accountNameFuture.complete(accounts);
                            }

                        });
                    } else {
                        accountNameFuture.complete(accounts);
                    }
                }
            });

            accountNameFuture.whenComplete((listRes, throwable) -> {
                LOG.info("accountNameFuture listRes:{}, throwable：{}", listRes, throwable);
                ActionResponse response = new ActionResponse();
                response.setStatus(HttpResponseStatus.OK);
                String resp = "";
                if(throwable != null){
                    resp = RouteAction.failedJson(throwable.getMessage());
                } else {
                    resp = RouteAction.successfulJson(listRes);
                }
                response.setResp(resp);
                responseFuture.complete(response);
            });
        } catch (Exception e) {
            LOG.error("execute param:{}, find account info from es fail : {}", paramBody, e);
            responseFuture.completeExceptionally(e);
        }

        return responseFuture;
    }



}
