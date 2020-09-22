package com.ifeng.fhh.fhhService.web.action;

import com.ifeng.fhh.fhhService.model.domain.AccountScoreRecord;

import com.ifeng.fhh.fhhService.service.account.AccountScoreRecordService;
import com.ifeng.fhh.fhhService.service.account.impl.AccountScoreRecordServiceImpl;
import com.ifeng.fhh.zmt.tools.EmptyUtils;
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
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-7
 */
@Controller
public class AccountScoreInsertAction implements RouteAction {

    private static final Logger LOG = LoggerFactory.getLogger(AccountScoreRecordServiceImpl.class);

    @Autowired
    private AccountScoreRecordService accountScoreRecordService;

    @Override
    @RequestMapping(path = "/account/scoreRecord/add", method = RequestMethod.POST)
    public CompletableFuture<ActionResponse> route(ActionRequest request) {
        CompletableFuture<ActionResponse> responseFuture = new CompletableFuture<>();
        try {
            String bodyJson = request.getBodyString();

            if(Objects.isNull(bodyJson)){
                throw new Exception("body cannot be null");
            }

            AccountScoreRecord record = JackSonUtils.json2Bean(bodyJson, AccountScoreRecord.class);


            Long eAccountId = record.geteAccountId();
            if (EmptyUtils.isEmpty(eAccountId))
                throw new Exception("eAccountId cannot be empty");

            Objects.requireNonNull(accountScoreRecordService, "accountScoreRecordServicecannot be null");
            accountScoreRecordService.insertAndExecuteScoreRecord(record).whenComplete(((aBoolean, throwable) -> {
                ActionResponse response = new ActionResponse();
                response.setStatus(HttpResponseStatus.OK);
                String resp = "";
                if(throwable != null){
                    resp = RouteAction.failedJson(throwable.getMessage());
                } else {
                    resp = RouteAction.successfulJson("add and execute success");
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
