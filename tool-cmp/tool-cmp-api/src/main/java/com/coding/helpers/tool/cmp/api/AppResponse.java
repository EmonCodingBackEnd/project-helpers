package com.coding.helpers.tool.cmp.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class AppResponse<T> implements Serializable {

    private static final long serialVersionUID = -4627150270589944255L;

    protected Integer errorCode = 0;

    protected String errorMessage = "操作成功";

    protected T data;
}
