#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * 文件名称：StatusDefinition.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190318 06:46
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190318-01         Rushing0711     M201903180646 新建文件
 ********************************************************************************/
package ${package}.exception;

import com.coding.helpers.tool.cmp.exception.AppBaseStatus;
import lombok.Getter;

/**
 * 异常状态的定义.
 *
 * <p>创建时间: <font style="color:${symbol_pound}00FFFF">20190318 06:49</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
public enum AppStatus implements AppBaseStatus {
    PARAM_ERROR(1010000, "请求参数错误"),
    OBJECT_NOT_EXIST(1031000, "对象不存在"),
    DICT_NOT_EXIST(1031001, "根据字典值找不到对应字典"),
    OBJECT_CONVERT_ERROR(1032000, "对象转换异常"),
    FROM_JSON_ERRPR(1032001, "JSON转换到对象错误"),
    TO_JSON_ERRPR(1032002, "对象转换到JSON错误"),
    OBJECT_EXIST(1033000, "对象已存在"),
    BUSINESS_CHECK_ERROR(1034000, "业务检查异常异常"),
    INNER_SERVER_ERROR(1035000, "调用内部服务错误"),
    ;

    private Integer errorCode;
    private String errorMessage;

    AppStatus(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
