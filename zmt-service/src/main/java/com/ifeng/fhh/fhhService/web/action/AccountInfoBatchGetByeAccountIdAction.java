package com.ifeng.fhh.fhhService.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifeng.fhh.fhhService.model.domain.Account;
import com.ifeng.fhh.fhhService.service.account.AccountService;
import com.ifeng.fhh.zmt.tools.EmptyUtils;
import com.ifeng.fhh.zmt.web.route.ActionRequest;
import com.ifeng.fhh.zmt.web.route.ActionResponse;
import com.ifeng.fhh.zmt.web.route.RouteAction;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-7
 */
@Controller
public class AccountInfoBatchGetByeAccountIdAction implements RouteAction {

    private static final Logger LOG = LoggerFactory.getLogger(AccountInfoBatchGetByeAccountIdAction.class);

    @Autowired
    private AccountService accountService;

    @Override
    @RequestMapping(path = "/account/info/batch/get", method = RequestMethod.POST)
    public CompletableFuture<ActionResponse> route(ActionRequest request) {

        CompletableFuture<ActionResponse> responseFuture = new CompletableFuture<>();
        String paramBody = request.getBodyString();
        try {
            JSONObject paramMap = JSON.parseObject(paramBody);
            String idsParam = paramMap.getString("eAccountIds");
            if (EmptyUtils.isEmpty(idsParam)) {
                throw new Exception("id cannot be empty");
            }
            List<Long> ids = JSON.parseArray(idsParam, Long.class);
            if (ids.size() > 30) {
                throw new Exception("query param id more than 30");
            }

            CompletableFuture<List<Account>> accountListFuture = new CompletableFuture<>();
            accountService.batchFindByeAccountId(ids).whenComplete((list, throwable)->{
                if(throwable != null){
                    accountListFuture.completeExceptionally(throwable);
                } else {
                    accountListFuture.complete(list);
                }
            });

            accountListFuture.whenComplete((list, throwable)->{
                ActionResponse response = new ActionResponse();
                response.setStatus(HttpResponseStatus.OK);
                String resp = "";
                if(throwable != null){
                    resp = RouteAction.failedJson(throwable.getMessage());
                } else {
                    resp = RouteAction.successfulJson(list);
                }
                response.setResp(resp);
                responseFuture.complete(response);
            });
        } catch (Exception e) {
            LOG.error("execute param:{}, batch find account baseInfo by id fail : {}", paramBody, e);
            responseFuture.completeExceptionally(e);
        }

        return responseFuture;
    }



}
