package com.coding.helpers.plugin.gray.config;

import com.coding.helpers.plugin.gray.constant.GrayConstants;
import com.coding.helpers.plugin.gray.request.rule.FilterRequestRule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@Getter
@Setter
@ConfigurationProperties(prefix = "helper.request.rule")
public class RequestRuleProperties {

    /** 向下请求规则内容. */
    private String data = GrayConstants.EMPTY;

    /** 传递模式. {@linkplain TransferModeEnum 参见} */
    private TransferModeEnum transferMode = TransferModeEnum.OVERRIDE_FIRST;

    public String updateRule(FilterRequestRule rule) {
        if (rule == null) {
            return this.data;
        }
        switch (transferMode) {
            case OVERRIDE_FIRST:
                if (StringUtils.isEmpty(this.data)) {
                    return rule.toRule();
                } else {
                    return this.data;
                }
            case PENETRATE:
                return rule.toRule();
            case OVERRIDE:
                return this.data;
            case CONCAT:
                if (StringUtils.isEmpty(this.data)) {
                    return rule.toRule();
                } else {
                    return rule.toRule().concat("&").concat(this.data);
                }
            default:
                return rule.toRule();
        }
    }
}
