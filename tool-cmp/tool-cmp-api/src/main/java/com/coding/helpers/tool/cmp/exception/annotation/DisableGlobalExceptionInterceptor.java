package com.coding.helpers.tool.cmp.exception.annotation;

import java.lang.annotation.*;

/**
 * 忽略全局异常处理注解定义.
 *
 * <p>创建时间: <font style="color:#00FFFF">20191130 11:50</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisableGlobalExceptionInterceptor {}
