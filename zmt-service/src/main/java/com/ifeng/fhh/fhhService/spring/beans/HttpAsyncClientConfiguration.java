package com.ifeng.fhh.fhhService.spring.beans;

import com.ifeng.fhh.fhhService.tools.httpClient.ApacheAsyncHttpClient;
import com.ifeng.fhh.fhhService.tools.httpClient.HttpClientTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-15
 */
@Configuration
public class HttpAsyncClientConfiguration {

    @Bean
    public HttpClientTemplate buildTemplate() throws Exception{
        return new ApacheAsyncHttpClient();
    }

}
