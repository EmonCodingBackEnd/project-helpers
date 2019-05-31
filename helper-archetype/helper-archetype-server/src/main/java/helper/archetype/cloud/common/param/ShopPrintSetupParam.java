/*
 * 文件名称：ShopPrintSetupParam.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190311 16:29
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190311-01         Rushing0711     M201903111629 新建文件
 ********************************************************************************/
package helper.archetype.cloud.common.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ShopPrintSetupParam implements Serializable {

    private static final long serialVersionUID = -6683936192533516696L;

    /** 默认打印机编号（指定是第几号打印机）. */
    private String defaultPrinter;

    /** 小票规格. */
    private String receiptSpec;

    /** 小票二维码. */
    private String qrCode;

    /** 小票二维码提示语. */
    private String qrCodeTips;

    private OrderSetup orderSetup;

    private VerifySetup verifySetup;

    private ShiftSetup shiftSetup;

    /** 订单小票. */
    @Data
    public static class OrderSetup implements Serializable {

        private static final long serialVersionUID = 150769325014415660L;
    }

    /** 核销小票. */
    @Data
    public static class VerifySetup implements Serializable {

        private static final long serialVersionUID = 4804610106064821725L;

        /** 是否与订单同步打印. */
        private String syncPrint;
    }

    /** 交班小票. */
    @Data
    public static class ShiftSetup implements Serializable {

        private static final long serialVersionUID = 2398804312787248446L;
    }
}
