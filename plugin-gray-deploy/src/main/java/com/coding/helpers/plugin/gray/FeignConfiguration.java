package com.coding.helpers.plugin.gray;

import com.coding.helpers.plugin.gray.config.RequestRuleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(name = {"feign.RequestInterceptor"})
@EnableConfigurationProperties(RequestRuleProperties.class)
@RefreshScope
public class FeignConfiguration {

    @Autowired private RequestRuleProperties ruleProperties;

    @Bean
    public FeignHeaderRequestInterceptor feignHeaderRequestInterceptor() {
        FeignHeaderRequestInterceptor interceptor = new FeignHeaderRequestInterceptor();
        interceptor.setRuleProperties(ruleProperties);
        return interceptor;
    }
}
