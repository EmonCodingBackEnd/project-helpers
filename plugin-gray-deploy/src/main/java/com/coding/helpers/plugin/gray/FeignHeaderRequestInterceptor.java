package com.coding.helpers.plugin.gray;

import com.coding.helpers.plugin.gray.config.RequestRuleProperties;
import com.coding.helpers.plugin.gray.constant.GrayConstants;
import com.coding.helpers.plugin.gray.request.rule.FilterRequestRule;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Slf4j
public class FeignHeaderRequestInterceptor implements RequestInterceptor {

    private RequestRuleProperties ruleProperties;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        FilterRequestRule rule = CoreHeaderInterceptor.rule.get();
        if (rule != null) {
            requestTemplate.header(GrayConstants.RULE_HEADER, rule.toRule());
        }
    }
}
