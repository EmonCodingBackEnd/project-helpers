/*
 * 文件名称：AppExceptiion.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 21:55
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903092155 新建文件
 ********************************************************************************/
package com.coding.helpers.tool.cmp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppException extends RuntimeException {

    private static final long serialVersionUID = 8623276252895911775L;

    protected Integer errorCode;

    protected String errorMessage;

    public AppException(AppBaseStatus appStatus) {
        super(appStatus.getErrorMessage());
        this.errorCode = appStatus.getErrorCode();
        this.errorMessage = appStatus.getErrorMessage();
    }

    public AppException(AppBaseStatus appStatus, String errorMessage) {
        super(errorMessage);
        this.errorCode = appStatus.getErrorCode();
        this.errorMessage = errorMessage;
    }
}
