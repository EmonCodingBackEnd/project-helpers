package com.coding.helpers.plugin.gray.config;

import lombok.Getter;

@Getter
public enum TransferModeEnum {
    
    OVERRIDE_FIRST("overrideFirst", "默认，覆盖优先(自己有则覆盖，没有则透传)"),
    PENETRATE("penetrate", "透传(接收到什么,向下传输什么)"),
    OVERRIDE("override", "覆盖(自己有则覆盖,没有则删除)"),
    CONCAT("concat", "连接(内容叠加)"),
    ;

    TransferModeEnum(String transferMode, String description) {
        this.transferMode = transferMode;
        this.description = description;
    }

    private String transferMode;

    private String description;
}
