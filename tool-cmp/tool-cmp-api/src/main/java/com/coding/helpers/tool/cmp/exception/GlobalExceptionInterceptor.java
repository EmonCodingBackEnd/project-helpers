/*
 * 文件名称：ExceptionInterceptor.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 22:09
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903092209 新建文件
 ********************************************************************************/
package com.coding.helpers.tool.cmp.exception;

import com.coding.helpers.tool.cmp.api.AppResponse;
import com.coding.helpers.tool.cmp.exception.annotation.DisableGlobalExceptionInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ConditionalOnMissingBean(annotation = DisableGlobalExceptionInterceptor.class)
@Slf4j
public class GlobalExceptionInterceptor {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AppResponse handle(Exception e) {
        AppResponse<Object> appResponse = AppResponse.getDefaultResponse();
        if (e instanceof AppException) {
            AppException appException = (AppException) e;
            appResponse.setErrorCode(appException.getErrorCode());
            appResponse.setErrorMessage(appException.getErrorMessage());
        } else {
            log.error(
                    String.format(
                            "【系统异常】errorCode=%s,errorMessage=%s",
                            AppBaseStatus.SYSTEM_UNEXPECTED_ERROR.getErrorCode(),
                            AppBaseStatus.SYSTEM_UNEXPECTED_ERROR.getErrorMessage()),
                    e);
            appResponse.setErrorCode(AppBaseStatus.SYSTEM_UNEXPECTED_ERROR.getErrorCode());
            appResponse.setErrorMessage(AppBaseStatus.SYSTEM_UNEXPECTED_ERROR.getErrorMessage());
            appResponse.setData(e.getMessage());
        }
        return appResponse;
    }
}
