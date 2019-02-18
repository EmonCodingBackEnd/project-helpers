package com.coding.helpers.plugin.gray;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

public class DefaultRibbonConfiguration {

    @Value("${ribbon.client.name:#{null}")
    private String name;

    @Autowired(required = false)
    private IClientConfig config;

    private PropertiesFactory propertiesFactory;

    @Bean
    public IRule ribbonRule() {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        if (this.propertiesFactory.isSet(IRule.class, name)) {
            return this.propertiesFactory.get(IRule.class, config, name);
        }

        FilterAndWeightMetadataRule rule = new FilterAndWeightMetadataRule();
        rule.initWithNiwsConfig(config);
        return rule;
    }
}
