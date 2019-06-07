/*
 * 文件名称：AppBaseRedisKey.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190327 18:07
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190327-01         Rushing0711     M201903271807 新建文件
 ********************************************************************************/
package com.coding.helpers.tool.cmp.common.cache;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface AppBaseRedisKey {

    /**
     * 获取key，不包含变量部分，适用于声明使用，比如 @Cacheable.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190603 10:51</font><br>
     * 比如： prefix:keyPart1:keyPart2......:
     *
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    String getKey();

    /**
     * 获取key，包含变量部分，适用于编程使用.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190603 10:52</font><br>
     * 比如： prefix:keyPart1:keyPart2......:keyVar
     *
     * @param keyVar - key中的变量部分
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    String getKey(String keyVar);

    /** Redis的Key前缀. */
    String getPrefix();

    default String getDelimiter() {
        return ":";
    }

    /**
     * 实现该接口的枚举可以在调用该方法.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190327 19:10</font><br>
     * 比如： endWithColon = true, prefix:keyPart1:keyPart2......:<br>
     * 比如： endWithColon = false, prefix:keyPart1:keyPart2......:keyVar
     *
     * @param endWithColon - 是否以冒号结尾
     * @param keyParts - 组成RedisKey的元素集合，不包含前缀，前缀是自动添加的
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    default String combineKeyParts(boolean endWithColon, String... keyParts) {
        List<Object> keyList = new ArrayList<>();
        keyList.add(getPrefix());
        keyList.addAll(Arrays.asList(keyParts));
        if (endWithColon) {
            keyList.add("");
        }
        return StringUtils.collectionToDelimitedString(keyList, getDelimiter());
    }
}
