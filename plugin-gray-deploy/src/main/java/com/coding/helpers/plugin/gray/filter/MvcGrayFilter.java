package com.coding.helpers.plugin.gray.filter;

import com.coding.helpers.plugin.gray.CoreHeaderInterceptor;
import com.coding.helpers.plugin.gray.config.RequestRuleProperties;
import com.coding.helpers.plugin.gray.request.rule.FilterRequestRule;
import com.coding.helpers.plugin.gray.request.rule.RequestRuleFactory;
import lombok.Setter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Setter
public class MvcGrayFilter extends OncePerRequestFilter {

    private RequestRuleProperties ruleProperties;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String labels = request.getHeader(CoreHeaderInterceptor.HEADER_RULE);
        FilterRequestRule rule = RequestRuleFactory.create(labels);
        labels = ruleProperties.updateRule(rule);
        CoreHeaderInterceptor.initHystrixRequestContext(labels);
        try {
            filterChain.doFilter(request, response);
        } finally {
            CoreHeaderInterceptor.shutdownHystrixRequestContext();
        }
    }
}
