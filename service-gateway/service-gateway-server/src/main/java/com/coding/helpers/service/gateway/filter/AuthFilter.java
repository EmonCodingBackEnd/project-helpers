package com.coding.helpers.service.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 校验请求中传递的 Token.
 *
 * <p>创建时间: <font style="color:#00FFFF">20191128 23:21</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Component
public class AuthFilter extends AbstractPreZuulFilter {

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        log.info("{} request to {}", request.getMethod(), request.getRequestURL().toString());

        // 这里从url参数里获取，也可以从cookie或者header里获取
        Object token = request.getParameter("token");
        if (Objects.isNull(token)) {
            log.error("error: token is empty");
            return fail(HttpStatus.UNAUTHORIZED.value(), "error: token is empty");
        }
        return success();
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 5;
    }
}
