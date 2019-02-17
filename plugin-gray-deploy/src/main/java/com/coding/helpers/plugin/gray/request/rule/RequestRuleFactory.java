package com.coding.helpers.plugin.gray.request.rule;

import org.springframework.util.StringUtils;

public class RequestRuleFactory {

    private static final String RULE_TYPE_TAG_SPLIT = "#";

    private static final String RULE_AND_SPLIT = "&";

    private static final String RULE_AND_NAME = "AND";

    private static final String RULE_OR_SPLIT = "|";

    private static final String RULE_OR_NAME = "OR";

    public static FilterRequestRule create(String ruleStr) {
        if (StringUtils.isEmpty(ruleStr)) {
            return null;
        }
        if (ruleStr.contains(RULE_AND_SPLIT) || ruleStr.contains(RULE_OR_SPLIT)) {
            return createCompose(ruleStr);
        } else {
            return createCommon(ruleStr);
        }
    }

    static CommonRequestRule createCommon(String ruleStr) {
        if (StringUtils.isEmpty(ruleStr)) {
            return null;
        }
        String[] arr = ruleStr.split(RULE_TYPE_TAG_SPLIT);
        if (arr.length > 2) {
            return null;
        }
        if (arr.length == 1) {
            return new CommonRequestRule(1, arr[0]);
        } else {
            return new CommonRequestRule(Integer.parseInt(arr[0]), arr[1]);
        }
    }

    static ComposeRequestRule createCompose(String ruleStr) {
        if (ruleStr == null) {
            return null;
        }
        if (ruleStr.contains(RULE_AND_SPLIT)) {
            ComposeRequestRule rule = new ComposeRequestRule();
            rule.setLogic(RULE_AND_NAME);
            for (String _rule : ruleStr.split(RULE_AND_SPLIT)) {
                rule.addRule(createCommon(_rule));
            }
            return rule;
        } else if (ruleStr.contains(RULE_OR_SPLIT)) {
            ComposeRequestRule rule = new ComposeRequestRule();
            rule.setLogic(RULE_OR_NAME);
            for (String _rule : ruleStr.split(RULE_OR_SPLIT)) {
                rule.addRule(createCommon(_rule));
            }
            return rule;
        } else {
            ComposeRequestRule rule = new ComposeRequestRule();
            rule.setLogic(RULE_AND_NAME);
            rule.addRule(createCommon(ruleStr));
            return rule;
        }
    }
}
