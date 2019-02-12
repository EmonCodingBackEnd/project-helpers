package com.netflix.hystrix.strategy.concurrency;

import org.springframework.beans.BeanUtils;

public class HystrixRequestContextCopy {

    public static HystrixRequestContext get() {
        HystrixRequestContext current = HystrixRequestContext.getContextForCurrentThread();
        if (current != null) {
            HystrixRequestContext context = BeanUtils.instantiateClass(HystrixRequestContext.class);
            context.state.putAll(current.state);
            return context;
        }
        return null;
    }
}
