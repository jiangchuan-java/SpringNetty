package com.ifeng.fhh.fhhService.web.action;


import com.alibaba.fastjson.JSON;
import com.ifeng.fhh.fhhService.service.account.AccountScoreRecordService;

import com.ifeng.fhh.zmt.tools.JackSonUtils;

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
 * 获取账号分值记录
 *
 * @Auther: zhunn
 * @Date: 2019/12/06 16:26
 * @Description:
 */
@Controller
public class AccountScoreListGetAction implements RouteAction {

    private static final Logger LOG = LoggerFactory.getLogger(AccountScoreListGetAction.class);

    @Autowired
    private AccountScoreRecordService accountScoreRecordService;


    @Override
    @RequestMapping(path = "/account/scoreRecord/list", method = RequestMethod.GET)
    public CompletableFuture<ActionResponse> route(ActionRequest request) {
        CompletableFuture<ActionResponse> responseFuture = new CompletableFuture<>();
        try {
            String eAccountId = request.getParams().get("eAccountId");

            if(Objects.isNull(eAccountId)){
                throw new Exception("eAccountId cannot be null");
            }

            Objects.requireNonNull(accountScoreRecordService, "accountScoreRecordServicecannot be null");
            accountScoreRecordService.findAccountScoreListByeAccountId(Long.valueOf(eAccountId)).whenComplete(((list, throwable) -> {
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

            }));
        } catch (Exception e) {
            LOG.error("execute find account score list by eAccountId fail: {}", e);
            responseFuture.completeExceptionally(e);
        }

        return responseFuture;
    }

}
