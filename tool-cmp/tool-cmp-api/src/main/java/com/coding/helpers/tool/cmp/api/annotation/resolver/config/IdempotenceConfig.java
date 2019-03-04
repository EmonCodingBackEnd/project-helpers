package com.coding.helpers.tool.cmp.api.annotation.resolver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "helper.tool-cmp-api.nonidempotent")
public class IdempotenceConfig {

    /** 幂等性校验所需的redis缓存key，默认：<code>helper:tool:cmp:api:nonidempotent</code>. */
    private String redisKey = "helper:tool:cmp:api:nonidempotent";
}
