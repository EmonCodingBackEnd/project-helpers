package com.coding.helpers.tool.cmp.idempotent.annotation.resolver;

import com.coding.helpers.core.annotation.support.TargetPoint;
import com.coding.helpers.tool.cmp.api.AppRequest;
import com.coding.helpers.tool.cmp.api.AppResponse;
import com.coding.helpers.tool.cmp.idempotent.annotation.resolver.config.IdempotenceConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.KryoCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class NonIdempotentResolver {

    @Autowired private RedissonClient redisson;

    @Autowired private IdempotenceConfig idempotenceConfig;

    private Codec codec = new KryoCodec();

    @Around(
        value =
                "@annotation(com.coding.helpers.tool.cmp.idempotent.annotation.NonIdempotent) || @within(com.coding.helpers.tool.cmp.idempotent.annotation.NonIdempotent)"
    )
    public Object doValidate(ProceedingJoinPoint point) throws Throwable {
        TargetPoint targetPoint = TargetPoint.createTargetPoint(point);
        Object[] args = targetPoint.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                if (AppRequest.class.isAssignableFrom(arg.getClass())) {
                    AppRequest request = (AppRequest) arg;
                    String requestId = request.getRequestId();
                    boolean validateResult = validateRequestId(requestId);
                    if (!validateResult) {
                        if (AppResponse.class.isAssignableFrom(targetPoint.getReturnType())) {
                            AppResponse response =
                                    (AppResponse) targetPoint.getReturnType().newInstance();
                            response.setErrorCode(1);
                            response.setErrorMessage("不允许重复提交！");
                            return response;
                        }
                    }
                }
            }
        }
        return point.proceed();
    }

    private boolean validateRequestId(String requestId) {
        if (requestId == null) {
            log.warn("【非幂等校验】requestId为空，默认校验结果为 false");
            return false;
        } else {
            RSet<String> set = redisson.getSet(idempotenceConfig.getRedisKey(), codec);
            set.expireAtAsync(getDayEnd(new Date()));
            return set.add(requestId);
        }
    }

    private Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
