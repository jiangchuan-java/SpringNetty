package com.ifeng.fhh.fhhService.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifeng.fhh.fhhService.tools.httpClient.HttpClientTemplate;
import com.ifeng.fhh.zmt.web.route.ActionRequest;
import com.ifeng.fhh.zmt.web.route.ActionResponse;
import com.ifeng.fhh.zmt.web.route.RouteAction;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
@Component("AuthorityInterceptor")
public class AuthorityInterceptor implements MethodInterceptor {

    private static Map<String, String> roleMap = new HashMap<>();

    static {
        roleMap.put("wemedia_new_operator_account_edit", "/operator/account/update/"); //运营人员
        roleMap.put("wemedia_new_operator_account_add", "/operator/account/insert"); //新增账号
        roleMap.put("wemedia_new_auditor_account_edit", "/auditor/account/update/"); //审核编辑
        roleMap.put("wemedia_new_onliner_account_on_offline", "/onliner/account/update/"); //账号上下线
        roleMap.put("wemedia_new_silencer_account_silence", "/silence/account/update/"); // 账号禁言
        roleMap.put("wemedia_new_operator_account_edit", "/account/es/info/get"); //运营人员
    }

    private static final String TOKEN = "Authorization";

    @Autowired
    private HttpClientTemplate httpClientTemplate;

    @Value("${authorization.url}")
    private String authorUrl;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        CompletableFuture<ActionResponse> finalFuture = new CompletableFuture<>(); //验证结果的future
        CompletableFuture<ActionResponse> authorFuture = new CompletableFuture<>(); //验证结果的future
        CompletableFuture<String> requestFutuere = new CompletableFuture<>(); //请求的future
        Object[] args = invocation.getArguments();
        Object onlyOneParam = args.length > 0 ? args[0] : null;
        if (Objects.nonNull(onlyOneParam) && onlyOneParam instanceof ActionRequest) {/*仅拦截 route方法*/
            ActionRequest request = (ActionRequest) onlyOneParam;
            String path = request.getPath();
            Map<String, String> headers = request.getHeaders();
            String token = headers.get(TOKEN);
            headers = new HashMap<>();
            headers.put("Authorization", token);
            if (Objects.isNull(token)) {
                authorFuture.completeExceptionally(new Throwable("权限不足"));
            } else {
                //请求权限验证服务，根据token获取角色
                httpClientTemplate.get(authorUrl, headers, 200, 200, 200).whenComplete((resp, throwable) -> {
                    if (throwable != null) {
                        requestFutuere.completeExceptionally(throwable);
                    } else {
                        requestFutuere.complete(resp);
                    }
                });
                requestFutuere.whenComplete((resp, authorThrowable) -> {
                    if (authorThrowable != null) {
                        authorFuture.completeExceptionally(authorThrowable); /*请求失败了，验证也直接失败，保护接口调用*/
                    } else {
                        if (checkRole(resp, path)) { /*验证通过，调用代理方法返回结果， 不能直接返回是因为这都是内部类，给不到最外层*/
                            try {
                                CompletableFuture<ActionResponse> invokeFuture = (CompletableFuture<ActionResponse>) invocation.proceed();
                                invokeFuture.whenComplete((routeResult, routeThrowable) -> {
                                    if (routeThrowable != null) {
                                        authorFuture.completeExceptionally(routeThrowable);
                                    } else {
                                        authorFuture.complete(routeResult);
                                    }
                                });
                            } catch (Throwable invokeThrowable) {
                                invokeThrowable.printStackTrace();
                            }
                        } else {
                            authorFuture.completeExceptionally(new Throwable("权限不足"));
                        }
                    }
                });
            }
        } else {
            return invocation.proceed();
        }
        authorFuture.whenComplete((result, throwable) -> {
            ActionResponse response = new ActionResponse();
            String resp = "";
            if (throwable != null) {
                resp = RouteAction.failedJson(throwable.getMessage());
                response.setStatus(HttpResponseStatus.UNAUTHORIZED);
            } else {
                resp = result.getResp();
                response.setStatus(HttpResponseStatus.OK);
            }
            response.setResp(resp);
            finalFuture.complete(response);
        });
        return finalFuture;
    }

    private boolean checkRole(String resp, String path) {
        JSONObject jsonObject = JSON.parseObject(resp);
        JSONObject dataJson = jsonObject.getJSONObject("data");
        if (dataJson.containsKey("role")) {
            Object[] array = dataJson.getJSONArray("role").toArray();
            for (Object object : array) {
                if(Objects.equals(roleMap.get(object), path)){
                    return true;
                }
            }
        }
        return false;
    }

    public HttpClientTemplate getHttpClientTemplate() {
        return httpClientTemplate;
    }

    public void setHttpClientTemplate(HttpClientTemplate httpClientTemplate) {
        this.httpClientTemplate = httpClientTemplate;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }
}
