#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * 文件名称：EnvController.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190405 16:33
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190405-01         Rushing0711     M201904051633 新建文件
 ********************************************************************************/
package ${package}.controller;

import com.coding.helpers.tool.cmp.api.annotation.IgnoreResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class EnvController {

    @Value("${symbol_dollar}{env}")
    private String env
            
    @IgnoreResponse
    @GetMapping("/env/print")
    public String print() {
        return env;
    }
}
