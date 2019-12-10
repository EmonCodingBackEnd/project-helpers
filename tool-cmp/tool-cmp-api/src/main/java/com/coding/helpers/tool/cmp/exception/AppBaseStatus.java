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

import lombok.Getter;

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
public interface AppBaseStatus {

    /**
     * 错误码设定由3部分组成：2位(10-99)系统编号 + 2位(00-99)系统模块编号或者错误类型编号 + 3位自定义编号.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190327 16:33</font><br>
     * 错误码第2部分，如果用来表示错误类型编号，可以定义如下：<br>
     *
     * <ul>
     *   <li>XX10000-普通校验错误，在BindingResult判断的错误，该错误包含XX1X表示的错误
     *   <li>XX11XXX-请求参数为空
     *   <li>XX12XXX-请求参数类型错误
     *   <li>XX13XXX-请求参数范围超限
     *   <li>XX14XXX-请求参数格式不正确（比如手机号的校验）
     *   <li>XX20000-统称登录校验错误
     *   <li>XX21000-未登录、未认证、401:unauthorized
     *   <li>XX22000-登录信息不正确（用户名密码等）
     *   <li>XX22001-用户不存在
     *   <li>XX22002-密码错误
     *   <li>XX23000-登录权限控制（账号封停、过期、锁定等）
     *   <li>XX23001-账号封停
     *   <li>XX23002-账号锁定
     *   <li>XX23003-账号已过期
     *   <li>XX24000-登录信息超时
     *   <li>XX25000-用户权限不足，403:forbidden
     *   <li>XX300000-服务端错误（一般指在Service中发生的涉及业务逻辑校验的错误，比如对象不存在、已存在、序列化/反序列化异常、格式转换异常、类型转换异常等）
     *   <li>XX31XXX-对象不存在（根据检索条件查询不到记录等）
     *   <li>XX32XXX-序列化/反序列化异常、格式转换异常、类型转换异常等
     *   <li>XX33XXX-对象已存在、重复等等
     *   <li>XX34XXX-逻辑校验错误，指代那些在请求校验正常的参数，在实际业务处理时发生的逻辑错误
     *   <li>XX35XXX-调用内部服务错误，非远程错误
     *   <li>XX80XXX-上传下载短信等外部服务错误
     *   <li>XX99XXX版本错误
     * </ul>
     *
     * @return java.lang.Integer
     * @author Rushing0711
     * @since 1.0.0
     */
    Integer getErrorCode();

    String getErrorMessage();

    AppBaseStatus SUCCESS = success();

    AppBaseStatus SYSTEM_UNEXPECTED_ERROR = systemUnexpectedError();

    /** 操作成功. */
    static AppBaseStatus success() {
        return new DefaultStatus(0, "操作成功");
    }

    /** 系统意料之内的异常. */
    static AppBaseStatus systemExpectedError() {
        return new DefaultStatus(10099000, "系统意料之内的异常");
    }

    /** 系统意料之外的异常. */
    static AppBaseStatus systemUnexpectedError() {
        return new DefaultStatus(10099999, "系统意料之外的异常");
    }

    @Getter
    final class DefaultStatus implements AppBaseStatus {
        private Integer errorCode;

        private String errorMessage;

        private DefaultStatus(Integer errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }
}
