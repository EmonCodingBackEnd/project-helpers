/*
 * 文件名称：AppStatus.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 21:59
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903092159 新建文件
 ********************************************************************************/
package com.coding.helpers.tool.cmp.exception;

/**
 * 异常基错误码类，各个系统使用枚举继承该类完成错误码的定义.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190309 22:29</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AppStatus {

    /** 系统意料之外的异常. */
    Integer SYSTEM_UNEXPECTED_ERROR_CODE = 9999999;

    String SYSTEM_UNEXPECTED_ERROR_MESSAGE = "系统意料之外的异常";

    default Integer getErrorCode() {
        return 0;
    }

    default String getErrorMessage() {
        return "处理成功";
    }
}
