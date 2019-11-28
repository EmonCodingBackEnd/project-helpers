package com.coding.helpers.service.gateway.filter;

import com.coding.helpers.service.gateway.exception.AppStatus;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 限流过滤器.
 *
 * <p>创建时间: <font style="color:#00FFFF">20191128 23:31</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class RateLimitFilter extends AbstractPreZuulFilter {

    /** 每秒可以获取100个令牌. */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        if (!RATE_LIMITER.tryAcquire()) {
            log.error("rate limit: {}", request.getRequestURI());
            return fail(AppStatus.BUSINESS_CHECK_ERROR.getErrorCode(), "限流异常");
        }
        log.info("get rate token success");
        return success();
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 7;
    }
}
