package com.coding.helpers.plugin.gray.request.rule;

import com.coding.helpers.plugin.gray.constant.GrayConstants;
import org.springframework.util.StringUtils;

public class RequestRuleFactory {

    public static FilterRequestRule create(String ruleStr) {
        if (StringUtils.isEmpty(ruleStr)) {
            return null;
        }
        if (ruleStr.contains(GrayConstants.RULE_AND_SPLIT)
                || ruleStr.contains(GrayConstants.RULE_OR_SPLIT)) {
            return createCompose(ruleStr);
        } else {
            return createCommon(ruleStr);
        }
    }

    private static CommonRequestRule createCommon(String ruleStr) {
        if (StringUtils.isEmpty(ruleStr)) {
            return null;
        }
        String[] arr = ruleStr.split(GrayConstants.RULE_TYPE_TAG_SPLIT);
        if (arr.length > 2) {
            return null;
        }
        if (arr.length == 1) {
            return new CommonRequestRule(1, arr[0]);
        } else {
            return new CommonRequestRule(Integer.parseInt(arr[0]), arr[1]);
        }
    }

    private static ComposeRequestRule createCompose(String ruleStr) {
        if (ruleStr == null) {
            return null;
        }
        if (ruleStr.contains(GrayConstants.RULE_AND_SPLIT)) {
            ComposeRequestRule rule = new ComposeRequestRule();
            rule.setLogic(GrayConstants.RULE_AND_NAME);
            for (String _rule : ruleStr.split(GrayConstants.RULE_AND_SPLIT)) {
                rule.addRule(createCommon(_rule));
            }
            return rule;
        } else if (ruleStr.contains(GrayConstants.RULE_OR_SPLIT)) {
            ComposeRequestRule rule = new ComposeRequestRule();
            rule.setLogic(GrayConstants.RULE_OR_NAME);
            for (String _rule : ruleStr.split(GrayConstants.RULE_OR_SPLIT)) {
                rule.addRule(createCommon(_rule));
            }
            return rule;
        } else {
            ComposeRequestRule rule = new ComposeRequestRule();
            rule.setLogic(GrayConstants.RULE_AND_NAME);
            rule.addRule(createCommon(ruleStr));
            return rule;
        }
    }
}
