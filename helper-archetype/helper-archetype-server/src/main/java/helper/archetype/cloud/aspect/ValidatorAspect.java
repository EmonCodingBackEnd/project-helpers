/*
 * 文件名称：ValidatorAspect.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190311 18:53
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190311-01         Rushing0711     M201903111853 新建文件
 ********************************************************************************/
package helper.archetype.cloud.aspect;

import helper.archetype.cloud.exception.AppStatus;
import com.coding.helpers.tool.cmp.api.AppRequest;
import com.coding.helpers.tool.cmp.exception.AppException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/** 验证的切面. */
@Aspect
@Component
public class ValidatorAspect {

    @Autowired private Validator validator;

    @Pointcut("execution(* helper.archetype.cloud.service..*.*(..))")
    public void publicMethods() {}

    @Before("publicMethods()")
    public void instrumentMetered(JoinPoint jp) {
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof AppRequest) {
                String message = "";
                Set<ConstraintViolation<Object>> set = validator.validate(arg);
                for (ConstraintViolation<Object> s : set) {
                    message = s.getMessage();
                    break;
                }
                if (StringUtils.isNotEmpty(message)) {
                    throw new AppException(AppStatus.PARAM_ERROR, message);
                }
            }
        }
    }
}
