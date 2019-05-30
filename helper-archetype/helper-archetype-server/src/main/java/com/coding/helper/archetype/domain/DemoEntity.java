/*
 * 文件名称：ProductInfo.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 08:05
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903100805 新建文件
 ********************************************************************************/
package com.coding.helper.archetype.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品表.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190310 08:15</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "demo")
@Data
public class DemoEntity extends BaseEntity {

    private static final long serialVersionUID = 1048251028840319951L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(
        name = "idGenerator",
        strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy"
    )
    private Long id;

    /** 类目编号. */
    @Column(name = "category_type")
    private Integer categoryType;

    /** 商品名称. */
    @Column(name = "product_name")
    private String productName;

    /** 单价. */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /** 库存. */
    @Column(name = "product_stock")
    private Integer productStock;

    /** 描述. */
    @Column(name = "product_description")
    private String productDescription;

    /** 小图. */
    @Column(name = "product_icon")
    private String productIcon;

    /** 商品状态. */
    @Column(name = "product_status")
    private Integer productStatus;
}
