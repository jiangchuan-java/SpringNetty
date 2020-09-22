package com.ifeng.fhh.external.support.web.action;

import com.ifeng.fhh.external.support.database.mongo.DistributionDBDao;
import com.ifeng.fhh.external.support.entity.DistributionDocument;
import com.ifeng.fhh.zmt.tools.JackSonUtils;
import com.ifeng.fhh.zmt.web.route.ActionRequest;
import com.ifeng.fhh.zmt.web.route.ActionResponse;
import com.ifeng.fhh.zmt.web.route.RouteAction;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLDecoder;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;


/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-28
 */
@Component
public class DocumentInsertOrUpdateAction implements RouteAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentInsertOrUpdateAction.class);

    @Autowired
    private DistributionDBDao distributionDBDao;

    @RequestMapping(path = "/document/insertOrUpdate", method = RequestMethod.POST)
    public CompletableFuture<ActionResponse> route(ActionRequest request) {

        CompletableFuture<ActionResponse> finalFuture = new CompletableFuture<>();
        try {
            String body = request.getBodyString();
            body = URLDecoder.decode(body);
            DistributionDocument document = JackSonUtils.json2Bean(body, DistributionDocument.class);
            distributionDBDao.insertOrUpdate(document).whenComplete(new BiConsumer<Boolean, Throwable>() {
                @Override
                public void accept(Boolean aBoolean, Throwable throwable) {
                    ActionResponse response = new ActionResponse();
                    response.setStatus(HttpResponseStatus.OK);
                    String resp = "";
                    if (throwable != null) {
                        resp = RouteAction.failedJson(throwable.getMessage());
                    } else {
                        resp = RouteAction.successfulJson("insert success");
                    }
                    response.setResp(resp);
                    finalFuture.complete(response);
                }
            });
        }catch (Exception e) {
            LOGGER.error("route failed: ", e);
            finalFuture.completeExceptionally(e);
        }
        return finalFuture;
    }
}
