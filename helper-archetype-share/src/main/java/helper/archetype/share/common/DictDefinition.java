/*
 * 文件名称：Dicts.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 19:59
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903091959 新建文件
 ********************************************************************************/
package helper.archetype.share.common;

import com.coding.helpers.tool.cmp.exception.AppBaseStatus;
import com.coding.helpers.tool.cmp.exception.AppException;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义字典.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190309 19:59</font><br>
 * 有两种定义形式：1、接口；2、枚举。如果需要校验数据是否合法，推荐枚举的定义方式。
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DictDefinition {

    Logger log = LoggerFactory.getLogger(DictDefinition.class);

    interface BaseEnum<T> {

        T getValue();

        default String getDesc() {
            log.warn("当前枚举未实现getDesc方法");
            throw new AppException(AppBaseStatus.systemExpectedError(), "当前枚举未实现getDesc方法");
        }
    }

    /** 根据C类型的value值获取枚举实例，如果找不到则返回null. */
    static <T, E extends BaseEnum<T>> E getByValue(Class<E> enumClazz, T value) {
        for (E each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        return null;
    }

    /** 根据C类型从value值获取枚举实例，如果找不到则抛异常. */
    static <T, E extends BaseEnum<T>> E getByValueNoisy(Class<E> enumClazz, T value) {
        E e = getByValue(enumClazz, value);
        if (e == null) {
            log.error("【字典查询】根据字典值找不到对应字典, enumClazz={}, value={}", enumClazz, value);
            throw new AppException(AppBaseStatus.systemExpectedError(), "根据字典值找不到对应字典");
        }
        return e;
    }

    // ==================================================华丽的分割线==================================================

    /** 【代码和数据库】数据是否已删除，1-已删除； 0-未删除 */
    interface Deleted {
        String NAME = "deleted";
        /** 已删除. */
        Integer YES = 1;
        /** 未删除. */
        Integer NO = 0;
    }

    /** 【仅定义在代码】是与否；1-是;0-否. */
    interface YesOrNo {
        String NAME = "yes_or_no";
        /** 是. */
        Integer YES = 1;
        /** 否. */
        Integer NO = 0;
    }

    /** 【代码和数据库】是否启用系统参数 */
    interface Enabled {
        String NAME = "enabled";
        /** 启用 */
        Integer ENABLED = 1;
        /** 停用 */
        Integer DISABLED = 0;
    }

    /** 【仅定义在代码】商品上下架状态. */
    @RequiredArgsConstructor
    enum OnlyValueEnum implements BaseEnum<Integer> {
        /** 在架. */
        UP(0),
        /** 下架. */
        DOWN(1),
        ;
        @NonNull @Getter private Integer value;

        public static final String NAME = "product_status";

        public static OnlyValueEnum of(Integer value) {
            return getByValue(OnlyValueEnum.class, value);
        }
    }
    /** 【仅定义在代码】商品上下架状态. */
    @RequiredArgsConstructor
    enum ValueDescEnum implements BaseEnum<Integer> {
        /** 在架. */
        UP(0, "在架"),
        /** 下架. */
        DOWN(1, "下架"),
        ;
        @NonNull @Getter private Integer value;
        @NonNull @Getter private String desc;

        public static final String NAME = "product_status";

        public static ValueDescEnum of(Integer value) {
            return getByValue(ValueDescEnum.class, value);
        }
    }
}
