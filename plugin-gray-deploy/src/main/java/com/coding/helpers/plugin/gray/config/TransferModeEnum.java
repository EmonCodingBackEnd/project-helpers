package com.coding.helpers.plugin.gray.config;

import lombok.Getter;

/** 传递模式. */
@Getter
public enum TransferModeEnum {
    /** 默认，覆盖优先(自己有则覆盖，没有则透传). */
    OVERRIDE_FIRST("overrideFirst", "默认，覆盖优先(自己有则覆盖，没有则透传)"),
    /** 透传(接收到什么,向下传输什么). */
    PENETRATE("penetrate", "透传(接收到什么,向下传输什么)"),
    /** 覆盖(自己有则覆盖,没有则删除). */
    OVERRIDE("override", "覆盖(自己有则覆盖,没有则删除)"),
    /** 连接(内容叠加). */
    CONCAT("concat", "连接(内容叠加)"),
    ;

    TransferModeEnum(String transferMode, String description) {
        this.transferMode = transferMode;
        this.description = description;
    }

    private String transferMode;

    private String description;
}
