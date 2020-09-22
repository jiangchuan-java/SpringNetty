package com.ifeng.fhh.fhhService.web.action;

import com.alibaba.fastjson.JSON;
import com.ifeng.fhh.fhhService.model.domain.Account;
import com.ifeng.fhh.fhhService.service.account.AccountService;
import com.ifeng.fhh.fhhService.service.account.impl.AccountScoreRecordServiceImpl;
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

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-7
 */
@Controller
public class InnerAccountInfoUpdateAction implements RouteAction {

    private static final Logger LOG = LoggerFactory.getLogger(AccountScoreRecordServiceImpl.class);

    @Autowired
    private AccountService accountService;

    @Override
    @RequestMapping(path = "/inner/account/update/", method = RequestMethod.POST)
    public CompletableFuture<ActionResponse> route(ActionRequest request) {
        CompletableFuture<ActionResponse> responseFuture = new CompletableFuture<>();
        try {
            String bodyJson = request.getBodyString();

            if(Objects.isNull(bodyJson)){
                throw new Exception("body cannot be null");
            }

            Account accountSaveReq = JSON.parseObject(bodyJson, Account.class);


            Long eAccountId = accountSaveReq.geteAccountId();
            if (EmptyUtils.isEmpty(eAccountId))
                throw new Exception("eAccountId cannot be empty");

            Objects.requireNonNull(accountService, "accountService be null");
            accountService.saveAccount(accountSaveReq).whenComplete(((along, throwable) -> {
                ActionResponse response = new ActionResponse();
                response.setStatus(HttpResponseStatus.OK);
                String resp = "";
                if(throwable != null){
                    resp = RouteAction.failedJson(throwable.getMessage());
                } else {
                    resp = RouteAction.successfulJson(along);
                }
                response.setResp(resp);
                responseFuture.complete(response);

            }));
        } catch (Exception e) {
            LOG.error("execute add account score record fail: {}", e);
            responseFuture.completeExceptionally(e);
        }

        return responseFuture;
    }
}
