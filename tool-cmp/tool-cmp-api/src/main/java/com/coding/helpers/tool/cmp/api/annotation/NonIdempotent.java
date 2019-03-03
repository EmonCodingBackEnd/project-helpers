package com.coding.helpers.tool.cmp.api.annotation;

import java.lang.annotation.*;
import com.coding.helpers.tool.cmp.api.AppRequest;

/**
 * 标注该注解的方法，表示方法的重复执行，会导致非幂等的结果.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190303 23:49</font><br>
 * 标注该注解的方法，要求在请求信息中包含 {@linkplain AppRequest#requestId} 参数值。<br>
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NonIdempotent {}
