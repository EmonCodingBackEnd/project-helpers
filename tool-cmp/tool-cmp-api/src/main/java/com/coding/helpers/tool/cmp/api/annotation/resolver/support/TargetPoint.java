/*
 * 文件名称：TargetPoint.java
 * 系统名称：project-helpers
 * 模块名称：tool-cmp-api
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190303 22:02
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190303-01         Rushing0711     M201903032202 新建文件
 ********************************************************************************/
package com.coding.helpers.tool.cmp.api.annotation.resolver.support;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 拦截点信息.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190303 21:44</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@Setter
public class TargetPoint {

    private Object target;

    private Method method;

    private Object[] args;

    private String[] argNames;

    private Class<?> returnType;

    /**
     * 获取方法上的指定注解信息.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190303 21:57</font><br>
     * [请在此输入功能详述]
     *
     * @param <A> - 注解类型
     * @param annotationType - 注解类，或者说注解类型的类型
     * @return A - 返回注解类型A的实例对象
     * @author Rushing0711
     * @since 1.0.0
     */
    public <A extends Annotation> A getMethodAnnotation(Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(this.method, annotationType);
    }

    /**
     * 获取类上的指定注解信息.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190303 22:05</font><br>
     * [请在此输入功能详述]
     *
     * @param <A> - 注解类型
     * @param annotationType - 注解类，或者说注解类型的类型
     * @return A - 返回注解类型A的实例对象
     * @author Rushing0711
     * @since 1.0.0
     */
    public <A extends Annotation> A getClassAnnotation(Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(this.target.getClass(), annotationType);
    }

    /**
     * 从目标类开始搜索指定注解，获取其所在的第一个类.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190303 22:13</font><br>
     * [请在此输入功能详述]
     *
     * @param annotationType - 注解类，或者说注解类型的类型
     * @return java.lang.Class - 指定注解所在的第一个类
     * @author Rushing0711
     * @since 1.0.0
     */
    public Class<?> findAnnotationDeclaringClass(Class<? extends Annotation> annotationType) {
        return AnnotationUtils.findAnnotationDeclaringClass(annotationType, this.target.getClass());
    }

    public static TargetPoint createTargetPoint(JoinPoint joinPoint) {
        TargetPoint targetPoint = new TargetPoint();
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        targetPoint.setTarget(target);
        targetPoint.setArgs(args);

        if (MethodSignature.class.isAssignableFrom(joinPoint.getSignature().getClass())) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            targetPoint.setMethod(methodSignature.getMethod());
            targetPoint.setArgNames(methodSignature.getParameterNames());
        }
        return targetPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TargetPoint that = (TargetPoint) o;

        return (target != null ? target.equals(that.target) : that.target == null)
                && (method != null ? method.equals(that.method) : that.method == null);
    }

    @Override
    public int hashCode() {
        int result = target != null ? target.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }
}
