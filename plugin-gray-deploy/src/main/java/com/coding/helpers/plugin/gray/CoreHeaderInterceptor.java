package com.coding.helpers.plugin.gray;

import com.coding.helpers.plugin.gray.config.RequestRuleProperties;
import com.coding.helpers.plugin.gray.request.rule.FilterRequestRule;
import com.coding.helpers.plugin.gray.request.rule.RequestRuleFactory;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Setter
public class CoreHeaderInterceptor extends HandlerInterceptorAdapter {

    private RequestRuleProperties ruleProperties;

    public static final String HEADER_RULE = "x-rule";

    public static final HystrixRequestVariableDefault<FilterRequestRule> rule =
            new HystrixRequestVariableDefault<>();

    public static void initHystrixRequestContext(String labels) {
        log.info("rule: {}", labels);
        if (!HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.initializeContext();
        }

        FilterRequestRule rule = RequestRuleFactory.create(labels);

        if (rule != null) {
            CoreHeaderInterceptor.rule.set(rule);
        } else {
            CoreHeaderInterceptor.rule.remove();
        }
    }

    public static void shutdownHystrixRequestContext() {
        if (HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.getContextForCurrentThread().shutdown();
        }
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 用当前应用的配置更新传递规则
        String labels = request.getHeader(CoreHeaderInterceptor.HEADER_RULE);
        FilterRequestRule rule = RequestRuleFactory.create(labels);
        labels = ruleProperties.updateRule(rule);
        CoreHeaderInterceptor.initHystrixRequestContext(labels);
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            @Nullable ModelAndView modelAndView)
            throws Exception {
        CoreHeaderInterceptor.shutdownHystrixRequestContext();
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            @Nullable Exception ex)
            throws Exception {
        CoreHeaderInterceptor.shutdownHystrixRequestContext();
    }
}
