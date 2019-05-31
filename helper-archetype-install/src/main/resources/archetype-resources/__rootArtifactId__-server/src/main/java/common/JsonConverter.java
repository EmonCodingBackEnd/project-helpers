#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * 文件名称：Converter.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190311 17:27
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190311-01         Rushing0711     M201903111727 新建文件
 ********************************************************************************/
package ${package}.common;

import ${package}.common.param.ShopPrintSetupParam;
import ${package}.exception.AppStatus;
import ${groupId}.tool.cmp.exception.AppException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * JSON转换器.
 *
 * <p>创建时间: <font style="color:${symbol_pound}00FFFF">20190311 17:27</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@Slf4j
public class JsonConverter {

    private static Gson gson = new Gson();

    private static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        JsonConverter.objectMapper = objectMapper;
    }

    public static ShopPrintSetupParam toShopPrintSetupParam(String paramValue) {
        ShopPrintSetupParam param;
        try {
            param = gson.fromJson(paramValue, new TypeToken<ShopPrintSetupParam>() {}.getType());
        } catch (JsonSyntaxException e) {
            log.error(String.format("【JSON转换错误】JSON转换到对象错误, string=%s", paramValue), e);
            throw new AppException(AppStatus.FROM_JSON_ERRPR);
        }
        return param;
    }

    public static String fromShopPrintSetupParam(ShopPrintSetupParam printParam) {
        String paramValue;
        try {
            paramValue = objectMapper.writeValueAsString(printParam);
        } catch (JsonProcessingException e) {
            log.error(String.format("【JSON转换错误】对象转换到JSON错误, object=%s", printParam), e);
            throw new AppException(AppStatus.TO_JSON_ERRPR);
        }
        return paramValue;
    }

    public static String toJson(Object object) {
        String paramValue;
        try {
            paramValue = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(String.format("【JSON转换错误】对象转换到JSON错误, object=%s", object), e);
            throw new AppException(AppStatus.TO_JSON_ERRPR);
        }
        return paramValue;
    }
}
