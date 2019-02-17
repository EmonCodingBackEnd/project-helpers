package com.coding.helpers.plugin.gray;

import com.coding.helpers.plugin.gray.annotation.EnableMvcGrayFilter;
import com.coding.helpers.plugin.gray.config.RequestRuleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnMissingBean(annotation = EnableMvcGrayFilter.class)
public class CoreAutoConfiguration implements WebMvcConfigurer {

    @Autowired private RequestRuleProperties ruleProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CoreHeaderInterceptor coreHeaderInterceptor = new CoreHeaderInterceptor();
        coreHeaderInterceptor.setRuleProperties(ruleProperties);
        registry.addInterceptor(coreHeaderInterceptor);
    }
}
