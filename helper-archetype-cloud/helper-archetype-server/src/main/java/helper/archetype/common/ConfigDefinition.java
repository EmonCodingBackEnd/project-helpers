/*
 * 文件名称：Configs.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 19:57
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903091957 新建文件
 ********************************************************************************/
package helper.archetype.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/** 
 * 项目通用配置.
 * 
 * <p>创建时间: <font style="color:#00FFFF">20190309 19:58</font><br>
 * [请在此输入功能详述]
 * 
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "common.config")
@RefreshScope
@Slf4j
public class ConfigDefinition {}
