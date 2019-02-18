package com.coding.helpers.plugin.gray;

import com.coding.helpers.plugin.gray.config.RequestRuleProperties;
import com.coding.helpers.plugin.gray.request.rule.FilterRequestRule;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

@Setter
@Slf4j
public class CoreHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private RequestRuleProperties ruleProperties;

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        FilterRequestRule rule = CoreHeaderInterceptor.rule.get();
        if (rule != null) {
            requestWrapper.getHeaders().add(CoreHeaderInterceptor.HEADER_RULE, rule.toRule());
        }
        return execution.execute(request, body);
    }
}
