/*
 * 文件名称：ServerController.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190328 23:12
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190328-01         Rushing0711     M201903282312 新建文件
 ********************************************************************************/
package helper.archetype.cloud.controller;

import com.coding.helpers.tool.cmp.api.annotation.IgnoreResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @IgnoreResponse
    @GetMapping("/product/msg")
    public String msg() {
        return "this is product' msg";
    }
}
